package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public class WhileConditionBlock extends ConditionBlock {

	public WhileConditionBlock(final Argument condition) {
		super(condition);
	}

	@Override
	public Result execute(final Context context) {
		final CLIContext cliContext = new CLIContext(context);
		cliContext.getControl().pushScope(createScope(context));
		try {
			Result result = Result.TRUE;
			while (true) {
				result = condition.execute(context);
				if (result.getState() && ((Boolean) result.getValue())) {
					result = super.execute(context);
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
			return Result.valueOf(exception);
		} finally {
			cliContext.getControl().popScope();
		}
	}

	@Override
	public String toString() {
		return String.format("while (%s) {\n%s\n}", condition, super.toString());
	}
}
