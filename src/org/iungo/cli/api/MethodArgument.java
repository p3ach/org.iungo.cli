package org.iungo.cli.api;


public class MethodArgument {
	
	protected final Argument key;
	
	protected final Argument value;
	
	public MethodArgument(final Argument key, final Argument value) {
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
