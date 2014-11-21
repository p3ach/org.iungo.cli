package org.iungo.cli.implementation;

import java.util.HashMap;
import java.util.Map;

import org.iungo.cli.api.Block;
import org.iungo.cli.api.Scope;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.context.api.ContextAPI;
import org.iungo.result.api.Result;

public class BlockScope implements Scope {

	protected final Map<String, ScopeValue> declared = new HashMap<>();
	
	protected final Context values = ((ContextAPI) CLIBundleActivator.getInstance().getAPI(ContextAPI.class)).createContext();
	
	protected final Block block;
	
	public BlockScope(final Block block) {
		super();
		this.block = block;
	}

	@Override
	public Block getBlock() {
		return block;
	}

	@Override
	public Result defineValue(final String key, final Object value) {
		if (declared.containsKey(key)) {
			return Result.FALSE;
		}
		declared.put(key, new ScopeValue(value));
		return Result.TRUE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(final String key) {
		if (!declared.containsKey(key)) {
			throw new UnsupportedOperationException(String.format("Value key [%s] not defined.", key));
		}
		return (T) declared.get(key).getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T setValue(final String key, final Object value) {
		if (!declared.containsKey(key)) {
			throw new UnsupportedOperationException(String.format("Value key [%s] not defined.", key));
		}
		return (T) declared.put(key, new ScopeValue(value));
	}

}
