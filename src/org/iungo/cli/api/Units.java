package org.iungo.cli.api;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.iungo.logger.api.Logger;
import org.iungo.logger.api.SimpleLogger;

public class Units implements Iterable<Entry<String, Unit>> {

	private static final Logger logger = new SimpleLogger(Units.class.getName());
	
	public static final String ROOT_NS = Units.class.getName();
	
	private final ConcurrentMap<String, Unit> units = new ConcurrentHashMap<>();

	public Unit get(final String name) {
		return units.get(name);
	}
	
	public void add(final Unit unit) {
		logger.debug(String.format("add(%s)", unit.getName()));
		units.put(unit.getName(), unit);
	}

	public void remove(final String name) {
		logger.debug(String.format("remove(%s)", name));
		units.remove(name);
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
