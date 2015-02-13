package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * 
 * Set the value for the given key.
 * 
 * @author dick
 *
 */
public class SetValueArgument implements Argument {

	protected final Argument key;
	
	protected final Argument value;
	
	public SetValueArgument(final Argument key, final Argument value) {
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
					executeEnvironment.getScopes().set(key, value);
				}
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	@Override
	public String toString() {
		return String.format("set key %s value %s", key, value);
	}

}
