package org.iungo.cli.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.iungo.result.api.Result;

public class ConfigUtils {

	public static final ConfigUtils instance = new ConfigUtils();
	
	private ConfigUtils() {
	}
	
	public Result openFromURL(final String name, final URL url) {
		try {
			final Config config = new SimpleConfig(name);
			final List<String> lines = config.getLines();
			final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
			return new Result(true, String.format("Opened config [%s] from URL [%s].", name, url), config);
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

}
