package org.iungo.cli.implementation;

import org.iungo.cli.api.Config;
import org.iungo.cli.api.Configs;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.context.api.ContextAPI;

public class DefaultConfigs implements Configs {

	protected final Context configs = ((ContextAPI) CLIBundleActivator.getInstance().getAPI(ContextAPI.class)).createContext();

	@Override
	public Config getConfig(final String name) {
		return configs.get(name);
	}
	
	@Override
	public Config addConfig(final Config config) {
		return configs.put(config.getName(), config);
	}

	@Override
	public Config removeConfig(final String name) {
		return configs.remove(name);
	}

}
