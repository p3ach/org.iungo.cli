package org.iungo.cli.api;

import org.iungo.result.api.Result;

public class ParseConfigArgument implements Word {

	private final Word name;

	public ParseConfigArgument(final Word name) {
		this.name = name;
	}

	@Override
	public Result go(final Environment executeEnvironment) {
		try {
			Result result = executeEnvironment.execute(name);
			if (result.isTrue()) {
				final String name = result.getValue();
				final Config config = executeEnvironment.getConfigs().get(name);
				if (config == null) {
					result = new Result(false, String.format("Failed to get config [%s].", name), null);
				} else {
					result = config.parse();
					if (result.isTrue()) {
						result = executeEnvironment.getUnits().add((Unit) result.getValue());
					}
				}
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}
	
	
}
