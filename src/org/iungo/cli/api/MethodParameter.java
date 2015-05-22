package org.iungo.cli.api;


public class MethodParameter {
	
	private final Word key;
	
	private final Word value;
	
	public MethodParameter(final Word key, final Word value) {
		this.key = key;
		this.value = value;
	}

	public Word getKey() {
		return key;
	}

	public Word getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.format("key %s value %s", key, value);
	}
}
