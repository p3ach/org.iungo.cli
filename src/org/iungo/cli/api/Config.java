package org.iungo.cli.api;

import java.util.List;

import org.iungo.result.api.Result;

public interface Config {

	String getName();
	
	List<String> getLines();
	
	Result compile();
}