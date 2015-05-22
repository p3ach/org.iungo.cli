package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * 
 * @author Dick
 *
 */
public class OpenConfigFromURLWord extends AbstractOpenConfigWord {

	private final Word url;

	public OpenConfigFromURLWord(final Word name, final Word url) {
		super(name);
		this.url = url;
	}

	public Word getURL() {
		return url;
	}
	
	@Override
	public Result go(final Environment environment) {
		try {
			/*
			 * Get the String name.
			 */
			Result result = getName().go(environment);
			if (result.isTrue()) {
				final String name = (String) result.getValue();
				/*
				 * Get the String URL.
				 */
				result = url.go(environment);
				if (result.isTrue()) {
					final String url = (String) result.getValue();
					/*
					 * Create the URL config.
					 */
					result = URLConfig.valueOf(name, url);
					if (result.isTrue()) {
						final Config config = (Config) result.getValue();
						/*
						 * Add the URL config to the environment.
						 */
						result = environment.getConfigs().add(config);
					}
				}
			}
			return result;
		} catch (final Exception exception) {
			/*
			 * We died...
			 */
			return Result.valueOf(exception);
		}
	}
	
	
}
