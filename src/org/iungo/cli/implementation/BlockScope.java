package org.iungo.cli.implementation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.iungo.cli.api.Block;
import org.iungo.cli.api.Scope;
import org.iungo.cli.api.Value;
import org.iungo.result.api.Result;

public class BlockScope implements Scope {

	protected final Map<String, Value> values = new HashMap<>();
	
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
	public Map<String, Value> getValues() {
		return values;
	}
	
	@Override
	public Boolean isDefinedValue(final String key) {
		return values.containsKey(key);
	}
	
	@Override
	public Result defineValue(final String key, final Object value) {
		if (values.containsKey(key)) {
			return Result.FALSE;
		}
		values.put(key, new DefaultValue(value));
		return Result.TRUE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(final String key) {
		if (!values.containsKey(key)) {
			throw new UnsupportedOperationException(String.format("Value key [%s] not defined.", key));
		}
		return (T) values.get(key).getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T setValue(final String key, final Object value) {
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
