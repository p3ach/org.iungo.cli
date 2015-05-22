package org.iungo.cli.api;

import org.iungo.result.api.Result;

public class IfConditionWord extends ConditionArgument {

	public IfConditionWord(final Word condition) {
		super(condition);
	}

	@Override
	public Result go(final Environment executeEnvironment) {
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
