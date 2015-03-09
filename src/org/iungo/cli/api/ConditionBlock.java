package org.iungo.cli.api;


public class ConditionBlock extends Block {

	public ConditionBlock() {
		super();
	}

	@Override
	protected Scope createScope(final ExecuteEnvironment executeEnvironment) {
		return new ConditionScope(this);
	}
}
