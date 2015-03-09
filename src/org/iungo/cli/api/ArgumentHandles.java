package org.iungo.cli.api;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.iungo.result.api.Result;

public class ArgumentHandles {

	private final ConcurrentMap<String, ArgumentHandle> handles = new ConcurrentHashMap<>();
	
	public Result add(final String key, final ArgumentHandle argumentHandle) {
		return Result.valueOf(handles.putIfAbsent(key, argumentHandle) == null);
	}
	
	public ArgumentHandle get(final String key) {
		return handles.get(key);
	}
}
