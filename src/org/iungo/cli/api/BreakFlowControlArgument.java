package org.iungo.cli.api;

import org.iungo.cli.grammar.AbstractGrammar;
import org.iungo.cli.grammar.GrammarConstants;
import org.iungo.result.api.Result;

public class BreakFlowControlArgument extends FlowControlArgument {


	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		executeEnvironment.getFlowLifecycle().setBreak();
		return Result.TRUE;
	}

	@Override
	public String toString() {
		return AbstractGrammar.getImageForConstant(new Integer[]{GrammarConstants.BREAK});
	}

}
