package org.iungo.cli.api;

import java.util.Map;

import org.iungo.id.api.ID;
import org.iungo.result.api.Result;

public interface Scope {

	static final String ID_ROOT = Scope.class.getName();
	
	static final ID VALUES = new ID(ID_ROOT, null, "Values");
	
	Block getBlock();

	Map<String, Value> getValues();
	
	Boolean has(String key);
	
	Result define(String key, Object value);
	
	<T> T get(String key);
	
	<T> T set(String key, T value);
}
