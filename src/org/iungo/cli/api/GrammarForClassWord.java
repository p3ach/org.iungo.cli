package org.iungo.cli.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.iungo.common.api.ClassUtils;
import org.iungo.result.api.Result;

public class GrammarForClassWord implements Word {
	
	private static final String CREATE_OBJECT_GRAMMAR = "create java object \"%s\" having parameters (%s) with arguments (%s)";

	private static final String CALL_JAVA_STATIC_METHOD_GRAMMAR = "call java static method \"%s\" having parameters (%s) on get java class \"%s\" with arguments (%s)";

	private static final String CALL_JAVA_METHOD_GRAMMAR = "call java method \"%s\" having parameters (%s) on ?Object with arguments (%s)";

	private static final String GET_JAVA_STATIC_FIELD_GRAMMAR = "get java static field \"%s\" from get java class \"%s\"";

	private static final String GET_JAVA_FIELD_GRAMMAR = "get java field \"%s\" from ?Object";
	
	private final Word c;

	public GrammarForClassWord(final Word c) {
		super();
		this.c = c;
	}

	protected String createParameters(final Class<?>[] classes) {
		final StringBuilder result = new StringBuilder();
		if (classes.length > 0) {
			result.append("get java class \"" + classes[0].getName() + "\"");
			for (int i = 1; i < classes.length; i++) {
				result.append(", get java class \"" + classes[i].getName() + "\"");
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
			Result result = executeEnvironment.execute(c);
			if (result.isTrue()) {
				/*
				 * Get the Object class.
				 */
				final String className = result.getValue();
				final Class<?> objectClass = ClassUtils.forName(className);
				/*
				 * 
				 */
				final Constructor<?>[] constructors = objectClass.getConstructors();
				/*
				 * 
				 */
				final StringBuilder text = new StringBuilder(2048);
				text.append("Create Java object [%s]:");
				for (Constructor<?> constructor : constructors) {
					Class<?>[] parameterTypes = constructor.getParameterTypes();
					text.append("\n" + String.format(CREATE_OBJECT_GRAMMAR, className, createParameters(parameterTypes), createArguments(parameterTypes.length)));
				}
				
				final java.lang.reflect.Method[] methods = objectClass.getMethods();
				text.append("\nStatic methods:");
				for (Method method : methods) {
					if (Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers())) {
						Class<?>[] parameterTypes = method.getParameterTypes();
						text.append("\n" + String.format(CALL_JAVA_STATIC_METHOD_GRAMMAR, method.getName(), createParameters(parameterTypes), className, createArguments(parameterTypes.length)));
					}
				}

				text.append("\nMethods:");
				for (Method method : methods) {
					if (!Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers())) {
						Class<?>[] parameterTypes = method.getParameterTypes();
						text.append("\n" + String.format(CALL_JAVA_METHOD_GRAMMAR, method.getName(), createParameters(parameterTypes), createArguments(parameterTypes.length)));
					}
				}

				final Field[] fields = objectClass.getFields();
				text.append("\nStatic fields:");
				for (Field field : fields) {
					if (Modifier.isStatic(field.getModifiers()) && Modifier.isPublic(field.getModifiers())) {
						text.append("\n" + String.format(GET_JAVA_STATIC_FIELD_GRAMMAR, field.getName(), className));
					}
				}
				text.append("\nFields:");
				for (Field field : fields) {
					if (!Modifier.isStatic(field.getModifiers()) && Modifier.isPublic(field.getModifiers())) {
						text.append("\n" + String.format(GET_JAVA_FIELD_GRAMMAR, field.getName(), className));
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
