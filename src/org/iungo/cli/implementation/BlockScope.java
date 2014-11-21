package org.iungo.cli.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.iungo.cli.api.Block;
import org.iungo.cli.api.Scope;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.context.api.ContextAPI;
import org.iungo.result.api.Result;

public class BlockScope implements Scope {

	protected final Map<String, ScopeValue> declared = new HashMap<>();
	
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
	public Boolean isDefinedValue(final String key) {
		return declared.containsKey(key);
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

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(1024);
		result.append(this.getClass().getName() + "[\n\tValues : ");
		final Iterator<String> iterator = declared.keySet().iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next());
			while (iterator.hasNext()) {
				result.append(", " + iterator.next());
			}
		}
		result.append("\n]");
		return result.toString();
	}
}
