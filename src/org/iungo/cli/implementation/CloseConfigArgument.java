package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;

public class CloseConfigArgument implements Argument {

	protected final Argument name;
	
	public CloseConfigArgument(final Argument name) {
		super();
		this.name = name;
	}

	@Override
	public Result go(final Context context) {
		return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(new CLIContext(context).getConfigs().removeConfig((String) name.go(context).getValue()) != null, null, null);
	}

}