package org.iungo.cli.api;


public interface Method extends Argument {

	static final String MAIN_METHOD_NAME = "main";
	
	String getName();
	
	MethodParameters getParameters();
	
	MethodLines getLines();

	Block getBlock();
}
