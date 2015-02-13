package org.iungo.cli.api;

import java.util.Iterator;

import org.iungo.result.api.Result;

public class Block extends Arguments {

	public static final String ROOT_NS = Block.class.getName();
	
	public static final Integer NORMAL_FLOW_CONTROL = 0;
	
	public static final Integer BREAK_FLOW_CONTROL = 8;

	public static final Integer CONTINUE_FLOW_CONTROL = 16;

	public static final Integer EXCEPTION_FLOW_CONTROL = 24;
	
	public static final Integer RETURN_FLOW_CONTROL = 32;

	protected Integer flowControl = NORMAL_FLOW_CONTROL;

	public void setBreak() {
		flowControl = BREAK_FLOW_CONTROL;
	}

	public void setContinue() {
		flowControl = CONTINUE_FLOW_CONTROL;
	}

	public void setReturn() {
		flowControl = RETURN_FLOW_CONTROL;
	}
	
	/**
	 * Create the Scope for this Block.
	 * Override in subclasses to create a specific Scope.
	 * @return
	 */
	protected Scope createScope(final ExecuteEnvironment executeEnvironment) {
		return new BlockScope(this);
	}
	
	/**
	 * Get the Arguments which by default is this (as we extend Arguments);
	 * <p>Override to return other Arguments e.g. CallMethodBlock will return method.getBlock();
	 * @return
	 */
	protected Arguments getArguments() {
		return this;
	}
	
	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		executeEnvironment.getScopes().push(createScope(executeEnvironment));
		try {
			Integer index = 0;
			final Iterator<Argument> iterator = getArguments().iterator();
			while (iterator.hasNext()) {
				Argument argument = iterator.next();
				index++;
				Result result = argument.execute(executeEnvironment);
				if (!result.getState()) {
					flowControl = EXCEPTION_FLOW_CONTROL;
					return result;
				}
				if (flowControl != NORMAL_FLOW_CONTROL) {
					return result;
				}
			}
			return new Result(true, null, index);
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		} finally {
			executeEnvironment.getScopes().pop();
		}
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(4086);
		Integer index = 0;
		final Iterator<Argument> iterator = this.iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next());
			while (iterator.hasNext()) {
				result.append(String.format("\n%s", iterator.next()));
			}
		}
		return result.toString();
	}

}
