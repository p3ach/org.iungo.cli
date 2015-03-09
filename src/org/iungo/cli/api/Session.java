package org.iungo.cli.api;

import org.iungo.result.api.Result;

public interface Session {

	ExecuteEnvironment getExecuteEnvironment();
	
	Result execute(String text);
}
