package org.iungo.cli.api;

import java.util.Stack;

public interface Environment {
	
	Block peekBlock();
	
	void pushBlock(Block block);	
	
	Block popBlock();
}
