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
	protected Scope pushScope(final ExecuteEnvironment executeEnvironment) {
		final Scope scope = new CallMethodScope(executeEnvironment, method, methodArguments);
		final Frame frame = new Frame();
		executeEnvironment.getFames().push(frame);
		frame.getScopes().push(scope);
		return scope;
	}
	
	@Override
	protected void popScope(final ExecuteEnvironment executeEnvironment) {
		executeEnvironment.getFames().peek().getScopes().pop();
		executeEnvironment.getFames().pop();
	}

	@Override
	protected Arguments getArguments() {
		return method.getBlock();
	}
}
