package org.iungo.cli.implementation;

import org.iungo.cli.grammar.AbstractGrammar;
import org.iungo.cli.grammar.DefaultGrammarConstants;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public class BreakFlowControlArgument extends FlowControlArgument {

	@Override
	public Result execute(final Context context) {
		new CLIContext(context).peekBlock().setBreak();
		return Result.TRUE;
	}

	@Override
	public String toString() {
		return AbstractGrammar.getImageForConstant(new Integer[]{DefaultGrammarConstants.BREAK});
	}

}
