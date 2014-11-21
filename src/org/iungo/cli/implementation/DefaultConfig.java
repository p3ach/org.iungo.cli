package org.iungo.cli.implementation;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.iungo.cli.api.Config;
import org.iungo.cli.api.Unit;
import org.iungo.cli.api.Units;
import org.iungo.cli.grammar.DefaultGrammar;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;

public class DefaultConfig implements Config {

	protected final String name;
	
	protected final URL url;
	
	protected final List<String> lines;
	
	public DefaultConfig(final String name, final URL url, final List<String> lines) {
		super();
		this.name = name;
		this.url = url;
		this.lines = lines;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public URL getURL() {
		return url;
	}
	
	@Override
	public List<String> getLines() {
		return lines;
	}

	@Override
	public Result compile(final Context context) {
		try {
			Result result = new DefaultGrammar(new ListInputStream<>(getLines(), StandardCharsets.UTF_8)).go(context);
			if (result.getState()) {
				Units units = new CLIContext(context).getUnits();
				units.addUnit((Unit) result.getValue());
				return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(true, String.format("Compiled config [%s] with [%s].", name, result.getText()), result.getValue());
			} else {
//				final Context resultContext = result.getValue();
//				final String line = lines.get(((Integer) resultContext.get(AbstractGrammar.LINE_KEY)) - 1);
//				result = Result.createFalse(String.format("%s\n%s^\n%s", line, StringUtils.repeat(" ", ((Integer) resultContext.get(AbstractGrammar.COLUMN_KEY)) - 1), result.getText()), result.getValue());
				return result;
			}
		} catch (final Exception exception) {
			return ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(exception);
		}
	}

}
