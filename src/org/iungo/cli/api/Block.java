package org.iungo.cli.api;

import java.util.Iterator;

import org.iungo.result.api.AggregateResult;
import org.iungo.result.api.Result;

public class Block extends Arguments {

	public static final String ROOT_NS = Block.class.getName();
	
	protected Scope createScope(final ExecuteEnvironment executeEnvironment) {
		return new BlockScope(this);
	}
	
	/**
	 * Create the Scope for this Block and push it onto the Stack.
	 * <p>Override in subclasses to create a specific Scope.
	 * @return
	 */
	protected Scope pushScope(final ExecuteEnvironment executeEnvironment) {
		final Scope scope = createScope(executeEnvironment);
		executeEnvironment.getFames().peek().getScopes().push(scope);
		return scope;
	}

	/**
	 * Pop the Scope for this Block from the Stack.
	 * @param executeEnvironment
	 */
	protected void popScope(final ExecuteEnvironment executeEnvironment) {
		executeEnvironment.getFames().peek().getScopes().pop();
	}
	
	/**
	 * Get the Arguments which by default is this (as we extend Arguments);
	 * <p>Override to return other Arguments e.g. CallMethodBlock will return method.getBlock();
	 * @return
	 */
	protected Arguments getArguments() {
		return this;
	}
	
	protected void checkFlow() {
	}

	/**
	 * Execute this block by iterating and executing each Argument.
	 */
	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		pushScope(executeEnvironment);
		try {
			executeEnvironment.getFlowLifecycle().setNormal();
			Integer index = 0;
			AggregateResult result = new AggregateResult();
			final Iterator<Argument> iterator = getArguments().iterator();
			while (iterator.hasNext()) {
				Argument argument = iterator.next();
				index++;
				result.add(executeEnvironment.execute(argument));
				if (result.isFalse()) {
					executeEnvironment.getFlowLifecycle().setException();
					return result;
				}
				if (!executeEnvironment.getFlowLifecycle().isState(FlowLifecycle.NORMAL)) {
					return result;
				}
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		} finally {
			popScope(executeEnvironment);
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
