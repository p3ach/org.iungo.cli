package org.iungo.cli.api;

import org.iungo.result.api.Result;

public interface Argument {
	
	Result execute(ExecuteEnvironment executeEnvironment);
}
