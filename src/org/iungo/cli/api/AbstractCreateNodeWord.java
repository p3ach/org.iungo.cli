package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * Create a Node with help from the ExecuteEnvironment.
 * <p>It is usually sufficient to extend this class and install a helper word handle with the class name.
 * 
 * @author Dick
 *
 * @param 
 */
public abstract class AbstractCreateNodeWord extends AbstractHelpedWord {

	private final NodeContextWord nodeContextWord;
	
	public AbstractCreateNodeWord(final NodeContextWord nodeContextWords) {
		super();
		this.nodeContextWord = nodeContextWords;
	}

	@Override
	public Result prepare(final Environment environment) {
		try {
			/*
			 * Return the result of executing the NodeContextWord.
			 */
			return nodeContextWord.go(environment);
		} catch (final Exception exception) {
			/*
			 * We died...
			 */
			return Result.valueOf(exception);
		}
	}
}
