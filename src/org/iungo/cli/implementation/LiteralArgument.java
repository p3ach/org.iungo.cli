package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.api.ExecuteEnvironment;
import org.iungo.cli.grammar.AbstractGrammar;
import org.iungo.cli.grammar.GrammarConstants;
import org.iungo.result.api.Result;

public class LiteralArgument implements Argument {
	
	public static final LiteralArgument NULL = new LiteralArgument(null) {
		@Override
		public String toString() {
			return AbstractGrammar.getImageForConstant(new Integer[]{GrammarConstants.LITERAL, GrammarConstants.NULL});
		}
	};
	
	protected final Result result;
	
	public LiteralArgument(final Object value) {
		super();
		this.result = new Result(true, null, value);
	}

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		return result;
	}

	@Override
	public String toString() {
		return "literal";
	}

}
