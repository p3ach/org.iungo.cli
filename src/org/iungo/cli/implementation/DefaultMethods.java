package org.iungo.cli.implementation;

import org.iungo.cli.api.Method;
import org.iungo.cli.api.Methods;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.context.api.ContextAPI;

public class DefaultMethods implements Methods {
	
	protected final Context methods = ((ContextAPI) CLIBundleActivator.getInstance().getAPI(ContextAPI.class)).createContext();
	
	@Override
	public Method get(String name) {
		return methods.get(name);
	}

	@Override
	public void add(Method method) {
		methods.put(method.getName(), method);
	}

}
