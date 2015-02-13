package org.iungo.cli.api;

import org.iungo.cli.implementation.StringLiteralArgument;

public class CallMainMethodArgument extends CallMethodArgument {
	
	public CallMainMethodArgument(final Argument unitName, final MethodArguments methodArguments) {
		super(unitName, new StringLiteralArgument(Method.MAIN_METHOD_NAME), methodArguments);
	}
}
