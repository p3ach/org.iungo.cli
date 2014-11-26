package org.iungo.cli.implementation;

import org.iungo.cli.api.Method;

public class MethodBlock extends DefaultBlock {

	protected final Method method;
	
	public MethodBlock(final Method method) {
		super();
		this.method = method;
	}
}
