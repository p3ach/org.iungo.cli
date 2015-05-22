package org.iungo.cli.api;

import org.iungo.result.api.Result;

public class CachedWord implements Word {

	private final Word word;
	
	private Result result = null;
	
	public CachedWord(final Word word) {
		this.word = word;
	}

	@Override
	public Result go(final Environment executeEnvironment) {
		if (result == null) {
			result = executeEnvironment.execute(word);
		}
		return result;
	}

}
