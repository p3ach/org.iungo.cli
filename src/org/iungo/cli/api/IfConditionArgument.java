package org.iungo.cli.api;

import org.iungo.result.api.Result;

public class IfConditionArgument extends ConditionArgument {

	public IfConditionArgument(final Argument condition) {
		super(condition);
	}

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		Result result = null;
		try {
			result = executeEnvironment.execute(condition);
			if (result.isTrue() && ((Boolean) result.getValue())) {
				/*
				 * Execute if condition returned true,null,true.
				 */
				executeEnvironment.execute(getBlock());
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

}
