package org.iungo.cli.api;

public interface Block extends Arguments {

	static final String ROOT_NS = Block.class.getName();
	
	static final Integer NORMAL_FLOW_CONTROL = 0;
	
	static final Integer BREAK_FLOW_CONTROL = 8;

	static final Integer CONTINUE_FLOW_CONTROL = 16;

	static final Integer EXCEPTION_FLOW_CONTROL = 24;
	
	static final Integer RETURN_FLOW_CONTROL = 32;
	
	void setBreak();
	
	void setContinue();
	
	void setReturn();
}
