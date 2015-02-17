package org.iungo.cli.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.iungo.exception.api.IungoException;

public class URLConfig extends AbstractConfig {

	private final URL url;
	
	public URLConfig(final String name, final URL url) {
		super(name);
		this.url = url;
		try {
			final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (final Exception exception) {
			throw new IungoException(String.format("Failed to read config [%s] from URL [%s].", name, url.toString()), exception);
		}
	}

	public URL getURL() {
		return url;
	}
	
	@Override
	public String toString() {
		return String.format(String.format("%s [\nURL [%s]\n%s\n]"), URLConfig.class.getName(), getURL().toString(), super.toString());
	}
}
