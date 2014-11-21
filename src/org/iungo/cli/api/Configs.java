package org.iungo.cli.api;

public interface Configs {

	static final String ROOT_NS = Configs.class.getName();
	
	Config getConfig(String name);
	
	Config addConfig(Config config);
	
	Config removeConfig(String name);
}
