package org.iungo.cli.implementation;

import org.iungo.cli.api.Method;
import org.iungo.cli.api.Scope;

public class MethodBlock extends DefaultBlock {

	protected final Method method;
	
	public MethodBlock(final Method method) {
		super();
		this.method = method;
	}

	@Override
	protected Scope createScope() {
		return new MethodScope(method);
	}

	
}
