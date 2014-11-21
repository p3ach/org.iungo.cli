package org.iungo.cli.api;

import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public interface Units {

	static final String ROOT_NS = Units.class.getName();
	
	Unit addUnit(Unit unit);
	
	Unit removeUnit(String name);
	
	Unit getUnit(String name);
	
	Result go(String unit, String method, Context context);
}
