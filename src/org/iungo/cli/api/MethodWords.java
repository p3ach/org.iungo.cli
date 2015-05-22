package org.iungo.cli.api;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class MethodWords implements Iterable<MethodWord> {

	private static final long serialVersionUID = 1L;

	private final List<MethodWord> arguments = new LinkedList<>();
	
	public void add(final MethodWord methodArgument) {
		arguments.add(methodArgument);
	}

	@Override
	public Iterator<MethodWord> iterator() {
		return arguments.iterator();
	}

	/**
	 * Return this MethodArguments as per Grammer.
	 */
	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(1024);
		result.append("(");
		final Iterator<MethodWord> iterator = arguments.iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next().toString());
			while (iterator.hasNext()) {
				result.append(", " + iterator.next().toString());
			}
		}
		result.append(")");
		return super.toString();
	}
}
