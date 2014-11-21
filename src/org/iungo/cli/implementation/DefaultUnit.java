package org.iungo.cli.implementation;

import org.iungo.cli.api.Method;
import org.iungo.cli.api.Methods;
import org.iungo.cli.api.Unit;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;

public class DefaultUnit implements Unit {

	protected final String name;
	
	protected final Methods methods = new DefaultMethods();
	
	public DefaultUnit(final String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Methods getMethods() {
		return methods;
	}

	@Override
	public Result go(final String name, final Context context) {
		try {
			Method method = methods.get(name);
			if (method == null) {
				return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(false, String.format("Method [%s] not defined.", name), null);
			} else {
				return method.go(context);
			}
		} catch (final Exception exception) {
			return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(exception);
		}
	}

}
