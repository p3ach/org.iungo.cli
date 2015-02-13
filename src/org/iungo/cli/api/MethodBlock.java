package org.iungo.cli.api;


public class MethodBlock extends Block {

	private final Method method;
	
	public MethodBlock(final Method method) {
		super();
		this.method = method;
	}
	
	public Method getMethod() {
		return method;
	}
}
