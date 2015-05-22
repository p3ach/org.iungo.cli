package org.iungo.cli.api;

import org.iungo.id.api.ID;

public interface Scope {
	
	final Integer ENVIRONMENT_VALUE = 2;
	
	final Integer FRAME_VALUE = 4;
	
	final Integer SCOPE_VALUE = 8;

	static final String ID_ROOT = Scope.class.getName();
	
	static final ID VALUES_ID = new ID(ID_ROOT, null, "Values");
	
	Block getBlock();

	Values getValues();
}
