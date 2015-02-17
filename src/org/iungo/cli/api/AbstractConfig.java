package org.iungo.cli.api;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.iungo.cli.grammar.Grammar;
import org.iungo.cli.implementation.ListInputStream;
import org.iungo.common.api.ConcurrentLinkedList;
import org.iungo.common.api.ConcurrentList;
import org.iungo.result.api.Result;

public abstract class AbstractConfig implements Config {

	protected final String name;
	
	protected final ConcurrentList<String> lines = new ConcurrentLinkedList<String>();
	
	public AbstractConfig(final String name) {
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
	public Result parse() {
		try {
			Result result = new Grammar(new ListInputStream<>(getLines(), StandardCharsets.UTF_8)).tryParse();
			if (result.isTrue()) {
				return new Result(true, String.format("Parsed config [%s] with [%s].", name, result.getText()), result.getValue());
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
		return String.format("Name [%s]\nLines [%d]", getName(), getLines().size());
	}
}
