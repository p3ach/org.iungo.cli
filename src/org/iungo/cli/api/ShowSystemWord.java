package org.iungo.cli.api;

import org.iungo.result.api.Result;

public class ShowSystemWord extends AbstractHelpedWord {
	
	@Override
	public Result prepare(final Environment environment) {
		return Result.TRUE;
	}

}
