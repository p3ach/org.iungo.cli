package org.iungo.cli.api;

import org.iungo.result.api.Result;

public interface Session {

	Result execute(String text);
}
