package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.context.api.Context;
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
	public Result go(final Context context) {
		final Result keyResult = key.go(context);
		if (!keyResult.getState()) {
			return keyResult;
		}
		final Result valueResult = value.go(context);
		if (!valueResult.getState()) {
			return valueResult;
		}
		new CLIContext(context).getControl().setValue((String) keyResult.getValue(), valueResult.getValue());
		return valueResult; // Which will be Result(true, null, Object)...
	}

}
