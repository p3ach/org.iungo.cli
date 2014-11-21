package org.iungo.cli.api;

import org.iungo.cli.grammar.AbstractGrammar;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.id.api.ID;
import org.iungo.id.api.IDAPI;
import org.iungo.result.api.Result;

public interface Grammar {
	
	static final String ROOT_NS = AbstractGrammar.class.getName();
	
	static final String NAME_NS = "";
	
	static final ID THROWABLE_KEY = ((IDAPI) CLIBundleActivator.getInstance().getAPI(IDAPI.class)).createID(ROOT_NS, NAME_NS, "Throwable");
	
	static final ID LINE_KEY = ((IDAPI) CLIBundleActivator.getInstance().getAPI(IDAPI.class)).createID(ROOT_NS, NAME_NS, "Line");
	
	static final ID COLUMN_KEY = ((IDAPI) CLIBundleActivator.getInstance().getAPI(IDAPI.class)).createID(ROOT_NS, NAME_NS, "Column");

	Result go(Context context);
}
