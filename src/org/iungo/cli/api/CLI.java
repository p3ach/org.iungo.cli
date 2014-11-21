package org.iungo.cli.api;

import java.net.URL;

import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.id.api.ID;
import org.iungo.id.api.IDAPI;
import org.iungo.result.api.Result;

public interface CLI {

	static final String ROOT_NS = CLI.class.getName();

	static final ID FLOW_CONTROL_BLOCKS = ((IDAPI) CLIBundleActivator.getInstance().getAPI(IDAPI.class)).createID(ROOT_NS, Block.ROOT_NS, "");

	static final ID CONTEXT = ((IDAPI) CLIBundleActivator.getInstance().getAPI(IDAPI.class)).createID(ROOT_NS, Context.ROOT_NS, "");

	static final ID CONTROL = ((IDAPI) CLIBundleActivator.getInstance().getAPI(IDAPI.class)).createID(ROOT_NS, "", Control.NS);
	
	static final ID CONFIGS = ((IDAPI) CLIBundleActivator.getInstance().getAPI(IDAPI.class)).createID(ROOT_NS, Configs.ROOT_NS, "");

	static final ID UNITS = ((IDAPI) CLIBundleActivator.getInstance().getAPI(IDAPI.class)).createID(ROOT_NS, Units.ROOT_NS, "");
	
	Result openConfig(String name, URL url);
	
	Result closeConfig(String name);
	
	Config getConfig(String name);
	
	Result goConfig(String unit, String method, Context context);
}
