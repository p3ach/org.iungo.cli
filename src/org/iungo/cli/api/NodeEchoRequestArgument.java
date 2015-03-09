package org.iungo.cli.api;

import org.iungo.id.api.ID;
import org.iungo.result.api.Result;

public class NodeEchoRequestArgument implements Argument {

	private final Argument id;

	public NodeEchoRequestArgument(final Argument id) {
		super();
		this.id = id;
	}
	
	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		try {
			Result result = executeEnvironment.execute(id);
			if (result.isTrue()) {
				final ID id = ID.valueOf((String) result.getValue());
				result = new Result(true, null, id);
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

}
