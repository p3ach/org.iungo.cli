package org.iungo.cli.api;

public abstract class ConditionArgument implements Word {

	protected final Word condition;
	
	protected final ConditionBlock conditionBlock = new ConditionBlock();

	public ConditionArgument(final Word condition) {
		super();
		this.condition = condition;
	}
	
	public Block getBlock() {
		return conditionBlock; 
	}
}
