package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.api.Block;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;

public class IfConditionBlock extends ConditionBlock {

	protected final Block elseBlock = new DefaultBlock();
	
	public IfConditionBlock(final Argument condition) {
		super(condition);
	}

	@Override
	public Result go(final Context context) {
		final CLIContext cliContext = new CLIContext(context);
		cliContext.getControl().pushScope(createScope(context));
		try {
			Result result = condition.go(context);
			if (result.getState()) {
				if ((Boolean) result.getValue()) {
					return super.go(context);
				} else {
					if (elseBlock == null) {
						return Result.TRUE;
					} else {
						return elseBlock.go(context);
					}
				}
			}
			return result;
		} catch (final Exception exception) {
			return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(exception);
		} finally {
			cliContext.getControl().popScope();
		}
	}

	public Block getElseBlock() {
		return elseBlock;
	}
}
