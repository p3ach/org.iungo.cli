package org.iungo.cli.api;


/**
 * Block to support calling a Method.
 * 
 * @author dick
 *
 */
public class CallMethodBlock extends Block {

	protected final Method method;
	
	protected final MethodWords methodArguments;
	
	public CallMethodBlock(final Method method, final MethodWords methodArguments) {
		super();
		this.method = method;
		this.methodArguments = methodArguments;
	}

	@Override
	protected Scope createScope(Environment executeEnvironment) {
		return new CallMethodScope(executeEnvironment, method, methodArguments);
	}

	@Override
	protected Scope pushScope(final Environment executeEnvironment) {
		pushFrame(executeEnvironment);
		return super.pushScope(executeEnvironment);
	}
	
	@Override
	protected void popScope(final Environment executeEnvironment) {
		super.popScope(executeEnvironment);
		popFrame(executeEnvironment);
	}

	@Override
	protected Words getWords() {
		return method.getBlock();
	}
}
