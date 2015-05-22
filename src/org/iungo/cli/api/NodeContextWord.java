package org.iungo.cli.api;

import org.iungo.cli.api.KeyValueWord.KeyValueEntry;
import org.iungo.cli.api.KeyValueWords.KeyValueEntries;
import org.iungo.node.api.NodeContext;
import org.iungo.result.api.Result;

/**
 * Execute the given KeyValueWords and return a NodeContext.
 * 
 * @author dick
 *
 */
public class NodeContextWord implements Word {

	private final KeyValueWords keyValueWords;
	
	public NodeContextWord(final KeyValueWords keyValueWords) {
		super();
		this.keyValueWords = keyValueWords;
	}

	@Override
	public Result go(final Environment environment) {
		try {
			/*
			 * Check if this is permitted.
			 */
			Result result = environment.permit(this);
			if (result.isTrue()) {
				/*
				 * Execute the KeyValueWords.
				 */
				result = keyValueWords.go(environment);
				if (result.isTrue()) {
					/*
					 * Build the NodeContext.
					 */
					final NodeContext nodeContext = new NodeContext();
					for (KeyValueEntry keyValueEntry : (KeyValueEntries) result.getValue()) {
						nodeContext.put((String) keyValueEntry.getKey(), keyValueEntry.getValue());
					}
					result = new Result(true, null, nodeContext); 
				}
			}
			return result;
		} catch (final Exception exception) {
			/*
			 * We died...
			 */
			return Result.valueOf(exception);
		}
	}
}
