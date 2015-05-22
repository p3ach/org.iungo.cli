package org.iungo.cli.api;

import org.iungo.result.api.Result;

public class Unit {
	
	private final String name;
	
	private final Methods methods = new Methods();
	
	public Unit(final String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Methods getMethods() {
		return methods;
	}

	public Result execute(final String name, final Environment environment) {
		try {
			final Method method = methods.get(name);
			if (method == null) {
				return new Result(false, String.format("Method [%s] not defined.", name), null);
			} else {
				return method.go(environment);
			}
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	@Override
	public String toString() {
		return String.format("Name [%s]\nMethods\n[%s]", name, methods);
	}
}
