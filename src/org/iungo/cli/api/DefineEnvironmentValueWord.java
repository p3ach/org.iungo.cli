package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * 
 * Define an Environment key/value pair with the given key with the given value.
 * 
 * @author dick
 *
 */
public class DefineEnvironmentValueWord extends AbstractDefineValueWord {
	
	public DefineEnvironmentValueWord(final Word key, final Word value) {
		super(key, value);
	}

	@Override
	protected Result define(final Environment executeEnvironment, final String key, final Object value) {
		return executeEnvironment.getEnvironmentScope().getValues().define(key, value);
	}
	
}
