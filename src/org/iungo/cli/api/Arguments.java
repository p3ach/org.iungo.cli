package org.iungo.cli.api;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.iungo.result.api.Result;

public class Arguments implements Argument, Iterable<Argument> {

	private final List<Argument> arguments = new LinkedList<>();

	public Boolean add(final Argument argument) {
		return arguments.add(argument);
	}
	
	public Boolean add(final Arguments arguments) {
		Boolean result = true;
		for (Argument argument : arguments) {
			result = add(argument);
			if (!result) {
				break;
			}
		}
		return result;
	}

	@Override
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
