package org.iungo.cli.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.iungo.cli.implementation.SimpleValue;
import org.iungo.logger.api.ClassLogger;
import org.iungo.result.api.Result;

public abstract class AbstractScope implements Scope {

	private static final ClassLogger logger = new ClassLogger(AbstractScope.class.getName());
	
	private final Map<String, Value> values = new HashMap<>();

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
		values.put(key, new SimpleValue(value));
		return Result.TRUE;
	}

	@Override
	public Result undefine(final String key) {
		if (!values.containsKey(key)) {
			throw new UnsupportedOperationException(String.format("Value key [%s] not defined.", key));
		}
		values.remove(key);
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
	public <T> T set(final String key, final T value) {
		if (!values.containsKey(key)) {
			throw new UnsupportedOperationException(String.format("Value key [%s] not defined.", key));
		}
		return (T) values.put(key, new SimpleValue(value));
	}

	public String valuesToString() {
		final StringBuilder result = new StringBuilder(1024);
		final Iterator<Entry<String, Value>> iterator = values.entrySet().iterator();
		if (iterator.hasNext()) {
			Entry<String, Value> entry = iterator.next();
			result.append(String.format("%s [%s]", entry.getKey(), entry.toString()));
			while (iterator.hasNext()) {
				entry = iterator.next();
				result.append(String.format("\n%s [Class [%s]]", entry.getKey(), entry.toString()));
			}
		}
		return result.toString();
	}
	
	@Override
	public String toString() {
		return valuesToString();
	}
}
