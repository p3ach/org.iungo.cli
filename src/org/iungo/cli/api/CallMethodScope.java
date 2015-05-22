package org.iungo.cli.api;


/**
 * Create the Scope for calling the given Method with the given Arguments.
 * <p>Define the Parameters as Values then set the Arguments as Values. As setValue will throw an Exception if the key is not defined the constructor may fail.
 * 
 * @author dick
 *
 */
public class CallMethodScope extends BlockScope {

	protected final Method method;
	
	protected final MethodWords methodArguments;
	
	public CallMethodScope(final Environment executeEnvironment, final Method method, final MethodWords methodArguments) {
		super(null);
		this.method = method;
		this.methodArguments = methodArguments;
		/*
		 * Define all the method parameters.
		 */
		for (MethodParameter methodParameter : method.getParameters()) {
			getValues().define((String) methodParameter.getKey().go(executeEnvironment).getValue(), methodParameter.getValue().go(executeEnvironment).getValue());
		}
		/*
		 * Set all the method arguments.
		 */
		for (MethodWord methodArgument : methodArguments) {
			getValues().set((String) methodArgument.getKey().go(executeEnvironment).getValue(), methodArgument.getValue().go(executeEnvironment).getValue());
		}
	}

	/**
	 * Return Method.getBlock().
	 */
	@Override
	public Block getBlock() {
		return method.getBlock();
	}

	@Override
	public String toString() {
		return String.format("%s\n%s", method, super.toString());
	}
}
