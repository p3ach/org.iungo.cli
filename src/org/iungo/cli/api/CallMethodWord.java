package org.iungo.cli.api;

import org.iungo.logger.api.Logger;
import org.iungo.logger.api.SimpleLogger;
import org.iungo.result.api.Result;

public class CallMethodWord implements Word {
	
	private static final Logger logger = new SimpleLogger(CallMethodWord.class.getName());
	
	protected final Word unitName;
	
	protected final Word methodName;

	protected final MethodWords methodArguments;
	
	public CallMethodWord(final Word unitName, final Word methodName, final MethodWords methodArguments) {
		this.unitName = unitName;
		this.methodName = methodName;
		this.methodArguments = methodArguments;
	}

	@Override
	public Result go(final Environment environment) {
		logger.debug(String.format("execute(%s)", environment.getClass().getName()));
		try {
			/*
			 * Unit name.
			 */
			Result result = environment.execute(unitName);
			if (result.isFalse()) {
				return result;
			}
			final String unitName = (String) result.getValue();
			final Unit unit = environment.getUnits().get(unitName);
			if (unit == null) {
				return new Result(false, String.format("Failed to get Unit with name [%s].", unitName), null);
			}
			
			/*
			 * Method name.
			 */
			result = environment.execute(methodName);
			if (result.isFalse()) {
				return result;
			}
			final String methodName = (String) result.getValue();
			final Method method = unit.getMethods().get(methodName);
			if (method == null) {
				return new Result(false, String.format("Failed to get Method with name [%s].", methodName), null);
			}
			
			/*
			 * Call method block.
			 */
			final CallMethodBlock callMethodBlock = new CallMethodBlock(method, methodArguments);
			result = callMethodBlock.go(environment);
			
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
