package org.iungo.cli.api;

import org.iungo.result.api.Result;

public class CreateTDBNodeArgument extends CreateNodeArgument {

	private final Argument location;
	
	public CreateTDBNodeArgument(final Argument location) {
		super();
		this.location = location;
	}

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		try {
			
			return Result.TRUE;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

}
