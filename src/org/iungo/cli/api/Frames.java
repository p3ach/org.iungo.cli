package org.iungo.cli.api;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import org.iungo.logger.api.ClassLogger;
import org.iungo.logger.api.Loggers;

public class Frames {

	private static final ClassLogger logger = Loggers.create(Frames.class);
	
	private final Deque<Frame> frames = new LinkedList<>();
	
	public void push(final Frame frame) {
		logger.begin(String.format("push(%s)", frame.toString()));
		try {
			frames.push(frame);
		} finally {
			logger.end(String.format("push(%s)=", frame.toString()));
		}
	}
	
	public Frame pop() {
		logger.begin("pop()");
		Frame result = null;
		try {
			result = frames.pop();
			return result;
		} finally {
			logger.end(String.format("pop()=%s", result.toString()));
		}
	}
	
	public Frame peek() {
		logger.begin("peek()");
		Frame result = null;
		try {
			result = frames.peek();
			return result;
		} finally {
			logger.end(String.format("peek()=%s", result.toString()));
		}
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
