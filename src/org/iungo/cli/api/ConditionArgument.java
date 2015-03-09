package org.iungo.cli.api;

public abstract class ConditionArgument implements Argument {

	protected final Argument condition;
	
	protected final ConditionBlock conditionBlock = new ConditionBlock();

	public ConditionArgument(final Argument condition) {
		super();
		this.condition = condition;
	}
	
	public Block getBlock() {
		return conditionBlock; 
	}
}
