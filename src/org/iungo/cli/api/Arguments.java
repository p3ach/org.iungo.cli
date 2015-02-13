package org.iungo.cli.api;

import java.util.Iterator;

import org.iungo.common.api.ConcurrentLinkedList;
import org.iungo.common.api.ConcurrentList;
import org.iungo.result.api.Result;

public class Arguments implements Argument {

	private final ConcurrentList<Argument> arguments = new ConcurrentLinkedList<>();

	public Boolean add(final Argument argument) {
		return arguments.add(argument);
	}

	public Iterator<Argument> iterator() {
		return arguments.iterator();
	}
	
	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		Result result = Result.TRUE;
		for (Argument argument : arguments) {
			result = argument.execute(executeEnvironment);
			if (!result.getState()) {
				break;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(1024);
		final Iterator<Argument> iterator = arguments.iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next());
			while (iterator.hasNext()) {
				result.append(String.format("\n%s", iterator.next()));
			}
		}
		return result.toString();
	}
}
