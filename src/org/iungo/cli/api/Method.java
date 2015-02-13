package org.iungo.cli.api;

import org.iungo.logger.api.Logger;
import org.iungo.logger.api.SimpleLogger;
import org.iungo.result.api.Result;

public class Method implements Argument {
	
	private static final Logger logger = new SimpleLogger(Method.class.getName());

	public static final String MAIN_METHOD_NAME = "main";
	
	private final String name;
	
	private final MethodParameters parameters = new MethodParameters();
	
	private final MethodLines lines = new MethodLines();
	
	private final MethodBlock block = new MethodBlock(this);
	
	public Method(final String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public MethodParameters getParameters() {
		return parameters;
	}

	public MethodLines getLines() {
		return lines;
	}

	public Block getBlock() {
		return block;
	}

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		logger.debug(String.format("execute(%%)", executeEnvironment));
		try {
			return block.execute(executeEnvironment);
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	@Override
	public String toString() {
		return String.format("method \"%s\" parameters (%s) {\n%s\n}", name, parameters, block);
	}
}
