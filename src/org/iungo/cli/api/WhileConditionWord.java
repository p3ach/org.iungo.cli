package org.iungo.cli.api;

import org.iungo.cli.grammar.AbstractGrammar;
import org.iungo.cli.grammar.GrammarConstants;
import org.iungo.result.api.Result;

public class WhileConditionWord extends ConditionArgument {

	public WhileConditionWord(final Word condition) {
		super(condition);
	}

	@Override
	public Result go(final Environment executeEnvironment) {
		Result result = null;
		try {
			while (true) {
				result = executeEnvironment.execute(condition);
				if (result.isTrue() && (Boolean) result.getValue()) { // Condition executed successfully and returned true.
					result = executeEnvironment.execute(conditionBlock); // Execute the block.
					if (result.isTrue()) {
						if (executeEnvironment.getFlowLifecycle().isState(FlowLifecycle.BREAK)) {
							executeEnvironment.getFlowLifecycle().setNormal();
							break;
						}
						continue; // As we are in a while(true) this will cause the condition to be executed again...
					}
				}
				break; // Anything else and we break!
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	@Override
	public String toString() {
		return String.format(String.format("%s (%s) {}", AbstractGrammar.getImageForConstant(GrammarConstants.WHILE), condition.toString()));
	}
}
