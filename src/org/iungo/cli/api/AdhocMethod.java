package org.iungo.cli.api;

import org.iungo.logger.api.Logger;
import org.iungo.logger.api.SimpleLogger;
import org.iungo.result.api.Result;

/**
 * Wrap the given method such that it uses the...
 * 
 * @author dick
 *
 */
public class AdhocMethod implements Method {

	private static final Logger logger = new SimpleLogger(AdhocMethod.class.getName());
	
	private final Method method;
	
	private final AdhocMethodBlock adhocMethodBlock;
	
	public AdhocMethod(final Method method) {
		this.method = method;
		this.adhocMethodBlock = new AdhocMethodBlock(method);
	}

	@Override
	public String getName() {
		return method.getName();
	}

	@Override
	public MethodParameters getParameters() {
		return method.getParameters();
	}

	@Override
	public MethodLines getLines() {
		return method.getLines();
	}

	@Override
	public Block getBlock() {
		return adhocMethodBlock;
	}

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		logger.debug(String.format("execute(%%)", executeEnvironment));
		try {
			return getBlock().execute(executeEnvironment);
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	@Override
	public String toString() {
		return String.format("%s [\n%s\n]", AdhocMethod.class.getName(), super.toString());
	}
}
