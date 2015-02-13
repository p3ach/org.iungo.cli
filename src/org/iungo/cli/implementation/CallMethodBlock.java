package org.iungo.cli.implementation;

import org.iungo.cli.api.Arguments;
import org.iungo.cli.api.Block;
import org.iungo.cli.api.ExecuteEnvironment;
import org.iungo.cli.api.Method;
import org.iungo.cli.api.MethodArguments;
import org.iungo.cli.api.Scope;

/**
 * Block to support calling a Method.
 * 
 * @author dick
 *
 */
public class CallMethodBlock extends Block {

	protected final Method method;
	
	protected final MethodArguments methodArguments;
	
	public CallMethodBlock(final Method method, final MethodArguments methodArguments) {
		super();
		this.method = method;
		this.methodArguments = methodArguments;
	}

	@Override
	protected Scope createScope(final ExecuteEnvironment executeEnvironment) {
		return new CallMethodScope(executeEnvironment, method, methodArguments);
	}
	
	@Override
	protected Arguments getArguments() {
		return method.getBlock();
	}
}
