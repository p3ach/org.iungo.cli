package org.iungo.cli.implementation;

import org.iungo.cli.api.Block;
import org.iungo.cli.api.BlockScope;
import org.iungo.cli.api.ExecuteEnvironment;
import org.iungo.cli.api.Method;
import org.iungo.cli.api.MethodArguments;
import org.iungo.cli.api.SimpleMethod;
import org.iungo.cli.api.MethodArgument;
import org.iungo.cli.api.MethodArguments;
import org.iungo.cli.api.MethodParameter;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

/**
 * Create the Scope for calling the given Method with the given Arguments.
 * <p>Define the Parameters as Values then set the Arguments as Values. As setValue will throw an Exception if the key is not defined the constructor may fail.
 * 
 * @author dick
 *
 */
public class CallMethodScope extends BlockScope {

	protected final Method method;
	
	protected final MethodArguments methodArguments;
	
	public CallMethodScope(final ExecuteEnvironment executeEnvironment, final Method method, final MethodArguments methodArguments) {
		super(null);
		this.method = method;
		this.methodArguments = methodArguments;
		/*
		 * Define all the method parameters.
		 */
		for (MethodParameter methodParameter : method.getParameters()) {
			define((String) methodParameter.getKey().execute(executeEnvironment).getValue(), methodParameter.getValue().execute(executeEnvironment).getValue());
		}
		/*
		 * Set all the method arguments.
		 */
		for (MethodArgument methodArgument : methodArguments) {
			set((String) methodArgument.getKey().execute(executeEnvironment).getValue(), methodArgument.getValue().execute(executeEnvironment).getValue());
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
