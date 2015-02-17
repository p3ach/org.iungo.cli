package org.iungo.cli.api;

public class ExecuteEnvironmentScope extends BlockScope {

	public ExecuteEnvironmentScope() {
		super(null);
	}

	@Override
	public Block getBlock() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return String.format("%s [\n%s\n]", ExecuteEnvironmentScope.class.getName(), super.toString());
	}
}
