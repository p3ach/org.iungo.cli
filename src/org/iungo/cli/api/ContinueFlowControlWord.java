package org.iungo.cli.api;

import org.iungo.cli.grammar.AbstractGrammar;
import org.iungo.cli.grammar.GrammarConstants;
import org.iungo.result.api.Result;

public class ContinueFlowControlWord extends FlowControlArgument {

	@Override
	public Result go(final Environment executeEnvironment) {
		executeEnvironment.getFlowLifecycle().setContinue();
		return Result.TRUE;
	}

	@Override
	public String toString() {
		return AbstractGrammar.getImageForConstant(new Integer[]{GrammarConstants.CONTINUE});
	}

}
