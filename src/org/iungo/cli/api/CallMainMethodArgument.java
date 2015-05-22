package org.iungo.cli.api;


public class CallMainMethodArgument extends CallMethodWord {
	
	public CallMainMethodArgument(final Word unitName, final MethodWords methodArguments) {
		super(unitName, new LiteralWord(SimpleMethod.MAIN_METHOD_NAME), methodArguments);
	}
}
