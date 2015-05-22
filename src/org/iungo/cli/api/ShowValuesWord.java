package org.iungo.cli.api;

import org.iungo.result.api.Result;

public class ShowValuesWord implements Word {

	@Override
	public Result go(final Environment executeEnvironment) {
		try {
			final String text = executeEnvironment.getFrames().peek().getScopes().peek().getValues().toString();
			return new Result(true, text, text);
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

}
