package org.iungo.cli.api;

import java.util.Iterator;

import org.iungo.common.api.ConcurrentLinkedList;
import org.iungo.common.api.ConcurrentList;

public class MethodParameters implements Iterable<MethodParameter> {

	private static final long serialVersionUID = 1L;

	private final ConcurrentList<MethodParameter> methodParameters = new ConcurrentLinkedList<MethodParameter>();
	
	public void add(final MethodParameter methodParameter) {
		methodParameters.add(methodParameter);
	}
	
	@Override
	public Iterator<MethodParameter> iterator() {
		return methodParameters.iterator();
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(1024);
//		final Iterator<MethodParameter> iterator = iterator();
//		if (iterator.hasNext()) {
//			result.append(iterator.next());
//			while (iterator.hasNext()) {
//				result.append(", " + iterator.next());
//			}
//		}
		return super.toString();
	}
}
