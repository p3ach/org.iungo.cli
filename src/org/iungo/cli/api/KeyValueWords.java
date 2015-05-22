package org.iungo.cli.api;

import java.util.LinkedList;
import java.util.List;

import org.iungo.cli.api.KeyValueWord.KeyValueEntry;
import org.iungo.result.api.Result;

/**
 * 
 * @author Dick
 *
 */
public class KeyValueWords implements Word {

	public static class KeyValueEntries extends LinkedList<KeyValueEntry> {
	
		private static final long serialVersionUID = 1L;
	}
	
	private final List<KeyValueWord> words = new LinkedList<KeyValueWord>();

	public void add(final KeyValueWord keyValueWord) {
		words.add(keyValueWord);
	}

	/**
	 * Return a List of KeyValueEntry.
	 */
	@Override
	public Result go(final Environment environment) {
		try {
			/*
			 * Check this is permitted.
			 */
			Result result = environment.permit(this);
			if (result.isTrue()) {
				final KeyValueEntries entries = new KeyValueEntries();
				for (KeyValueWord keyValueWord : words) {
					result = keyValueWord.go(environment);
					if (result.isTrue()) {
						entries.add((KeyValueEntry) result.getValue());
						continue;
					}
					return result;
				}
				result = new Result(true, null, entries);
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}
}
