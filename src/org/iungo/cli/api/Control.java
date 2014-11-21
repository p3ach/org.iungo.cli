package org.iungo.cli.api;

public interface Control {

	static final String NS = Control.class.getName();
	
	void pushScope(Scope scope);
	
	Scope popScope();
	
	Scope peepScope();
	
	void defineValue(String key, Object value);
	
	/**
	 * Get the value for the given key.
	 * @param key The key identifying the required value.
	 * @return The value cast to T.
	 */
	<T> T getValue(String key);
	
	/**
	 * Set the value for the given key. Throw an Exception if the key is not declared.
	 * @param key The key identifying the value to be set.
	 * @param value The new value.
	 */
	void setValue(String key, Object value);
}
