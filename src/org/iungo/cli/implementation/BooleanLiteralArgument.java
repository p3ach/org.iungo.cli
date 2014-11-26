package org.iungo.cli.implementation;

public class BooleanLiteralArgument extends LiteralArgument {

	public static final BooleanLiteralArgument FALSE = new BooleanLiteralArgument(false);
	
	public static final BooleanLiteralArgument TRUE = new BooleanLiteralArgument(true);
	
	public BooleanLiteralArgument(final Boolean value) {
		super(value);
	}

	@Override
	public String toString() {
		return String.format("literal boolean %s", result.getValue());
	}
}
