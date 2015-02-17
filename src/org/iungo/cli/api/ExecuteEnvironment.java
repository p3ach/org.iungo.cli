package org.iungo.cli.api;

import org.iungo.logger.api.Logger;
import org.iungo.logger.api.SimpleLogger;

public class ExecuteEnvironment {
	
	private static final Logger logger = new SimpleLogger(ExecuteEnvironment.class.getName());
	
	protected final Configs configs = new Configs();
	
	protected final Units units = new Units();
	
	protected final Scopes scopes = new Scopes();
	
	public ExecuteEnvironment() {
		super();
		scopes.push(new ExecuteEnvironmentScope());
	}

	public Configs getConfigs() {
		return configs;
	}
	
	public Units getUnits() {
		return units;
	}

	public Scopes getScopes() {
		return scopes;
	}
	
	@Override
	public String toString() {
		return String.format("%s [\nConfigs [\n%s\n]\nUnits [\n%s\n]\nScopes [\n%s\n]\n]", ExecuteEnvironment.class.getName(), configs.toString(), units.toString(), scopes.toString());
	}

}
