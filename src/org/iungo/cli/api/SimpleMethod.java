package org.iungo.cli.api;

import org.iungo.logger.api.Logger;
import org.iungo.logger.api.SimpleLogger;
import org.iungo.result.api.Result;


public class SimpleMethod implements Method {
	
	private static final Logger logger = new SimpleLogger(SimpleMethod.class.getName());
	
	private final String name;
	
	private final MethodParameters parameters = new MethodParameters();
	
	private final MethodLines lines = new MethodLines();
	
	private final MethodBlock block = new MethodBlock(this);
	
	public SimpleMethod(final String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public MethodParameters getParameters() {
		return parameters;
	}

	@Override
	public MethodLines getLines() {
		return lines;
	}

	@Override
	public Block getBlock() {
		return block;
	}

	@Override
	public Result go(final Environment executeEnvironment) {
		logger.debug(String.format("execute(%)", executeEnvironment));
		try {
			return getBlock().go(executeEnvironment);
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		} finally {
			logger.debug(String.format("end : execute(%)", executeEnvironment));
		}
	}

	@Override
	public String toString() {
		return String.format("%s method \"%s\" parameters (%s) {\n%s\n}", SimpleMethod.class.getName(), name, parameters, block);
	}
}
