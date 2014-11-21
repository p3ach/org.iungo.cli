package org.iungo.cli.grammar;

import java.util.UUID;

import org.iungo.cli.api.Grammar;
import org.iungo.cli.api.Unit;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;


public abstract class AbstractGrammar implements Grammar {

	/*
	 * Class.
	 */
	
	/**
	 * Useful for defaulting Token prior to an optional consume.
	 */
	protected final Token EMPTY_DOUBLE_QUOTED_STRING = new Token(DefaultGrammarConstants.DOUBLE_QUOTED_STRING, "\"\"");
	
	/*
	 * Instance.
	 */

	@Override
	public Result go(final Context context) {
		try {
			return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(true, null, compile());
		} catch (final Exception exception) {
//			final Context context = new Context();
//			context.put(THROWABLE_KEY, throwable);
//			final String message = throwable.getMessage();
//			Integer index = message.indexOf("line ");
//			if (index != -1) {
//				context.put(LINE_KEY, Integer.valueOf(message.substring(index + 5, message.indexOf(","))));
//			}
//			index = message.indexOf("column ");
//			if (index != -1) {
//				context.put(COLUMN_KEY, Integer.valueOf(message.substring(index + 7, message.indexOf("."))));
//			}
			return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(exception);
	  }
	}
	
	protected String createUnitName() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * Return the given <DOUBLE_QUOTED_STRING> Token image with the prefix/suffix " removed. 
	 * @param token
	 * @return
	 */
	protected String removeDoubleQuotes(final Token token) {
		if (token.kind != DefaultGrammarConstants.DOUBLE_QUOTED_STRING) {
			throw new UnsupportedOperationException(String.format("Expected < DOUBLE_QUOTED_STRING > token was given [%s]", token));
		}
//		int l = token.image.length();
//		if (token.image.length() == 2) {
//			return "";
//		} else {
			return token.image.substring(1, token.image.length() - 1);
//		}
	}
	
	protected abstract Unit compile() throws ParseException;
}
