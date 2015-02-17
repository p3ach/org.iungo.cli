package org.iungo.cli.api;

public class AdhocMethodBlock extends MethodBlock {

	public AdhocMethodBlock(final Method method) {
		super(method);
	}

	/**
	 * Peek the current Scope and return it.
	 * <p>That is the last two Scope objects on the Scopes deque will be the same.
	 */
	@Override
	protected Scope createScope(final ExecuteEnvironment executeEnvironment) {
		return executeEnvironment.getScopes().peek();
	}

	/**
	 * Return the MethodBlock associated with the given Method used to construct this AdhocMethodBlock.
	 */
	@Override
	protected Arguments getArguments() {
		return getMethod().getBlock();
	}

	@Override
	public String toString() {
		return String.format("%s [\n%s\n]", AdhocMethodBlock.class.getName(), super.toString());
	}
}
