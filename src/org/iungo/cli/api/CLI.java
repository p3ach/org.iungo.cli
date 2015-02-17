package org.iungo.cli.api;

import org.iungo.id.api.ID;
import org.iungo.result.api.Result;

public interface CLI {

	static final String ROOT_NS = CLI.class.getName();

	static final ID FLOW_CONTROL_BLOCKS = new ID(ROOT_NS, Block.ROOT_NS, null);

	static final ID CONTEXT = new ID(ROOT_NS, "Context", null);

	static final ID CONTROL = new ID(ROOT_NS, null, Control.NS);
	
	static final ID CONFIGS = new ID(ROOT_NS, Configs.ID_ROOT, null);

	static final ID UNITS = new ID(ROOT_NS, Units.ROOT_NS, null);

	Configs getConfigs();

	Units getUnits();
	
	Result compile(String name);
	
	Result execute(String name);
	
	Result parse(String text);
}
