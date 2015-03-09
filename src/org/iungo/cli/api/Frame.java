package org.iungo.cli.api;

public class Frame {

	private final Scopes scopes = new Scopes();
	
	public Scopes getScopes() {
		return scopes;
	}

	@Override
	public String toString() {
		return String.format("%s", scopes.toString());
	}
}
