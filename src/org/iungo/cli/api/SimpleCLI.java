package org.iungo.cli.api;

import java.io.StringReader;

import org.iungo.cli.api.Configs;
import org.iungo.cli.grammar.Grammar;
import org.iungo.result.api.Result;

public class SimpleCLI implements CLI {

	protected final Configs configs = new Configs();

	protected final Units units = new Units();
	
	@Override
	public Configs getConfigs() {
		return configs;
	}

	@Override
	public Units getUnits() {
		return units;
	}

	@Override
	public Result compile(final String name) {
		final Config config = configs.get(name);
		if (config == null) {
			return new Result(false, String.format("Unknown config [%s].", name), null);
		}
		Result result = config.parse();
		if (result.isFalse()) {
			return result;
		}
		final Unit unit = (Unit) result.getValue();
		units.add(unit);
		return new Result(true, String.format("Compiled config [%s] and added unit [%s].", name, unit.getName()), unit);
	}
	
	@Override
	public Result execute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result parse(final String text) {
		final Grammar grammar = new Grammar(new StringReader(String.format("{%s}", text)));
		return grammar.tryParse();
	}
}
