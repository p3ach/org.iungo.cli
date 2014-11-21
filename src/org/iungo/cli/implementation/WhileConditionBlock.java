package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;

public class WhileConditionBlock extends ConditionBlock {

	public WhileConditionBlock(final Argument condition) {
		super(condition);
	}

	@Override
	public Result go(final Context context) {
		final CLIContext cliContext = new CLIContext(context);
		cliContext.getControl().pushScope(createScope());
		try {
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
			return result;
		} catch (final Exception exception) {
			return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(exception);
		} finally {
			cliContext.getControl().popScope();
		}
	}

	
}
