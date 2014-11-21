package org.iungo.cli.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import org.iungo.cli.api.CLI;
import org.iungo.cli.api.Config;
import org.iungo.cli.api.Configs;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;

public class DefaultCLI implements CLI {

	protected final Configs configs = new DefaultConfigs();
	
	@Override
	public Result openConfig(final String name, final URL url) {
		try {
			final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
			final List<String> lines = new LinkedList<>();
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
			final Config config = new DefaultConfig(name, url, lines);
			configs.addConfig(config);
			return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(true, String.format("Opened config name [%s] URL [%s].", name, url), config);
		} catch (final Exception exception) {
			return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(exception);
		}
	}

	@Override
	public Result closeConfig(final String name) {
		return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(configs.removeConfig(name) != null, null, null);
	}

	@Override
	public Config getConfig(final String name) {
		return configs.getConfig(name);
	}

	@Override
	public Result goConfig(final String config, final String method, final Context context) {
		// TODO Auto-generated method stub
		return null;
	}

}
