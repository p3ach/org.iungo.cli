package org.iungo.cli.implementation;

import org.iungo.cli.api.Arguments;
import org.iungo.cli.api.Method;
import org.iungo.cli.api.MethodArguments;
import org.iungo.cli.api.Scope;
import org.iungo.context.api.Context;

public class CallMethodBlock extends DefaultBlock {

	protected final Method method;
	
	protected final MethodArguments arguments = new DefaultMethodArguments();
	
	public CallMethodBlock(final Method method, final MethodArguments arguments) {
		super();
		this.method = method;
		this.arguments.putAll(arguments);
	}

	@Override
	protected Scope createScope(final Context context) {
		return new CallMethodScope(context, method, arguments);
	}
	
	@Override
	protected Arguments getArguments() {
		return method.getBlock();
	}
}
