package org.iungo.cli.api;

import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public interface Argument {
	
	Result go(Context context);
}
