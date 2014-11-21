package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.api.Arguments;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.common.api.CommonAPI;
import org.iungo.common.api.ConcurrentList;
import org.iungo.common.api.ConcurrentListGo;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public class DefaultArguments implements Arguments {

	protected ConcurrentList<Argument> arguments = ((CommonAPI) CLIBundleActivator.getInstance().getAPI(CommonAPI.class)).createConcurrentList();
	
	@Override
	public Result go(final Context context) {
		return arguments.read(new ConcurrentListGo<Argument>() {
			@SuppressWarnings("unchecked")
			@Override
			public <T> T go(final ConcurrentList<Argument> concurrentList) {
				Result result = Result.TRUE;
				for (Argument argument : concurrentList) {
					result = argument.go(context);
					if (!result.getState()) {
						break;
					}
				}
				return (T) result;
			}
		});
	}

	@Override
	public Boolean addArgument(final Argument argument) {
		return arguments.add(argument);
	}

}
