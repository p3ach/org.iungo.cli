package org.iungo.cli.implementation;

import org.iungo.cli.api.CLI;
import org.iungo.cli.api.Configs;
import org.iungo.cli.api.Units;

public class SimpleCLI implements CLI {

	protected final Configs configs = new ConcurrentHashMapConfigs();

	protected final Units units = new Units();
	
	@Override
	public Configs getConfigs() {
		return configs;
	}

	@Override
	public Units getUnits() {
		return units;
	}
}
