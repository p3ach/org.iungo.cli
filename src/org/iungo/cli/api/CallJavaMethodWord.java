package org.iungo.cli.api;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
public class CallJavaMethodWord extends AbstractCallJavaMethodArgument {
	
	private static final Logger logger = new SimpleLogger(CallJavaMethodWord.class.getName());
	
	public CallJavaMethodWord(final Word m, final Words p, final Word o, final Words a) {
		super(m, p, o, a);
	}

	@Override
	Result callMethod(final String m, final Class<?>[] parameters, final Object o, final Object[] arguments) {
		try {
			final Method jm = o.getClass().getMethod(m, parameters);
			Result result;
			if (!Modifier.isStatic(jm.getModifiers()) && Modifier.isPublic(jm.getModifiers())) {
				final Object value = jm.invoke(o, arguments);
				result = new Result(true, String.valueOf(value), value);
			} else {
				result = new Result(false, String.format("Cannot call java object method [%s] on object with class [%s]", m, o.getClass()), null);
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
