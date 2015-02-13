package org.iungo.cli.api;


public class MethodParameter {
	
	private final Argument key;
	
	private final Argument value;
	
	public MethodParameter(final Argument key, final Argument value) {
		this.key = key;
		this.value = value;
	}

	public Argument getKey() {
		return key;
	}

	public Argument getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.format("key %s value %s", key, value);
	}
}
