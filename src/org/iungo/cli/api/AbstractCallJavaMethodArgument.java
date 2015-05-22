package org.iungo.cli.api;

import java.util.List;

import org.iungo.logger.api.Logger;
import org.iungo.logger.api.SimpleLogger;
import org.iungo.result.api.Result;

/**
 * 
 * call java method [m] on [o] with ([p1 [,p2 [,...]]])
 * 
 * @author dick
 *
 */
public abstract class AbstractCallJavaMethodArgument implements Word {
	
	/**
	 * Method name.
	 */
	protected final Word m;

	/**
	 * Method parameters.
	 */
	protected final Words p;
	
	/**
	 * Either the Class or the Object.
	 */
	protected final Word o;
	
	/**
	 * Method arguments.
	 */
	protected final Words a;
	
	public AbstractCallJavaMethodArgument(final Word m, final Words p, final Word o, final Words a) {
		this.m = m;
		this.p = p;
		this.o = o;
		this.a = a;
	}

	protected Result parameters(final Environment executeEnvironment) {
		try {
			/*
			 * Method parameters.
			 */
			Result result;
			Class<?>[] parameters;
			if (p.getSize().equals(0)) {
				parameters = new Class[0];
				result = new Result(true, null, parameters);
			} else {
				result = p.go(executeEnvironment);
				if (result.isTrue()) {
					List<Result> results = (List<Result>) result.getValue();
					parameters = new Class[results.size()];
					int i = 0;
					for (Result r : results) {
						parameters[i++] = (Class<?>) r.getValue();
					}
					result = new Result(true, null, parameters);
				}
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	protected Result parameters(final Object[] arguments) {
		try {
			/*
			 * Create parameters from arguments.
			 */
			Class<?>[] parameters = new Class[arguments.length];
			for (int i = 0; i < arguments.length; i++) {
				parameters[i] = arguments[i].getClass();
			}
			return new Result(true, null, parameters);
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}
	
	protected Result arguments(final Environment executeEnvironment) {
		try {
			Result result;
			if (a.getSize().equals(0)) {
				result = new Result(true, null, new Object[0]);
			} else {
				result = a.go(executeEnvironment);
				if (result.isTrue()) {
					final List<Result> results = (List<Result>) result.getValue();
					Object[] arguments = new Object[results.size()];
					int i = 0;
					for (Result r : results) {
						arguments[i++] = r.getValue();
					}
					result = new Result(true, null, arguments);
				}
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}

	}
	
	/**
	 * Override in sub class to call method.
	 * @param m
	 * @param parameters
	 * @param o
	 * @param arguments
	 * @return
	 */
	abstract Result callMethod(final String m, final Class<?>[] parameters, final Object o, final Object[] arguments);
	
	@Override
	public Result go(final Environment executeEnvironment) {
		try {
			/*
			 * Name of method we want to call.
			 */
			Result result = executeEnvironment.execute(m);
			if (result.isTrue()) {
				final String methodName = (String) result.getValue();
				/*
				 * Class or Object we want to call the method on.
				 */
				result = executeEnvironment.execute(o);
				if (result.isTrue()) {
					final Object object = result.getValue();
					/*
					 * Method parameters.
					 */
					result = parameters(executeEnvironment);
					if (result.isTrue()) {
						Class<?>[] parameters = (Class[]) result.getValue();
						result = arguments(executeEnvironment);
						if (result.isTrue()) {
							Object[] arguments = (Object[]) result.getValue();
							/*
							 * Create parameters from arguments if required.
							 */
							if (p.getSize().equals(0) && !a.getSize().equals(0)) {
								result = parameters(arguments);
								if (result.isTrue()) {
									parameters = (Class[]) result.getValue();
								}
							}
							if (result.isTrue()) {
								/*
								 * Get the method and check it is not static and public.
								 */
								result = callMethod(methodName, parameters, object, arguments);
							}
						}
					}
				}
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	@Override
	public String toString() {
		return String.format("call unit %s method %s with arguments (%s)", m, o, a);
	}
}
