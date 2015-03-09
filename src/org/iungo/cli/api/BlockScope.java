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
}
