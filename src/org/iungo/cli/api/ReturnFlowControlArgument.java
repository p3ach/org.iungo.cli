package org.iungo.cli.api;

import org.iungo.cli.grammar.AbstractGrammar;
import org.iungo.cli.grammar.GrammarConstants;
import org.iungo.result.api.Result;

public class ReturnFlowControlArgument extends FlowControlArgument {

	private final Argument value;
	
	public ReturnFlowControlArgument(final Argument value) {
		super();
		this.value = value;
	}

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		executeEnvironment.getFlowLifecycle().setReturn();
		return executeEnvironment.execute(value);
	}

	@Override
	public String toString() {
		return AbstractGrammar.getImageForConstant(new Integer[]{GrammarConstants.BREAK});
	}

}
