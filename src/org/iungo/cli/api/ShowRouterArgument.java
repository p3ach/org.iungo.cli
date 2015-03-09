package org.iungo.cli.api;

import org.iungo.result.api.Result;

public class ShowRouterArgument implements Argument {

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		return Result.TRUE;
	}

}
