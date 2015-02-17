package org.iungo.cli.api;

import java.io.StringReader;

import org.iungo.cli.grammar.Grammar;
import org.iungo.logger.api.ClassLogger;
import org.iungo.result.api.Result;

public class SimpleSession implements Session {

	private static final ClassLogger logger = new ClassLogger(SimpleSession.class.getName());
	
	private final ExecuteEnvironment executeEnvironment = new ExecuteEnvironment();
	
	private final Grammar grammar = new Grammar(new StringReader(""));
	
	@Override
	public synchronized Result execute(final String text) {
		logger.begin(String.format("execute(%s)", text));
		try {
			grammar.ReInit(new StringReader(String.format("{%s}", text)));
			Result result = grammar.tryParse();
			if (result.isFalse()) {
				return result;
			}
			final Unit unit = result.getValue();
			final Method method = unit.getMethods().get(Method.MAIN_METHOD_NAME);
			final AdhocMethod adhocMethod = new AdhocMethod(method);
			return adhocMethod.execute(executeEnvironment);
		} finally {
			logger.end(String.format("execute(%s)", text));
		}
	}

}
