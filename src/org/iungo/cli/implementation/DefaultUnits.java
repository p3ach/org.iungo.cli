package org.iungo.cli.implementation;

import java.util.Map.Entry;

import org.iungo.cli.api.Unit;
import org.iungo.cli.api.Units;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.context.api.ContextAPI;
import org.iungo.result.api.Result;

public class DefaultUnits implements Units {

	protected final Context context = ((ContextAPI) CLIBundleActivator.getInstance().getAPI(ContextAPI.class)).createContext();
	
	@Override
	public Unit addUnit(final Unit unit) {
		return context.put(unit.getName(), unit);
	}

	@Override
	public Unit removeUnit(final String name) {
		return context.remove(name);
	}

	@Override
	public Unit getUnit(final String name) {
		return context.get(name);
	}

	@Override
	public Result go(final String unit, final String method, final Context context) {
		return ((Unit) context.get(unit)).go(method, context);
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder();
		for (Entry<String, Object> entry : context.getEntries()) {
			result.append(String.format("\n%s", entry.getKey()));
		}
		return result.toString();
	}
}
