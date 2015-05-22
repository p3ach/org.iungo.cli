package org.iungo.cli.api;

import org.iungo.result.api.Result;

public class SimpleSession implements Session {

//	private static final ClassLogger logger = new ClassLogger(SimpleSession.class);
	
	private final Environment executeEnvironment;
	
	public SimpleSession() {
		super();
		executeEnvironment = new Environment();
	}

	@Override
	public Environment getEnvironment() {
		return executeEnvironment;
	}
	
	@Override
	public synchronized Result go(final String text) {
		return executeEnvironment.execute(text);
	}
}
