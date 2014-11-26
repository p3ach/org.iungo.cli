package org.iungo.cli.api;


public interface Value {
	
	<T> T getValue();
	
	<T> T setValue(T value);
}
