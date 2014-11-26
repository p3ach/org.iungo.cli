package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;

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

	@Override
	public Result go(final Context context) {
		final Result keyResult = key.go(context);
		if (!keyResult.getState()) {
			return keyResult;
		}
		final Object value = new CLIContext(context).getControl().getValue((String) keyResult.getValue());
		if (value == null) {
			final Result ifNullResult = ifNull.go(context);
			if (!ifNullResult.getState()) {
				return ifNullResult;
			}
			return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(true, null, ifNullResult.getValue());
		}
		return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(true, null, value);
	}

	@Override
	public String toString() {
		return String.format("get key %s", key);
	}

}
