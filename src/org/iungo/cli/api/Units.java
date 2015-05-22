package org.iungo.cli.api;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.iungo.result.api.Result;

public class Units implements Iterable<Entry<String, Unit>> {
	
	public static final String ROOT_NS = Units.class.getName();
	
	private final ConcurrentMap<String, Unit> units;
	
	public Units() {
		super();
		units = new ConcurrentHashMap<>();
	}

	public Unit get(final String name) {
		return units.get(name);
	}
	
	public Result add(final Unit unit) {
		try {
			if (units.putIfAbsent(unit.getName(), unit) == null) {
				return new Result(true, String.format("Added unit [%s].", unit.getName()), null);
			} else {
				return new Result(false, String.format("Failed to add unit [%s] as it already exists.", unit.getName()), null);
			}
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	public Result remove(final String name) {
		try {
			return Result.valueOf(units.remove(name) != null);
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	@Override
	public Iterator<Entry<String, Unit>> iterator() {
		return units.entrySet().iterator();
	}
	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder();
		final Iterator<String> iterator = units.keySet().iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next());
			while (iterator.hasNext()) {
				result.append(String.format("\n%s", iterator.next()));
			}
		}
		return result.toString();
	}

}
