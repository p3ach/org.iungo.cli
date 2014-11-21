package org.iungo.cli.implementation;

import java.util.Stack;

import org.iungo.cli.api.Argument;
import org.iungo.cli.api.Block;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public class WhileConditionBlock extends ConditionBlock {

	public WhileConditionBlock(final Argument condition) {
		super(condition);
	}

	@Override
	public Result go(final Context context) {
		Stack<Block> stack = context.get(BLOCK_STACK_ID.getID());
		stack.push(this);
		Result result = Result.TRUE;
		while (true) {
			result = condition.go(context);
			if (result.getState() && ((Boolean) result.getValue())) {
				result = super.go(context);
				if (result.getState()) {
					if (flowControl > 0) {
						if (flowControl == RETURN_FLOW_CONTROL) {
							break;
						} else if (flowControl == BREAK_FLOW_CONTROL) {
							break;
						} else if (flowControl == CONTINUE_FLOW_CONTROL) {
							continue;
						} else {
							throw new UnsupportedOperationException(String.format("Unsupported flow control [%d]", flowControl));
						}
					}
				} else {
					break;
				}
			} else {
				break;
			}
		}
		stack.pop();
		return result;
	}

	
}
