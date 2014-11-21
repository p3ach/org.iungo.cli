package org.iungo.cli.implementation;

import java.util.LinkedList;
import java.util.List;

import org.iungo.cli.api.Block;
import org.iungo.cli.api.Method;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;

public class DefaultMethod implements Method {

	protected final String name;
	
	protected final List<String> parameters = new LinkedList<>();
	
	protected final List<String> lines = new LinkedList<>();
	
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
	public List<String> getParameters() {
		return parameters;
	}

	@Override
	public List<String> getLines() {
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
}
