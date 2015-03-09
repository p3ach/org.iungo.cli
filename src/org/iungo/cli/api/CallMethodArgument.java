package org.iungo.cli.api;

import org.iungo.logger.api.Logger;
import org.iungo.logger.api.SimpleLogger;
import org.iungo.result.api.Result;

public class CallMethodArgument implements Argument {
	
	private static final Logger logger = new SimpleLogger(CallMethodArgument.class.getName());
	
	protected final Argument unitName;
	
	protected final Argument methodName;

	protected final MethodArguments methodArguments;
	
	public CallMethodArgument(final Argument unitName, final Argument methodName, final MethodArguments methodArguments) {
		this.unitName = unitName;
		this.methodName = methodName;
		this.methodArguments = methodArguments;
	}

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		logger.debug(String.format("execute(%s)", executeEnvironment.getClass().getName()));
		try {
			/*
			 * Unit name.
			 */
			Result result = executeEnvironment.execute(unitName);
			if (result.isFalse()) {
				return result;
			}
			final String unitName = result.getValue();
			final Unit unit = executeEnvironment.getUnits().get(unitName);
			if (unit == null) {
				return new Result(false, String.format("Failed to get Unit with name [%s].", unitName), null);
			}
			
			/*
			 * Method name.
			 */
			result = executeEnvironment.execute(methodName);
			if (result.isFalse()) {
				return result;
			}
			final String methodName = result.getValue();
			final Method method = unit.getMethods().get(methodName);
			if (method == null) {
				return new Result(false, String.format("Failed to get Method with name [%s].", methodName), null);
			}
			
			/*
			 * Call method block.
			 */
			final CallMethodBlock callMethodBlock = new CallMethodBlock(method, methodArguments);
			
			/*
			 * Execute.
			 */
			result = callMethodBlock.execute(executeEnvironment);
			
			/*
			 * Return.
			 */
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	@Override
	public String toString() {
		return String.format("call unit %s method %s with arguments (%s)", unitName, methodName, methodArguments);
	}
}
