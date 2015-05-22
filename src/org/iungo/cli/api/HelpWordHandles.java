package org.iungo.cli.api;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.iungo.result.api.Result;

/**
 * 
 * @author Dick
 */
public class HelpWordHandles {

	public static interface Handle {
		
		/**
		 * Handle the HelpedWord using the given prepare Result.
		 * @param Result of calling prepare(Environment)
		 * @return
		 */
		public Result go(Result prepare);
	}
	
	private final ConcurrentMap<String, Handle> handles;
	
	public HelpWordHandles() {
		handles = new ConcurrentHashMap<>();
	}
	
	public Result add(final Class<?> key, final Handle handle) {
		return Result.valueOf(handles.putIfAbsent(key.getName(), handle) == null);
	}
	
	public Result remove(final Class<?> key) {
		return Result.valueOf(handles.remove(key.getName()) != null);
	}
	
	public Handle get(final String key) {
		return handles.get(key);
	}
	
	public Result go(final Environment environment, final Class<?> key, final Result prepare) {
		final Handle handle = get(key.getName());
		if (handle == null) {
			return new Result(false, String.format("Failed to get word handle for key [%s].", key.getName()), null);
		} else {
			return handle.go(prepare);
		}
	}
}
