package org.iungo.cli.grammar;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import org.iungo.cli.api.Unit;
import org.iungo.result.api.Result;


public abstract class AbstractGrammar {

	/*
	 * Class.
	 */

	public static String getImageForConstant(final Integer constant) {
		String image = GrammarConstants.tokenImage[constant];
		if (image.startsWith("\"")) {
			image = image.substring(1, image.length() - 1);
		}
		return image;
	}

	/**
	 * Return the image string for the given list of Token constants.
	 * Useful for building the input for a given set of tokens.
	 * @param Integer[] of Token constants.
	 * @return
	 */
	public static String getImageForConstant(final Integer...constants) {
		final StringBuilder result = new StringBuilder(256);
		final Iterator<Integer> iterator = Arrays.asList(constants).iterator();
		if (iterator.hasNext()) {
			result.append(getImageForConstant(iterator.next()));
			while (iterator.hasNext()) {
				result.append(" " + getImageForConstant(iterator.next()));
			}
		}
		return result.toString();
	}
	
	/**
	 * Useful for defaulting Token prior to an optional consume.
	 */
	protected final Token EMPTY_DOUBLE_QUOTED_STRING = new Token(GrammarConstants.DOUBLE_QUOTED_STRING, "\"\"");

	private final AtomicInteger unitNameSequence = new AtomicInteger();
	
	protected Integer unitCount = 0;
	
	protected Integer methodCount = 0;
	
	protected Integer wordCount = 0;
	
	public synchronized Result parse() {
		Result result = null;
		try {
			final Unit unit = go();
			result = new Result(true, String.format("Parsed [\nUnits [%d]\nMethods [%d]\nWords [%d]\n]", unitCount, methodCount, wordCount), unit);
			return result;
		} catch (final ParseException parseException) {
			final String message = parseException.getMessage();
			final Integer lineIndex = message.indexOf("line ");
//			if (index != -1) {
//				context.put(LINE_KEY, Integer.valueOf(message.substring(index + 5, message.indexOf(","))));
//			}
			final Integer columnIndex = message.indexOf("column ");
//			if (index != -1) {
//				context.put(COLUMN_KEY, Integer.valueOf(message.substring(index + 7, message.indexOf("."))));
//			}
			result = new Result(false, parseException.getMessage(), null);
			return result;
		} catch (final TokenMgrError tokenMgrError) {
			final String message = tokenMgrError.getMessage();
			final Integer lineIndex = message.indexOf("line ");
//			if (index != -1) {
//				context.put(LINE_KEY, Integer.valueOf(message.substring(index + 5, message.indexOf(","))));
//			}
			final Integer columnIndex = message.indexOf("column ");
//			if (index != -1) {
//				context.put(COLUMN_KEY, Integer.valueOf(message.substring(index + 7, message.indexOf("."))));
//			}
			result = new Result(false, tokenMgrError.getMessage(), null);
			return result;
		} catch (final Exception exception) {
			result = Result.valueOf(exception);
			return result;
		}
	}
	
	protected String createUnitName() {
		return String.format("Unit-%d", unitNameSequence.incrementAndGet());
	}
	
	/**
	 * Return the given <DOUBLE_QUOTED_STRING> Token image with the prefix/suffix " removed. 
	 * @param token
	 * @return
	 */
	protected String removeDoubleQuotes(final Token token) {
		if (token.kind != GrammarConstants.DOUBLE_QUOTED_STRING) {
			throw new UnsupportedOperationException(String.format("Expected < DOUBLE_QUOTED_STRING > token was given [%s].", token));
		}
//		int l = token.image.length();
//		if (token.image.length() == 2) {
//			return "";
//		} else {
			return token.image.substring(1, token.image.length() - 1);
//		}
	}
	
	public abstract Unit go() throws ParseException;
}
