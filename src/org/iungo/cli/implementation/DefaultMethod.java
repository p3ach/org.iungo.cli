package org.iungo.cli.implementation;

import org.iungo.cli.api.Block;
import org.iungo.cli.api.Method;
import org.iungo.cli.api.MethodLines;
import org.iungo.cli.api.MethodParameters;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;

public class DefaultMethod implements Method {

	protected final String name;
	
	protected final MethodParameters parameters = new DefaultMethodParameters();
	
	protected final MethodLines	 lines = new DefaultMethodLines();
	
	protected final MethodBlock block = new MethodBlock(this);
	
	public DefaultMethod(final String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public MethodParameters getParameters() {
		return parameters;
	}

	@Override
	public MethodLines getLines() {
		return lines;
	}

	@Override
	public Block getBlock() {
		return block;
	}

	@Override
	public Result go(final Context context) {
		try {
			return block.go(context);
		} catch (final Exception exception) {
			return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(exception);
		}
	}

	@Override
	public String toString() {
		return String.format("method \"%s\" parameters (%s) {\n%s}", name, parameters, block);
	}
}
