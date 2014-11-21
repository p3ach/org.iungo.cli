package org.iungo.cli.api;

import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.id.api.ID;
import org.iungo.id.api.IDAPI;

public interface ArgumentAPI {
	
	static final String ID_ROOT = "Iungo";
	
	static final String ID_NAME = ArgumentAPI.class.getSimpleName();
	
	static final ID ARGUMENT_NAME = ((IDAPI) CLIBundleActivator.getInstance().getAPI(IDAPI.class)).createID(ID_ROOT, ID_NAME, "ArgumentName");

	Argument createArgument(Context context);
}
