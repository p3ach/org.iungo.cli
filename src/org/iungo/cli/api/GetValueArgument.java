package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * 
 * Get the value for the given key returning ifNull if the value for the given key is null.
 * 
 * ifNull.go(Context) is only called if the value for the given key is null.
 * 
 * @author dick
 *
 */
public class GetValueArgument implements Argument {

	protected final Argument key;
	
	protected final Argument ifNull;
	
	public GetValueArgument(final Argument key, final Argument ifNull) {
		super();
		this.key = key;
		this.ifNull = ifNull;
	}

	/**
	 * Get the value for the given key, returning the ifNull value if the key value is null.
	 */
	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		try {
			/*
			 * Execute the key.
			 */
			Result result = executeEnvironment.execute(key);
			if (result.isTrue()) {
				/*
				 * Get the String key and try and get the value from the Scopes.
				 */
				final String key = result.getValue();
				result = new Result(true, null, executeEnvironment.getFrames().peek().getScopes().get(key));
				if (result.isTrue() && result.getValue() == null) {
					/*
					 * We got a Null value for the key so execute the ifNull.
					 */
					result = executeEnvironment.execute(ifNull);
				}
			}
			/*
			 * Return the result which is either False, or True with the non Null value or the ifNull value (which might be Null... ;-)).
			 */
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	@Override
	public String toString() {
		return String.format("get key %s", key);
	}

}
