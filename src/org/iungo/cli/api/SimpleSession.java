package org.iungo.cli.api;

import org.iungo.logger.api.ClassLogger;
import org.iungo.result.api.Result;

public class SimpleSession implements Session {

	private static final ClassLogger logger = new ClassLogger(SimpleSession.class.getName());
	
//	private final Grammar grammar;
	
	private final ExecuteEnvironment executeEnvironment;
	
	public SimpleSession() {
		super();
//		grammar = new Grammar(new StringReader(""));
		executeEnvironment = new ExecuteEnvironment();
	}

	@Override
	public ExecuteEnvironment getExecuteEnvironment() {
		return executeEnvironment;
	}
	
	@Override
	public synchronized Result execute(final String text) {
		logger.begin(String.format("execute(%s)", text));
		return executeEnvironment.execute(text);
	}
}
