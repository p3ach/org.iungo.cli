package org.iungo.cli.api;

import org.iungo.cli.grammar.AbstractGrammar;
import org.iungo.cli.grammar.GrammarConstants;
import org.iungo.result.api.Result;

public class ReturnFlowControlWord extends FlowControlArgument {

	private final Word value;
	
	public ReturnFlowControlWord(final Word value) {
		super();
		this.value = value;
	}

	@Override
	public Result go(final Environment executeEnvironment) {
		executeEnvironment.getFlowLifecycle().setReturn();
		return executeEnvironment.execute(value);
	}

	@Override
	public String toString() {
		return AbstractGrammar.getImageForConstant(new Integer[]{GrammarConstants.BREAK});
	}

}
