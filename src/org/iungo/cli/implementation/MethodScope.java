package org.iungo.cli.implementation;

import org.iungo.cli.api.Method;

public class MethodScope extends BlockScope {

	protected final Method method;

	public MethodScope(final Method method) {
		super(method.getBlock());
		this.method = method;
		for (String parameter : method.getParameters()) {
			defineValue(parameter, null);
		}
	}
}
