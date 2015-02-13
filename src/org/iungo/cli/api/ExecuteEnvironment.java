package org.iungo.cli.api;

import org.iungo.logger.api.Logger;
import org.iungo.logger.api.SimpleLogger;

public class ExecuteEnvironment {
	
	private static final Logger logger = new SimpleLogger(ExecuteEnvironment.class.getName());
	
	protected final Units units = new Units();
	
	protected final Scopes scopes = new Scopes();

	public Units getUnits() {
		return units;
	}

	public Scopes getScopes() {
		return scopes;
	}

	@Override
	public String toString() {
		return String.format("%s [\nUnits [\n%s\n]\nScopes [\n%s\n]\n]", ExecuteEnvironment.class.getName(), units.toString(), scopes.toString());
	}

}
