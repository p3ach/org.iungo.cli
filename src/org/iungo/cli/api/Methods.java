package org.iungo.cli.api;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Methods {
	
	private final ConcurrentMap<String, Method> methods = new ConcurrentHashMap<>();
	
	public Method get(final String name) {
		return methods.get(name);
	}

	public void add(final Method method) {
		methods.put(method.getName(), method);
	}
	
	public void remove(final String name) {
		methods.remove(name);
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(2048);
		final Iterator<String> iterator = methods.keySet().iterator();
		if (iterator.hasNext()) {
			result.append(String.format("%s", iterator.next()));
			while (iterator.hasNext()) {
				result.append(String.format("\n%s", iterator.next()));
			}
		}
		return result.toString();
	}
}
