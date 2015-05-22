package org.iungo.cli.api;

public class EnvironmentBlock extends Block {

	private final Block block;
	
	public EnvironmentBlock(final Block block) {
		super();
		this.block = block;
	}

	@Override
	protected Words getWords() {
		return block;
	}

	@Override
	protected Scope createScope(final Environment environment) {
		return environment.getEnvironmentScope();
	}

	@Override
	protected Scope pushScope(final Environment environment) {
		pushFrame(environment);
		return super.pushScope(environment);
	}

	@Override
	protected void popScope(final Environment environment) {
		super.popScope(environment);
		popFrame(environment);
	}
}
