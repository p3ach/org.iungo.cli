package org.iungo.cli.api;

import org.iungo.result.api.Result;

public class ParseTextArgument implements Argument {

	private final Argument text;
	
	public ParseTextArgument(final Argument value) {
		super();
		this.text = value;
	}

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		return Result.FALSE;
	}

}
