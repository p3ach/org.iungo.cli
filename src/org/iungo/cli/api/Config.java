package org.iungo.cli.api;

import java.net.URL;
import java.util.List;

import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public interface Config {

	String getName();
	
	URL getURL();
	
	List<String> getLines();
	
	Result compile(Context context);
}