package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.api.Block;
import org.iungo.cli.api.Scope;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;

public class DefaultBlock extends DefaultArguments implements Block {

	protected Integer flowControl = NORMAL_FLOW_CONTROL;

	@Override
	public void setBreak() {
		flowControl = BREAK_FLOW_CONTROL;
	}

	@Override
	public void setContinue() {
		flowControl = CONTINUE_FLOW_CONTROL;
	}

	@Override
	public void setReturn() {
		flowControl = RETURN_FLOW_CONTROL;
	}
	
	/**
	 * Override in subclasses to create a specific Scope.
	 * @return
	 */
	protected Scope createScope() {
		return new BlockScope(this);
	}
	
	@Override
	public Result go(final Context context) {
		final CLIContext cliContext = new CLIContext(context);
		cliContext.getControl().pushScope(createScope());
		try {
			Integer index = 0;
			for (Argument argument : arguments) {
				++index;
				Result result = argument.go(context);
				if (!result.getState()) {
					flowControl = EXCEPTION_FLOW_CONTROL;
					return result;
				}
				if (flowControl != NORMAL_FLOW_CONTROL) {
					return result;
				}
			}
			return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(true, null, index);
		} catch (final Exception exception) {
			return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(exception);
		} finally {
			cliContext.getControl().popScope();
		}
	}

}
