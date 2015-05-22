package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * 
 * @author Dick
 *
 * @param 
 */
public class LiteralWord implements Word {

	public static class BooleanLiteralWord extends LiteralWord {
		
		public static final BooleanLiteralWord FALSE = new BooleanLiteralWord(false);
		
		public static final BooleanLiteralWord TRUE = new BooleanLiteralWord(true);

		public BooleanLiteralWord(final Boolean value) {
			super(value);
		}
	}
	
	public static class IntegerLiteralWord extends LiteralWord {

		public IntegerLiteralWord(final Integer value) {
			super(value);
		}
	}

	public static class LongLiteralWord extends LiteralWord {

		public LongLiteralWord(final Long value) {
			super(value);
		}
		
	}
	
	public static class StringLiteralWord extends LiteralWord {

		public StringLiteralWord(final String value) {
			super(value);
		}
	}
	
	public static final LiteralWord NULL = new LiteralWord(null);
	
	private final Result result;
	
	public LiteralWord(final Object value) {
		super();
		result = new Result(true, null, value);
	}

	@Override
	public Result go(final Environment environment) {
		return result;
	}

	@Override
	public String toString() {
		final Object value = result.getValue();
		if (value == null) {
			return null;
		}
		return String.format("%s\n%s", value.getClass().getName(), value.toString());
	}
}
