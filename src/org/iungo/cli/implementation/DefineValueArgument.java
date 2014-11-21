package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.context.api.Context;
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
	public Result go(final Context context) {
		final Result keyResult = key.go(context);
		if (!keyResult.getState()) {
			return keyResult;
		}
		final Result valueResult = value.go(context);
		if (!valueResult.getState()) {
			return valueResult;
		}
		new CLIContext(context).getControl().defineValue((String) keyResult.getValue(), valueResult.getValue());
		return valueResult; // Which will be Result(true, null, Object)...
	}

}
