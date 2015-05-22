package org.iungo.cli.api;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import org.iungo.cli.grammar.Grammar;
import org.iungo.cli.implementation.ListInputStream;
import org.iungo.result.api.Result;

public abstract class AbstractConfig implements Config {

	private final String name;
	
	private final List<String> lines;
	
	public AbstractConfig(final String name) {
		this(name, new LinkedList<String>());
	}
	
	public AbstractConfig(final String name, final List<String> lines) {
		super();
		this.name = name;
		this.lines = lines;
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
			return new Grammar(new ListInputStream<String>(getLines(), StandardCharsets.UTF_8)).parse();
		} catch (final Exception exception) {
			/*
			 * We died...
			 */
			return Result.valueOf(exception);
		}
	}

	@Override
	public String toString() {
		return String.format("Name [%s] Size [%d]", getName(), getLines().size());
	}
}
