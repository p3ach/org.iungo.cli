package org.iungo.cli.implementation;

import java.util.Map.Entry;

import org.iungo.cli.api.Block;
import org.iungo.cli.api.Method;
import org.iungo.cli.api.MethodArgument;
import org.iungo.cli.api.MethodArguments;
import org.iungo.cli.api.MethodParameter;
import org.iungo.context.api.Context;

/**
 * Create the Scope for calling the given Method with the given Arguments.
 * <p>Define the Parameters as Values then set the Arguments as Values. As setValue will throw an Exception if the key is not defined the constructor may fail.
 * 
 * @author dick
 *
 */
public class CallMethodScope extends BlockScope {

	protected final Method method;
	
	public CallMethodScope(final Context context, final Method method, final MethodArguments arguments) {
		super(null);
		this.method = method;
		/*
		 * Define all the method parameters.
		 */
		for (Entry<String, MethodParameter> entry : method.getParameters().entrySet()) {
			defineValue(entry.getKey(), entry.getValue().go(context).getValue());
		}
		/*
		 * Set all the method arguments.
		 */
		for (Entry<String, MethodArgument> entry : arguments.entrySet()) {
			setValue(entry.getKey(), entry.getValue().go(context).getValue());
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
