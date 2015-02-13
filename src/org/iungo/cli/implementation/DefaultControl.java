package org.iungo.cli.implementation;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import org.iungo.cli.api.Control;
import org.iungo.cli.api.Scope;

public class DefaultControl implements Control {

	protected final Deque<Scope> entries = new LinkedList<>();
	
	@Override
	public void pushScope(final Scope scope) {
		entries.push(scope);
	}

	@Override
	public Scope popScope() {
		return entries.pop();
	}

	@Override
	public Scope peekScope() {
		return entries.peek();
	}

	@Override
	public void defineValue(String key, Object value) {
		peekScope().define(key, value);
	}

	@Override
	public <T> T getValue(final String key) {
		Scope scope = getScopeForValue(key);
		if (scope == null) {
			throw new UnsupportedOperationException(String.format("Value key [%s] not defined.", key));
		}
		return scope.get(key);
	}

	@Override
	public void setValue(final String key, final Object value) {
		Scope scope = getScopeForValue(key);
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
		Iterator<Scope> iterator = entries.descendingIterator();
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
		final StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName() + " [");
		final Iterator<Scope> iterator = entries.iterator();
		while (iterator.hasNext()) {
			result.append("\n" + iterator.next());
		}
		result.append("\n[");
		return result.toString();
	}
}
