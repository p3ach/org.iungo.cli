package org.iungo.cli.implementation;

import java.util.Iterator;

import org.iungo.cli.api.Argument;
import org.iungo.cli.api.Arguments;
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
	 * Create the Scope for this Block.
	 * Override in subclasses to create a specific Scope.
	 * @return
	 */
	protected Scope createScope(Context context) {
		return new BlockScope(this);
	}
	
	protected Arguments getArguments() {
		return this;
	}
	
	@Override
	public Result go(final Context context) {
		final CLIContext cliContext = new CLIContext(context);
		cliContext.getControl().pushScope(createScope(context));
		try {
			Integer index = 0;
			for (Argument argument : getArguments()) {
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

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(4086);
		Integer index = 0;
		final Iterator<Argument> iterator = this.iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next());
			while (iterator.hasNext()) {
				result.append(String.format("\n%s", iterator.next()));
			}
		}
		return result.toString();
	}

}
