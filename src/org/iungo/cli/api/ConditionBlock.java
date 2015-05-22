package org.iungo.cli.api;


public class ConditionBlock extends Block {

	public ConditionBlock() {
		super();
	}

	@Override
	protected Scope createScope(final Environment executeEnvironment) {
		return new ConditionScope(this);
	}
}
