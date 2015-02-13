package org.iungo.cli.api;

import java.util.Iterator;
import java.util.LinkedList;

import org.iungo.common.api.ConcurrentLinkedList;

public class MethodLines {

	private static final long serialVersionUID = 1L;

	private final ConcurrentLinkedList<String> lines = new ConcurrentLinkedList<>();
	
	public void add(final String line) {
		lines.add(line);
	}
	
	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(4096);
		final Iterator<String> iterator = lines.iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next());
			while (iterator.hasNext()) {
				result.append("\n" + iterator.next());
			}
		}
		return super.toString();
	}
}
