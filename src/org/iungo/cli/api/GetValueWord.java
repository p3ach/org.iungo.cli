package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * 
 * Get the value for the given key.
 * 
 * @author Dick
 *
 */
public class GetValueWord implements Word {

	protected final Word key;
	
	public GetValueWord(final Word key) {
		super();
		this.key = key;
	}

	/**
	 * Get the value for the given key.
	 */
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
					/*
					 * Get the value from the Scopes for the given key.
					 */
					result = environment.getFrames().peek().getScopes().get((String) result.getValue());
				}
			}
			/*
			 * Return the result.
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
