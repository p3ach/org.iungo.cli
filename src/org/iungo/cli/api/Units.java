package org.iungo.cli.api;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.iungo.logger.api.ClassLogger;
import org.iungo.result.api.Result;

public class Units implements Iterable<Entry<String, Unit>> {

	private static final ClassLogger logger = new ClassLogger(Units.class.getName());
	
	public static final String ROOT_NS = Units.class.getName();
	
	private final ConcurrentMap<String, Unit> units = new ConcurrentHashMap<>();

	public Unit get(final String name) {
		return units.get(name);
	}
	
	public Result add(final Unit unit) {
		logger.begin(String.format("add(%s)", unit.getName()));
		try {
			return Result.valueOf(units.putIfAbsent(unit.getName(), unit) == null);
		} finally {
			logger.end(String.format("add(%s)", unit.getName()));
		}
	}

	public Result remove(final String name) {
		logger.begin(String.format("remove(%s)", name));
		try {
			return Result.valueOf(units.remove(name) != null);
		} finally {
			logger.end(String.format("remove(%s)", name));
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
