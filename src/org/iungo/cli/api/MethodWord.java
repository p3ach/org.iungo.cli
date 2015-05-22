package org.iungo.cli.api;


public class MethodWord {
	
	protected final Word key;
	
	protected final Word value;
	
	public MethodWord(final Word key, final Word value) {
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
