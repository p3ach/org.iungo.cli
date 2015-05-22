package org.iungo.cli.api;

import org.iungo.logger.api.Loggers;
import org.iungo.result.api.Result;

public class SetLoggerLevelWord implements Word {

	private final Word name;
	
	private final Word level;
	
	public SetLoggerLevelWord(final Word name, final Word level) {
		super();
		this.name = new RequireClassArgument.RequireStringClassArgument(name);
		this.level = new RequireClassArgument.RequireIntegerClassArgument(level);
	}

	@Override
	public Result go(final Environment executeEnvironment) {
		try {
			Result result = executeEnvironment.execute(name);
			if (result.isTrue()) {
				final String name = result.getValue();
				result = executeEnvironment.execute(level);
				if (result.isTrue()) {
					final Integer level = result.getValue();
					Loggers.getInstance().setLevel(name, level);
					result = Result.TRUE;
				}
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

}
