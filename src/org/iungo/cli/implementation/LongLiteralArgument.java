package org.iungo.cli.implementation;

import org.iungo.cli.api.LiteralArgument;

public class LongLiteralArgument extends LiteralArgument {

	public LongLiteralArgument(final Long value) {
		super(value);
	}

	@Override
	public String toString() {
		return String.format("literal long %d", (Long) result.getValue());
	}
}
