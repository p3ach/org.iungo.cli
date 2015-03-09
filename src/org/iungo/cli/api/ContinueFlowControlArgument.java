package org.iungo.cli.api;

import org.iungo.cli.grammar.AbstractGrammar;
import org.iungo.cli.grammar.GrammarConstants;
import org.iungo.result.api.Result;

public class ContinueFlowControlArgument extends FlowControlArgument {

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		executeEnvironment.getFlowLifecycle().setContinue();
		return Result.TRUE;
	}

	@Override
	public String toString() {
		return AbstractGrammar.getImageForConstant(new Integer[]{GrammarConstants.CONTINUE});
	}

}
