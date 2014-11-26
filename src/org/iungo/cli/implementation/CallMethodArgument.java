package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.api.Method;
import org.iungo.cli.api.MethodArguments;
import org.iungo.cli.api.Unit;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public class CallMethodArgument implements Argument {
	
	protected final Argument unitName;
	
	protected final Argument methodName;

	protected final MethodArguments arguments;
	
	public CallMethodArgument(final Argument unitName, final Argument methodName, final MethodArguments arguments) {
		this.unitName = unitName;
		this.methodName = methodName;
		this.arguments = arguments;
	}

	@Override
	public Result go(final Context context) {
		final Unit unit = new CLIContext(context).getUnits().getUnit((String) unitName.go(context).getValue());
		
		final Method method = unit.getMethods().get((String) methodName.go(context).getValue());
		
		final CallMethodBlock callMethodBlock = new CallMethodBlock(method, arguments);
		
		return callMethodBlock.go(context);
	}

	@Override
	public String toString() {
		return String.format("call unit %s method %s with arguments (%s)", unitName, methodName, arguments);
	}
}
