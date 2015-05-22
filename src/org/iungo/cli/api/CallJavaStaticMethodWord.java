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
public class CallJavaStaticMethodWord extends AbstractCallJavaMethodArgument {
	
	private static final Logger logger = new SimpleLogger(CallJavaStaticMethodWord.class.getName());
	
	public CallJavaStaticMethodWord(final Word m, final Words p, final Word o, final Words a) {
		super(m, p, o, a);
	}

	/**
	 * Call the static method (m) having the parameters (p) on the class (o) with the arguments (a). 
	 */
	@Override
	public Result callMethod(final String m, final Class<?>[] parameters, final Object o, final Object[] arguments) {
		try {
			final Method jm = ((Class<?>) o).getMethod(m, parameters);
			Result result;
			if (Modifier.isStatic(jm.getModifiers()) && Modifier.isPublic(jm.getModifiers())) {
				final Object value = jm.invoke(null, arguments);
				result = new Result(true, String.valueOf(value), value);
			} else {
				result = new Result(false, String.format("Cannot call java static method [%s] on object with class [%s]", m, o.getClass()), null);
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
