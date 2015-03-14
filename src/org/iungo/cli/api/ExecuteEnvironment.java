package org.iungo.cli.api;

import java.io.StringReader;

import org.iungo.cli.grammar.Grammar;
import org.iungo.filter.api.Filter;
import org.iungo.logger.api.ClassLogger;
import org.iungo.result.api.AggregateResult;
import org.iungo.result.api.Result;

public class ExecuteEnvironment {
	
	private static final ClassLogger logger = new ClassLogger(ExecuteEnvironment.class.getName());

	private class ExecuteTextBlock extends Block {

		private final Block block;
		
		public ExecuteTextBlock(final Block block) {
			super();
			this.block = block;
		}

		@Override
		protected Arguments getArguments() {
			return block;
		}

		@Override
		protected Scope createScope(ExecuteEnvironment executeEnvironment) {
			return executeEnvironmentScope;
		}

		@Override
		protected Scope pushScope(ExecuteEnvironment executeEnvironment) {
			pushFrame(executeEnvironment);
			return super.pushScope(executeEnvironment);
		}

		@Override
		protected void popScope(ExecuteEnvironment executeEnvironment) {
			super.popScope(executeEnvironment);
			popFrame(executeEnvironment);
		}
		
	}
	
	private final Grammar grammar;
	
	protected final Configs configs = new Configs();
	
	protected final Units units = new Units();
	
	private final Frames frames = new Frames();
	
	protected final ExecuteEnvironmentScope executeEnvironmentScope = new ExecuteEnvironmentScope();
	
	protected final ArgumentFilters argumentFilters = new ArgumentFilters();
	
	protected final ArgumentHandles argumentHandles = new ArgumentHandles();
	
	private final FlowLifecycle flowLifecycle = new FlowLifecycle();
	
	public ExecuteEnvironment() {
		super();
		grammar = new Grammar(new StringReader(""));
		argumentFilters.add(new ArgumentFilter() {
			@Override
			public Result go(final Argument argument) {
				return Filter.PERMIT_RESULT;
			}
		});
	}
	
	public Configs getConfigs() {
		return configs;
	}
	
	public Units getUnits() {
		return units;
	}

	public Frames getFrames() {
		return frames;
	}
	
//	public Scopes getScopes() {
//		return scopes;
//	}
	
	public ExecuteEnvironmentScope getExecuteEnvironmentScope() {
		return executeEnvironmentScope;
	}
	
	/*
	 * Flow control.
	 */
	
	public FlowLifecycle getFlowLifecycle() {
		return flowLifecycle;
	}
	
	public ArgumentHandles getArgumentHandles() {
		return argumentHandles;
	}
	
	/**
	 * Execute the given Argument.
	 * @param argument
	 * @return
	 */
	public Result execute(final Argument argument) {
		logger.begin(String.format("execute(%s)", argument));
		Result result = null;
		try {
			result = argumentFilters.go(argument);
			if (result.isTrue() && result.getValue().equals(Filter.PERMIT)) {
				result = argument.execute(this);
				if (result.isTrue()) {
					final ArgumentHandle argumentHandle = argumentHandles.get(argument.getClass().getName());
					if (argumentHandle != null) {
						result = argumentHandle.go(result);
					}
				}
			} else {
				result = new Result(false, String.format("Argument [%s] not permitted by filter.", argument), result);
			}
			return result;
		} catch (final Exception exception) {
			getFlowLifecycle().setException();
			result = Result.valueOf(exception);
//			executeEnvironmentScope.setLastResult(result);
			return result;
		} finally {
			logger.end(String.format("execute(%s)=[%s]", argument, result));
		}
	}

	public Result execute(final String text) {
		logger.begin(String.format("execute(%s)", text));
		Result result = null;
		try {
			grammar.ReInit(new StringReader(String.format("{%s}", text)));
			result = grammar.tryParse();
			if (result.isTrue()) {
				final Unit unit = result.getValue();
				final Method method = unit.getMethods().get(Method.MAIN_METHOD_NAME);
				final ExecuteTextBlock executeTextBlock = new ExecuteTextBlock(method.getBlock());
				result = execute(executeTextBlock);
			}
			return result;
		} catch (final Exception exception) {
			getFlowLifecycle().setException();
			result = Result.valueOf(exception);
			return result;
		} finally {
			logger.end(String.format("execute(%s)=[%s]", text, result));
		}
	}
	
	@Override
	public String toString() {
		return String.format("Configs [\n%s\n]\nUnits [\n%s\n]\nFrames [\n%s\n]", configs.toString(), units.toString(), frames.toString());
	}

}
