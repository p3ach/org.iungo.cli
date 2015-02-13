package org.iungo.cli.api;

public interface Configs {

	static final String ROOT_NS = Configs.class.getName();
	
	Config get(String name);
	
	void add(Config config);
	
	void remove(String name);
}
