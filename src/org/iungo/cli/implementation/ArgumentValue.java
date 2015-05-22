package org.iungo.cli.implementation;

import org.iungo.cli.api.Word;
import org.iungo.cli.api.Value;

public class ArgumentValue implements Value {

	protected final Word value;
	
	public ArgumentValue(final Word value) {
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
