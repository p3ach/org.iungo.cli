package org.iungo.cli.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import org.iungo.exception.api.IungoException;
import org.iungo.result.api.Result;

public class URLConfig extends AbstractConfig {

	
	public static Result valueOf(final String name, final String url) {
		try {
			return valueOf(name, new URL(url));
		} catch (final Exception exception) {
			/*
			 * We died...
			 */
			return Result.valueOf(exception);
		}
	}
	
	public static Result valueOf(final String name, final URL url) {
		try {
			Result result = readLinesFrom(url);
			if (result.isTrue()) {
				final List<String> lines = (List<String>) result.getValue();
				final URLConfig urlConfig = new URLConfig(name, lines, url);
				result = new Result(true, urlConfig.toString(), urlConfig) ;
			}
			return result;
		} catch (final Exception exception) {
			/*
			 * We died...
			 */
			return Result.valueOf(exception);
		}
	}
	
	protected static Result readLinesFrom(final URL url) {
		try {
			final List<String> lines = new LinkedList<>();
			final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
			return new Result(true, String.format("Read URL config [%s].", url.toString()), lines);
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}
	
	private final URL url;
	
	protected URLConfig(final String name, final List<String> lines, final URL url) {
		super(name, lines);
		this.url = url;
	}

	public URL getURL() {
		return url;
	}
	
	@Override
	public String toString() {
		return String.format(String.format("[%s] URL [%s]", super.toString(), getURL().toString()));
	}
}
