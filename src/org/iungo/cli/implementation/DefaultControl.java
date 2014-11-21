package org.iungo.cli.implementation;

import java.util.Deque;
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
	public Scope peepScope() {
		return entries.peek();
	}

	@Override
	public void defineValue(String key, Object value) {
		peepScope().defineValue(key, value);
	}

	@Override
	public <T> T getValue(final String key) {
		return peepScope().getValue(key);
	}

	@Override
	public void setValue(final String key, final Object value) {
		peepScope().setValue(key, value);
	}
}
