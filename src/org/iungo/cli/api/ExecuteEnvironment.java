package org.iungo.cli.api;

import org.iungo.filter.api.Filter;
import org.iungo.logger.api.ClassLogger;
import org.iungo.result.api.Result;

public class ExecuteEnvironment {
	
	private static final ClassLogger logger = new ClassLogger(ExecuteEnvironment.class.getName());
	
	protected final Configs configs = new Configs();
	
	protected final Units units = new Units();
	
	private final Frames frames = new Frames();
	
//	protected final Scopes scopes = new Scopes();
	
	protected final ExecuteEnvironmentScope executeEnvironmentScope = new ExecuteEnvironmentScope();
	
	protected final ArgumentFilters argumentFilters = new ArgumentFilters();
	
	protected final ArgumentHandles argumentHandles = new ArgumentHandles();
	
	private final FlowLifecycle flowLifecycle = new FlowLifecycle();
	
	protected Result lastResult = Result.UNDEFINED;
	
	public ExecuteEnvironment() {
		super();
//		scopes.push(executeEnvironmentScope);
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

	public Frames getFames() {
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
	
	public Result getResult() {
		return lastResult;
	}
	
	protected Result setLastResult(final Result result) {
		lastResult = result;

		return result;
	}
	
	/**
	 * Execute the given Argument.
	 * @param argument
	 * @return
	 */
	public Result execute(final Argument argument) {
		logger.begin(String.format("execute(%s)", argument));
		try {
			Result result = argumentFilters.go(argument);
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
			return setLastResult(result);
		} catch (final Exception exception) {
			return setLastResult(Result.valueOf(exception));
		} finally {
			logger.end(String.format("execute(%s)=[%s]", argument, lastResult));
		}
	}
	
	@Override
	public String toString() {
		return String.format("Configs [\n%s\n]\nUnits [\n%s\n]\nFrames [\n%s\n]", configs.toString(), units.toString(), frames.toString());
	}

}
