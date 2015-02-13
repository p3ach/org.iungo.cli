package org.iungo.cli.api;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.iungo.cli.grammar.Grammar;
import org.iungo.cli.implementation.ListInputStream;
import org.iungo.common.api.ConcurrentLinkedList;
import org.iungo.common.api.ConcurrentList;
import org.iungo.result.api.Result;

public class SimpleConfig implements Config {

	private final String name;
	
	private final ConcurrentList<String> lines = new ConcurrentLinkedList<String>();
	
	public SimpleConfig(final String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public List<String> getLines() {
		return lines;
	}

	@Override
	public Result compile() {
		try {
			Result result = new Grammar(new ListInputStream<>(getLines(), StandardCharsets.UTF_8)).go(null);
			if (result.getState()) {
//				Units units = new CLIContext(context).getUnits();
//				units.add((Unit) result.getValue());
				return new Result(true, String.format("Compiled config [%s] with [%s].", name, result.getText()), result.getValue());
			} else {
//				final Context resultContext = result.getValue();
//				final String line = lines.get(((Integer) resultContext.get(AbstractGrammar.LINE_KEY)) - 1);
//				result = Result.createFalse(String.format("%s\n%s^\n%s", line, StringUtils.repeat(" ", ((Integer) resultContext.get(AbstractGrammar.COLUMN_KEY)) - 1), result.getText()), result.getValue());
				return result;
			}
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
