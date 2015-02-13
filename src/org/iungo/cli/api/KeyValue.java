package org.iungo.cli.api;

public interface KeyValue {

	Argument getKey();
	
	Argument setKey(Argument key);
	
	Argument getValue();
	
	Argument setValue(Argument value);
}
