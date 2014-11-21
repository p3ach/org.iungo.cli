package org.iungo.cli.api;

import java.util.List;

import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public interface Method {

	static final String ROOT_NS = Method.class.getName();
	
	static final String MAIN_METHOD_NAME = "main";
	
	String getName();
	
	List<String> getParameters();
	
	List<String> getLines();
	
	Block getBlock();
	
	Result go(Context context);
}
