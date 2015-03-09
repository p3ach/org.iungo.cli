package org.iungo.cli.api;

import org.iungo.common.properties.api.JavaSystemProperties;
import org.iungo.result.api.Result;

public class ShowSystemArgument implements Argument {

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		final String text = JavaSystemProperties.instance.toString();
		return new Result(true, text, text);
	}

}
