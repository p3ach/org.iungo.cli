package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.api.Block;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public class IfConditionBlock extends ConditionBlock {

	protected final Block elseBlock = new Block();
	
	public IfConditionBlock(final Argument condition) {
		super(condition);
	}

	@Override
	public Result execute(final Context context) {
		final CLIContext cliContext = new CLIContext(context);
		cliContext.getControl().pushScope(createScope(context));
		try {
			Result result = condition.execute(context);
			if (result.getState()) {
				if ((Boolean) result.getValue()) {
					return super.execute(context);
				} else {
					if (elseBlock == null) {
						return Result.TRUE;
					} else {
						return elseBlock.execute(context);
					}
				}
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		} finally {
			cliContext.getControl().popScope();
		}
	}

	public Block getElseBlock() {
		return elseBlock;
	}
}
