package org.iungo.cli.api;

import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.id.api.ID;
import org.iungo.id.api.IDAPI;
import org.iungo.result.api.Result;

public interface Scope {

	static final String ID_ROOT = Scope.class.getName();
	
	static final ID VALUES = ((IDAPI) CLIBundleActivator.getInstance().getAPI(IDAPI.class)).createID(ID_ROOT, "", "Values");
	
	Block getBlock();
	
	Boolean isDefinedValue(String key);
	
	Result defineValue(String key, Object value);
	
	<T> T getValue(String key);
	
	<T> T setValue(String key, Object value);
}
