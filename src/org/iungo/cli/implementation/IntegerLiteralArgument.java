package org.iungo.cli.implementation;

public class IntegerLiteralArgument extends LiteralArgument {

	public IntegerLiteralArgument(final Integer value) {
		super(value);
	}

	@Override
	public String toString() {
		return String.format("literal integer %d", (Integer) result.getValue());
	}
}
