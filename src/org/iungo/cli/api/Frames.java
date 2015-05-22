package org.iungo.cli.api;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class Frames {

//	private static final ClassLogger logger = new ClassLogger(Frames.class);
	
	private final Deque<Frame> frames = new LinkedList<Frame>();
	
	public void push(final Frame frame) {
		frames.push(frame);
	}
	
	public Frame pop() {
		return frames.pop();
	}
	
	public Frame peek() {
		return frames.peek();
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(1024);
		final Iterator<Frame> iterator = frames.iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next().toString());
			while (iterator.hasNext()) {
				result.append(String.format("\n%s", iterator.next().toString()));
			}
		}
		return result.toString();
	}
}
