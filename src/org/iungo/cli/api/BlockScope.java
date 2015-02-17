package org.iungo.cli.api;


public class BlockScope extends AbstractScope {
	
	private final Block block;
	
	public BlockScope(final Block block) {
		super();
		this.block = block;
	}

	@Override
	public Block getBlock() {
		return block;
	}

	@Override
	public String toString() {
		return String.format("%s [\n%s\n]", BlockScope.class.getName(), super.toString());
	}
}
