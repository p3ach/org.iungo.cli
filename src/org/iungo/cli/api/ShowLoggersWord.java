package org.iungo.cli.api;

import org.iungo.logger.api.Loggers;
import org.iungo.result.api.Result;

public class ShowLoggersWord implements Word {

	@Override
	public Result go(Environment executeEnvironment) {
		try {
			final String text = Loggers.getInstance().toString();
			return new Result(true, text, text);
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

}
