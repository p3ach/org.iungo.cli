package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * 
 * Define the given key with the given value.
 * 
 * @author dick
 *
 */
public class DefineValueArgument implements Argument {

	protected final Argument key;
	
	protected final Argument value;
	
	public DefineValueArgument(final Argument key, final Argument value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		try {
			Result result = key.execute(executeEnvironment);
			if (result.isTrue()) {
				final String key = result.getValue();
				result = value.execute(executeEnvironment);
				if (result.isTrue()) {
					final Object value = result.getValue();
					executeEnvironment.getScopes().define(key, value);
				}
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	@Override
	public String toString() {
		return String.format("define key %s value %s", key, value);
	}
}
