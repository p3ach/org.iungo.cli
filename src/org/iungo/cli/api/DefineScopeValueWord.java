package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * 
 * Define a Scope key/value pair with the given key/value.
 * 
 * @author dick
 *
 */
public class DefineScopeValueWord extends AbstractDefineValueWord {
	
	public DefineScopeValueWord(final Word key, final Word value) {
		super(key, value);
	}

	@Override
	protected Result define(final Environment environment, final String key, final Object value) {
		return environment.getFrames().peek().getScopes().peek().getValues().define(key, value);
	}
	

}
