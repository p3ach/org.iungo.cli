package org.iungo.cli.api;


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
	protected Scope createScope(ExecuteEnvironment executeEnvironment) {
		return new CallMethodScope(executeEnvironment, method, methodArguments);
	}

	@Override
	protected Scope pushScope(final ExecuteEnvironment executeEnvironment) {
		/*
		 * Push a new Frame.
		 */
		pushFrame(executeEnvironment);
		/*
		 * Push a new CallMethodScope.
		 */
		return super.pushScope(executeEnvironment);
	}
	
	@Override
	protected void popScope(final ExecuteEnvironment executeEnvironment) {
		super.popScope(executeEnvironment);
		popFrame(executeEnvironment);
	}

	@Override
	protected Arguments getArguments() {
		return method.getBlock();
	}
}
