package org.iungo.cli.implementation;

import org.iungo.cli.api.LiteralArgument;

public class StringLiteralArgument extends LiteralArgument {

	public static final StringLiteralArgument EMPTY_STRING = new StringLiteralArgument("");

	public StringLiteralArgument(final String value) {
		super(value);
	}

	@Override
	public String toString() {
		return String.format("literal string \"%s\"", result.getValue());
	}
}
