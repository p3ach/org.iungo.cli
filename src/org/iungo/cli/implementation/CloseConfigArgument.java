package org.iungo.cli.implementation;

import org.iungo.cli.api.Word;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;

public class CloseConfigArgument implements Word {

	protected final Word name;
	
	public CloseConfigArgument(final Word name) {
		super();
		this.name = name;
	}

	@Override
	public Result execute(final Context context) {
		return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(new CLIContext(context).getConfigs().remove((String) name.executeBlock(context).get()) != null, null, null);
	}

}
