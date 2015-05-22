package org.iungo.cli.api;

import org.iungo.result.api.Result;

public class ParseTextArgument implements Word {

	private final Word text;
	
	public ParseTextArgument(final Word value) {
		super();
		this.text = value;
	}

	@Override
	public Result go(final Environment executeEnvironment) {
		return Result.FALSE;
	}

}
