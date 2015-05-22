package org.iungo.cli.api;

import org.iungo.result.api.Result;

public interface Session {

	Environment getEnvironment();
	
	Result go(String text);
}
