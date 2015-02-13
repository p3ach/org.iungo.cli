package org.iungo.cli.implementation;

import java.util.Stack;

import org.iungo.cli.api.Block;
import org.iungo.cli.api.CLI;
import org.iungo.cli.api.Configs;
import org.iungo.cli.api.Control;
import org.iungo.cli.api.Units;
import org.iungo.context.api.Context;
import org.iungo.context.api.ContextGo;

public class CLIContext {
	
	protected final Context context;

	public CLIContext(final Context context) {
		super();
		this.context = context;
	}
	
	/*
	 * Scope methods.
	 */
	
	public Control getControl() {
		return context.get(CLI.CONTROL.getID(), new ContextGo() {
			@Override
			public Object go(final Context context) {
				Control control = new DefaultControl();
				context.put(CLI.CONTROL.getID(), control);
				return (control);
			}
		});
	}
	
	/*
	 * Block methods.
	 */
	
	public Block peekBlock() {
		Stack<Block> stack = context.get(CLI.FLOW_CONTROL_BLOCKS.getID());
		return stack.peek();
	}

	public void pushBlock(final Block block) {
		Stack<Block> stack = context.get(CLI.FLOW_CONTROL_BLOCKS.getID());
		stack.push(block);
	}
	
	public Block popBlock() {
		Stack<Block> stack = context.get(CLI.FLOW_CONTROL_BLOCKS.getID());
		return stack.pop();
	}
	
	/*
	 * Config methods.
	 */
	
	public Configs getConfigs() {
		return context.get(CLI.CONFIGS.getID(), new ContextGo() {
			@Override
			public Object go(final Context context) {
				Configs created = new ConcurrentHashMapConfigs();
				Configs current = context.putIfAbsent(CLI.CONFIGS.getID(), created);
				return (current == null ? created : current);
			}
		});
	}
	
	/*
	 * Unit methods.
	 */
	
	public Units getUnits() {
		return context.get(CLI.UNITS.getID(), new ContextGo() {
			@Override
			public Object go(final Context context) {
				Units created = new Units();
				Units current = context.putIfAbsent(CLI.UNITS.getID(), created);
				return (current == null ? created : current);
			}
		});
	}
}
