package org.iungo.cli.api;

import java.net.URL;

import org.iungo.result.api.Result;

public class OpenConfigFromURLArgument extends AbstractOpenConfigArgument {

	private final Argument url;

	public OpenConfigFromURLArgument(final Argument name, final Argument url) {
		super(name);
		this.url = url;
	}

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		try {
			Result result = name.execute(executeEnvironment);
			if (result.isTrue()) {
				final String name = result.getValue();
				result = url.execute(executeEnvironment);
				if (result.isTrue()) {
					final URL url = new URL((String) result.getValue());
					final Config config = new URLConfig(name, url);
					result = executeEnvironment.getConfigs().add(config);
				}
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}
	
	
}
