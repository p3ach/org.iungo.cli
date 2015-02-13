package org.iungo.cli.api;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class MethodArguments implements Iterable<MethodArgument> {

	private static final long serialVersionUID = 1L;

	private final List<MethodArgument> arguments = new LinkedList<>();
	
	public void add(final MethodArgument methodArgument) {
		arguments.add(methodArgument);
	}

	@Override
	public Iterator<MethodArgument> iterator() {
		return arguments.iterator();
	}

	/**
	 * Return this MethodArguments as per Grammer.
	 */
	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(1024);
		result.append("(");
		final Iterator<MethodArgument> iterator = arguments.iterator();
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
