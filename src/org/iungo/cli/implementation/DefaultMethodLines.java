package org.iungo.cli.implementation;

import java.util.Iterator;
import java.util.LinkedList;

import org.iungo.cli.api.MethodLines;

public class DefaultMethodLines extends LinkedList<String> implements MethodLines {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(4096);
		final Iterator<String> iterator = this.iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next());
			while (iterator.hasNext()) {
				result.append("\n" + iterator.next());
			}
		}
		return super.toString();
	}
}
