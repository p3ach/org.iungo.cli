package org.iungo.cli.api;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.iungo.result.api.Result;

public class Configs implements Iterable<Entry<String, Config>> {

	public static final String ID_ROOT = Configs.class.getName();

	private final ConcurrentMap<String, Config> configs = new ConcurrentHashMap<String, Config>();

	public Config get(final String name) {
		return configs.get(name);
	}
	
	public Result add(final Config config) {
		if (configs.putIfAbsent(config.getName(), config) == null) {
			return new Result(true, String.format("Added config [%s].",  config.getName()), config);
		} else {
			return new Result(false, String.format("Failed to add config [%s] to configs as name already exists.",  config.getName()), null);
		}
	}

	public Result remove(final String name) {
		if (configs.remove(name) == null) {
			return new Result(false, String.format("Failed to remove config [%s] as it does not exist.", name), null);
		} else {
			return new Result(true, String.format("Removed config [%s].", name), null);
		}
	}

	@Override
	public Iterator<Entry<String, Config>> iterator() {
		return configs.entrySet().iterator();
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(1024);
		final Iterator<String> iterator = configs.keySet().iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next());
			while (iterator.hasNext()) {
				result.append(String.format("\n%s", iterator.next()));
			}
		}
		return result.toString();
	}
}
