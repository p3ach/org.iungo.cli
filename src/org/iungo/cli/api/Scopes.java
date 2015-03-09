package org.iungo.cli.api;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import org.iungo.logger.api.Logger;
import org.iungo.logger.api.SimpleLogger;

public class Scopes {

	private static final Logger logger = new SimpleLogger(Scopes.class.getName());
	
	protected final Deque<Scope> scopes = new LinkedList<>();

	public void push(final Scope scope) {
		logger.debug(String.format("push(%s)", scope.getClass().getName()));
		scopes.push(scope);
	}

	public Scope pop() {
		logger.debug("pop()");
		try {
			return scopes.pop();
		} finally {
		}
	}

	public Scope peek() {
		logger.debug("peek()");
		return scopes.peek();
	}
	
	public void define(final String key, final Object value) {
		logger.debug(String.format("define(%s, %s)", key, (value == null ? null : value.getClass())));
		peek().define(key, value);
	}

	public <T> T get(final String key) {
		logger.debug(String.format("get(%s)", key));
		final Scope scope = getScopeForValue(key);
		if (scope == null) {
			throw new UnsupportedOperationException(String.format("Value key [%s] not defined.", key));
		}
		return scope.get(key);
	}

	public void set(final String key, final Object value) {
		logger.debug(String.format("set(%s, %s)", key, (value == null ? null : value.getClass())));
		final Scope scope = getScopeForValue(key);
		if (scope == null) {
			throw new UnsupportedOperationException(String.format("Value key [%s] not defined.", key));
		}
		scope.set(key, value);
	}
	
	/**
	 * Get the Scope for the given value key.
	 * @param key
	 * @return
	 */
	protected Scope getScopeForValue(final String key) {
		logger.debug(String.format("getScopeForValue(%s)", key));
		Iterator<Scope> iterator = scopes.descendingIterator();
		while (iterator.hasNext()) {
			Scope scope = iterator.next();
			if (scope.has(key)) {
				return scope;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(2048);
		final Iterator<Scope> iterator = scopes.iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next());
			while (iterator.hasNext()) {
				result.append(String.format("\n%s", iterator.next()));
			}
		}
		return result.toString();
	}
}
