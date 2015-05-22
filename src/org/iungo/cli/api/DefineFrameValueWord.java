package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * 
 * Define a Frame key/value pair with the given key with the given value.
 * 
 * @author dick
 *
 */
public class DefineFrameValueWord extends AbstractDefineValueWord {
	
	public DefineFrameValueWord(final Word key, final Word value) {
		super(key, value);
	}

	@Override
	protected Result define(final Environment environment, final String key, final Object value) {
		return environment.getFrames().peek().getScopes().global(key, value);
	}
	
}
