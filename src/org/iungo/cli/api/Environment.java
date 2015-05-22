package org.iungo.cli.api;

import java.io.StringReader;

import org.iungo.cli.grammar.Grammar;
import org.iungo.filter.api.Filter;
import org.iungo.filter.api.Filters;
import org.iungo.filter.api.SimpleFilters;
import org.iungo.logger.api.ClassLogger;
import org.iungo.logger.api.ClassLogger.Entry;
import org.iungo.logger.api.ClassLogger.Go;
import org.iungo.result.api.Result;

public class Environment {
	
	private static final ClassLogger logger = new ClassLogger(Environment.class);

	private final Grammar grammar;
	
	protected final Configs configs;
	
	protected final Units units;
	
	private final Frames frames;
	
	protected final EnvironmentScope executeEnvironmentScope;
	
	protected final Filters<Word> wordFilters;

	/**
	 * Map of handles used to help words.
	 */
	protected final HelpWordHandles helpWordHandles;
	
	private final FlowLifecycle flowLifecycle = new FlowLifecycle();
	
	public Environment() {
		super();
		grammar = new Grammar();

		configs = new Configs();
		
		units = new Units();
		
		frames = new Frames();
		
		executeEnvironmentScope = new EnvironmentScope(this);
		
		wordFilters = new SimpleFilters<>();
		wordFilters.add(WordFilter.PERMIT_ALL);
		
		helpWordHandles = new HelpWordHandles();
	}
	
	public Configs getConfigs() {
		return configs;
	}
	
	/**
	 * Parse the given Config and if successful attempt to add the returned Unit to the set of Units.
	 * @param name
	 * @return
	 */
	public Result parseConfig(final String name) {
		Result result = null;
		try {
			final Config config = getConfigs().get(name);
			result = config.parse();
			if (result.isTrue()) {
				final Unit unit = (Unit) result.getValue();
				Result addUnitResult = getUnits().add(unit);
				if (addUnitResult.isFalse()) {
					result = new Result(false, addUnitResult.getText(), null);
				}
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}
	
	public Units getUnits() {
		return units;
	}

	public Result callMainMethod(final String name, final MethodWords methodArguments) {
		final CallMainMethodArgument callMainMethodArgument = new CallMainMethodArgument(new LiteralWord.StringLiteralWord(name), methodArguments);
		return execute(callMainMethodArgument);
	}

	public Result callMethod(final String unitName, final String methodName, final MethodWords methodArguments) {
		final CallMethodWord callMethodArgument = new CallMethodWord(new LiteralWord.StringLiteralWord(unitName), new LiteralWord.StringLiteralWord(methodName), methodArguments);
		return execute(callMethodArgument);
	}
	
	public Frames getFrames() {
		return frames;
	}
	
	public EnvironmentScope getEnvironmentScope() {
		return executeEnvironmentScope;
	}
	
	/*
	 * Flow control.
	 */
	
	public FlowLifecycle getFlowLifecycle() {
		return flowLifecycle;
	}
	
	public HelpWordHandles getHelpWordHandles() {
		return helpWordHandles;
	}
	
	public Result filter(final Word word) {
		return wordFilters.go(word);
	}
	
	public Result permit(final Word word) {
		final Result filterResult = filter(word);
		if (filterResult.isTrue() && filterResult.getValue().equals(Filter.PERMIT)) {
			return Result.TRUE;
		}
		return new Result(false, String.format("Word [%s] not permitted by filter.", word), null);
	}

	/**
	 * Words that need help should call this method.
	 * <p>After the word is prepared successfully the appropriate word handle is called
	 * @param helpedWord
	 * @return
	 */
	public Result help(final HelpedWord helpedWord) {
		try {
			final Result prepareResult = helpedWord.prepare(this);
			if (prepareResult.isTrue()) {
				return getHelpWordHandles().go(this, helpedWord.getClass(), prepareResult);
			}
			return new Result(false, prepareResult.getText(), null);
		} catch (final Exception exception) {
			/*
			 * We died...
			 */
			return Result.valueOf(exception);
		}
	}
	
	/**
	 * Execute the given Word<?> if permitted against the current Scope returning Result<T>.
	 * @param word
	 * @return
	 */
	public Result execute(final Word word) {
		final Entry entry = logger.begin(new Go() {
			@Override
			public String go() {
				return ClassLogger.getSignature("execute", new Object[]{word});
			}
		});
		Result result = null;
		try {
			final Result permitResult = permit(word);
			if (permitResult.isTrue()) {
				result = word.go(this);
			}
			return result;
		} catch (final Exception exception) {
			getFlowLifecycle().setException();
			result = Result.valueOf(exception);
			return result;
		} finally {
			entry.end(result);
		}
	}

	/**
	 * Execute the given text if successfully parsed.
	 * <p>The given text is parsed and a generated Unit and main Method are returned which we subsequently execute.
	 * @param text
	 * @return The Result of executing the derived Method.
	 */
	public Result execute(final String text) {
		final Entry entry = logger.begin(new Go() {
			@Override
			public String go() {
				return ClassLogger.getSignature("execute", new Object[]{text});
			}
		});
		Result result = null;
		try {
			grammar.ReInit(new StringReader(String.format("{%s}", text)));
			final Result parseResult = grammar.parse();
			if (parseResult.isTrue()) {
				final Unit unit = (Unit) parseResult.getValue();
				result = execute(new EnvironmentBlock(unit.getMethods().get(Method.MAIN_METHOD_NAME).getBlock()));
			}
			return result;
		} catch (final Exception exception) {
			getFlowLifecycle().setException();
			result = Result.valueOf(exception);
			return result;
		} finally {
			entry.end(result);
		}
	}
	
	@Override
	public String toString() {
		return String.format("Configs [\n%s\n]\nUnits [\n%s\n]\nFrames [\n%s\n]", configs.toString(), units.toString(), frames.toString());
	}

}
