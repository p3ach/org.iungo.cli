package org.iungo.cli.api;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.iungo.result.api.Result;

public class GrammarForObjectWord implements Word {
	
	private final Word o;

	public GrammarForObjectWord(final Word o) {
		super();
		this.o = o;
	}

	protected String createParameters(final Class<?>[] classes) {
		final StringBuilder result = new StringBuilder();
		if (classes.length > 0) {
			result.append("\"" + classes[0].getName() + "\"");
			for (int i = 1; i < classes.length; i++) {
				result.append(", \"" + classes[i].getName() + "\"");
			}
		}
		return result.toString();
	}
	
	protected String createArguments(final Integer count) {
		final StringBuilder result = new StringBuilder();
		if (count > 0) {
			result.append("?A1");
			for (int i = 1; i < count; i++) {
				result.append(", ?A" + String.valueOf(i + 1));
			}
		}
		return result.toString();
	}
	
	@Override
	public Result go(final Environment executeEnvironment) {
		try {
			Result result = executeEnvironment.execute(o);
			if (result.isTrue()) {
				/*
				 * Get the Object class.
				 */
				final Class<?> c = result.getValue().getClass();
				/*
				 * 
				 */
				final java.lang.reflect.Method[] methods = c.getMethods();
				/*
				 * 
				 */
				final StringBuilder text = new StringBuilder(2048);
				for (Method method : methods) {
					if (!Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers())) {
						Class<?>[] ptClasses = method.getParameterTypes();
						text.append("\n" + String.format(" call method name \"%s\" on ?%s with parameters(%s) arguments (%s)", method.getName(), c, createParameters(ptClasses), createArguments(ptClasses.length)));
					}
				}
				result = new Result(true, text.toString(), null);
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}
}
