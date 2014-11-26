package org.iungo.cli.implementation;

public class StringLiteralArgument extends LiteralArgument {

	public StringLiteralArgument(final String value) {
		super(value);
	}

	@Override
	public String toString() {
		return String.format("literal string \"%s\"", result.getValue());
	}
}