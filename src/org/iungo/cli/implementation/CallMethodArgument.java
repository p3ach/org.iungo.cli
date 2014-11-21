package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.api.Arguments;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public class CallMethodArgument implements Argument {
	
	protected final Argument unitName;
	
	protected final Argument methodName;

	protected final Arguments arguments;
	
	public CallMethodArgument(final Argument unitName, final Argument methodName, final Arguments arguments) {
		this.unitName = unitName;
		this.methodName = methodName;
		this.arguments = arguments;
	}

	@Override
	public Result go(final Context context) {
		return new CLIContext(context).getUnits().go((String) unitName.go(context).getValue(), (String) methodName.go(context).getValue(), context);
	}

}
