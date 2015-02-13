package org.iungo.cli.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.iungo.cli.implementation.DefaultValue;
import org.iungo.result.api.Result;

public class BlockScope implements Scope {
	
	private final Block block;

	private final Map<String, Value> values = new HashMap<>();
	
	public BlockScope(final Block block) {
		super();
		this.block = block;
	}

	@Override
	public Block getBlock() {
		return block;
	}

	@Override
	public Map<String, Value> getValues() {
		return values;
	}
	
	@Override
	public Boolean has(final String key) {
		return values.containsKey(key);
	}
	
	@Override
	public Result define(final String key, final Object value) {
		if (values.containsKey(key)) {
			return Result.FALSE;
		}
		values.put(key, new DefaultValue(value));
		return Result.TRUE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(final String key) {
		if (!values.containsKey(key)) {
			throw new UnsupportedOperationException(String.format("Value key [%s] not defined.", key));
		}
		return (T) values.get(key).getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T set(final String key, final Object value) {
		if (!values.containsKey(key)) {
			throw new UnsupportedOperationException(String.format("Value key [%s] not defined.", key));
		}
		return (T) values.put(key, new DefaultValue(value));
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(1024);
		result.append(this.getClass().getName() + "[\nValues : ");
		final Iterator<String> iterator = values.keySet().iterator();
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
