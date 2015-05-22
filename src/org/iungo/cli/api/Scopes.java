package org.iungo.cli.api;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import org.iungo.result.api.Result;

/**
 * 
 * @author dick
 *
 */
public class Scopes implements Values {

	protected final Deque<Scope> scopes;
	
	public Scopes() {
		super();
		scopes = new LinkedList<>();
	}

	public void push(final Scope scope) {
		scopes.push(scope);
	}
	
	public Scope pop() {
		return scopes.pop();
	}

	public Scope peek() {
		return scopes.peek();
	}
	
	public Scope peekLast() {
		return scopes.peekLast();
	}
	
	@Override
	public Result isDefined(final String key) {
		final Iterator<Scope> iterator = scopes.descendingIterator();
		while (iterator.hasNext()) {
			final Scope scope = iterator.next();
			final Result result = scope.getValues().isDefined(key);
			if (result.isTrue()) {
				return result;
			}
		}
		return Result.FALSE;
	}

	@Override
	public Result define(final String key, final Object value) {
		return peek().getValues().define(key, value);
	}

	public Result global(final String key, final Object value) {
		return peekLast().getValues().define(key, value);
	}

	@Override
	public Result undefine(final String key) {
		return peek().getValues().undefine(key);
	}

	@Override
	public Result get(final String key) {
		return isDefined(key);
	}

	/**
	 * Set the given key/value pair.
	 */
	@Override
	public Result set(final String key, final Object value) {
		final Iterator<Scope> iterator = scopes.descendingIterator();
		while (iterator.hasNext()) {
			final Scope scope = iterator.next();
			final Result result = scope.getValues().set(key, value);
			if (result.isTrue()) {
				return result;
			}
		}
		return Result.FALSE;
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
