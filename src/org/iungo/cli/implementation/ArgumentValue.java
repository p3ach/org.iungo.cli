package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.api.Value;

public class ArgumentValue implements Value {

	protected final Argument value;
	
	public ArgumentValue(final Argument value) {
		super();
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue() {
		return (T) value;
	}

	@Override
	public <T> T setValue(T value) {
		throw new UnsupportedOperationException();
	}

}
