package org.iungo.cli.implementation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.iungo.cli.api.Config;
import org.iungo.cli.api.Configs;

public class ConcurrentHashMapConfigs implements Configs {

	private final Map<String, Config> configs = new ConcurrentHashMap<String, Config>();

	@Override
	public Config get(final String name) {
		return configs.get(name);
	}
	
	@Override
	public void add(final Config config) {
		configs.put(config.getName(), config);
	}

	@Override
	public void remove(final String name) {
		configs.remove(name);
	}

}
