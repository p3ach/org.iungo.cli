package org.iungo.cli.api;

import java.util.Iterator;

import org.iungo.result.api.Result;

/**
 * A Block extends Words with the concept of a Frame and Scope.
 * <p>By default when execute(ExecuteEnvironment) is called it will call pushScope(ExecuteEnvironment) and popScope(ExecuteEnvironment).
 * This will push/pop a Scope on the current Scopes of the current Frame.
 * <p>Sub classes which need a new Frame need to call pushFrame(ExecuteEnvironment) and popFrame(ExecuteEnvironment) typically from pushScope(ExecuteEnvironment) and popScope(ExecuteEnvironment)
 * as these methods are called from execute(ExecuteEnvironment) with a try.
 * @author dick
 *
 */
public class Block extends Words {

	public static final String ROOT_NS = Block.class.getName();

	/**
	 * Create a Block from the given Words.
	 * @param word
	 * @return
	 */
	public static  Block valueOf(final Word word) {
		final Block block = new Block();
		block.add(word);
		return block;
	}

	public Block() {
		super();
	}

	/**
	 * Create a new Frame which by default is new Frame().
	 * @param environment
	 * @return The new Frame.
	 */
	protected Frame createFrame(final Environment environment) {
		return new Frame();
	}

	/**
	 * Create a new Frame for this Block and push it onto the Frames stack.
	 * <p>The ExecuteEnvironmentScope is pushed onto the Frame Scopes stack.
	 * @param environment
	 * @return The Frame pushed onto the stack.
	 */
	protected Frame pushFrame(final Environment environment) {
		final Frame frame = createFrame(environment);
		frame.getScopes().push(environment.getEnvironmentScope());
		environment.getFrames().push(frame);
		return frame;
	}
	
	/**
	 * Pop the last Frame from the Frames stack.
	 * @param environment
	 */
	protected void popFrame(final Environment environment) {
		environment.getFrames().pop();
	}

	/**
	 * Create a new Scope which by default is new BlockScope().
	 * @param executeEnvironment
	 * @return The new BlockScope.
	 */
	protected Scope createScope(final Environment executeEnvironment) {
		return new BlockScope(this);
	}
	
	/**
	 * Create a new Scope for this Block and push it onto the Stack.
	 * <p>Override in subclasses to create a specific Scope.
	 * @return
	 */
	protected Scope pushScope(final Environment executeEnvironment) {
		final Scope scope = createScope(executeEnvironment);
		executeEnvironment.getFrames().peek().getScopes().push(scope);
		return scope;
	}

	/**
	 * Pop the last Scope for this Block from the Stack.
	 * @param executeEnvironment
	 */
	protected void popScope(final Environment executeEnvironment) {
		executeEnvironment.getFrames().peek().getScopes().pop();
	}
	
	/**
	 * Get the words which by default is this (as we extend Words);
	 * <p>Override to return other words e.g. CallMethodBlock will return method.getBlock();
	 * @return
	 */
	protected Words getWords() {
		return this;
	}
	
	protected void checkFlow() {
	}

	/**
	 * Execute this block executing each Word.
	 * <p>NB The Result returned by the last Word executed will be the Result returned.
	 * As such use ReturnWord to return a specific Result.  
	 * @return Result
	 */
	@Override
	public Result go(final Environment environment) {
		/*
		 * Push a new scope.
		 */
		pushScope(environment);
		try {
//			executeEnvironment.getFlowLifecycle().setNormal();
			/*
			 * Default the result to true in case we don't have any words.
			 */
			Result result = environment.permit(this);
			if (result.isTrue()) {
				Integer index = 0;
				for (Word word : getWords()) {
					index++;
					/*
					 * Execute the word and check the result.
					 */
					result = word.go(environment);
					if (result.isTrue()) {
						/*
						 * Continue to the next word.
						 */
						continue;
					}
					/*
					 * The execute returned false so break and return this result.
					 */
					break;
				}
			}
			/*
			 * Return the result.
			 */
			return result;
		} catch (final Exception exception) {
			/*
			 * We died...
			 */
//			executeEnvironment.getFlowLifecycle().setException();
			return Result.valueOf(exception);
		} finally {
			/*
			 * Pop the scope we pushed earlier.
			 */
			popScope(environment);
		}
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(4086);
		final Iterator<Word> iterator = this.iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next());
			while (iterator.hasNext()) {
				result.append(String.format("\n%s", iterator.next()));
			}
		}
		return result.toString();
	}

}
