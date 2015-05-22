package org.iungo.cli.api;

import org.iungo.result.api.Result;


public class EnvironmentScope extends BlockScope {

	private final Environment environment;
	
	public EnvironmentScope(final Environment environment) {
		super(null);
		this.environment = environment;
	}

	/**
	 * Execute the given Block against this ExecuteEnvironmentScope.
	 * <p>Changes to the Scope are maintained between calls to this method.
	 * <p>I.E. Defining key "foo" with value "hello world" will return Result(true, "hello world", "hello world") if a subsequent call to get key "foo" is made.  
	 * @param argument
	 * @return
	 */
	public Result executeBlock(final Block block) {
		try {
			return (new EnvironmentBlock(block)).go(environment);
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	/**
	 * Execute the given Argument against this ExecuteEnvironmentScope.
	 * <p>Changes to the Scope are maintained between calls to this method.
	 * <p>I.E. Defining key "foo" with value "hello world" will return Result(true, "hello world", "hello world") if a subsequent call to get key "foo" is made.  
	 * @param argument
	 * @return
	 */
	public Result executeWord(final Word argument) {
		try {
			return (Result) executeBlock(Block.valueOf(argument));
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}
}
