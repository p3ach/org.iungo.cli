package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * 
 * Set given key/value.
 * 
 * @author dick
 *
 */
public class SetValueWord implements Word {

	protected final Word key;
	
	protected final Word value;
	
	public SetValueWord(final Word key, final Word value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public Result go(final Environment environment) {
		try {
			/*
			 * Check if this is permitted.
			 */
			Result result = environment.permit(this);
			if (result.isTrue()) {
				/*
				 * Get the key.
				 */
				result = key.go(environment);
				if (result.isTrue()) {
					final String key = (String) result.getValue();
					/*
					 * Get the value.
					 */
					result = value.go(environment);
					if (result.isTrue()) {
						/*
						 * Set the key/value.
						 */
						result = environment.getFrames().peek().getScopes().set(key, result.getValue());
					}
				}
			}
			/*
			 * Return result.
			 */
			return result;
		} catch (final Exception exception) {
			/*
			 * We died...
			 */
			return Result.valueOf(exception);
		}
	}
}
