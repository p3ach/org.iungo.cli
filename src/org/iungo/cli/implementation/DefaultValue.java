package org.iungo.cli.implementation;

import org.iungo.cli.api.Value;

public class DefaultValue implements Value {

	protected Object value = null;
	
	public DefaultValue(final Object value) {
		super();
		this.value = value;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue() {
		return (T) value;
	}

	public <T> T setValue(final T value) {
		this.value = value;
		return value;
	}

}
