package org.iungo.cli.api;

import java.util.List;

import org.iungo.common.api.ClassUtils;
import org.iungo.result.api.Result;

/**
 * Create an Object from the given name, parameters and arguments.
 * 
 * @author dick
 *
 */
public class CreateJavaObjectWord implements Word {
	
	protected final Word o;

	protected final Words p;
	
	protected final Words a;
	
	public CreateJavaObjectWord(final Word o, final Words p, final Words a) {
		this.o = o;
		this.p = p;
		this.a = a;
	}

	@Override
	public Result go(final Environment executeEnvironment) {
		try {
			Result result = executeEnvironment.execute(o);
			if (result.isTrue()) {
				final Class<?> objectClass = ClassUtils.forName((String) result.getValue());
				/*
				 * Constructor parameters.
				 */
				Class<?>[] parameters = null;
				if (p.getSize().equals(0)) {
					parameters = new Class<?>[0];
				} else {
					result = p.go(executeEnvironment);
					if (result.isTrue()) {
						List<Result> results = result.getValue();
						parameters = new Class<?>[results.size()];
						int i = 0;
						for (Result r : results) {
							parameters[i++] = (Class<?>) r.getValue();
						}
					}
				}
				if (result.isTrue()) {
					Object[] arguments = null;
					if (a.getSize().equals(0)) {
						arguments = new Object[0];
					} else {
						result = a.go(executeEnvironment);
						if (result.isTrue()) {
							List<Result> results = result.getValue();
							arguments = new Object[results.size()];
							int i = 0;
							for (Result r : results) {
								arguments[i++] = r.getValue();
							}
							/*
							 * Create parameters from arguments if required.
							 */
							if (p.getSize().equals(0) && !a.getSize().equals(0)) {
								parameters = new Class<?>[a.getSize()];
								i = 0;
								for (Result r : results) {
									parameters[i++] = r.getValue().getClass();
								}
							}
						}
					}
					if (result.isTrue()) {
						/*
						 * Create Object.
						 */
						final Object value = objectClass.getConstructor(parameters).newInstance(arguments);
						result = new Result(true, String.valueOf(value), value);
					}
				}

			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

}
