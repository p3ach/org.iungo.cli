package org.iungo.cli.api;

import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public interface Unit {

	String getName();
	
	Methods getMethods();
	
	Result go(String name, Context context);
}
