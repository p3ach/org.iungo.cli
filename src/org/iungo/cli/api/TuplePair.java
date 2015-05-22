package org.iungo.cli.api;

public class TuplePair<A, B> extends Tuple {

	private final A a;
	
	private final B b;

	public TuplePair(final A a, final B b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
