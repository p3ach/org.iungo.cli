package org.iungo.cli.api;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.iungo.result.api.Result;

/**
 * A list of words which can be executed.
 * 
 * @author Dick
 *
 */
public class Words implements Word, Iterable<Word> {
	
	public static final Words EMPTY = new Words(Collections.unmodifiableList(new LinkedList<Word>()));
	
	private final List<Word> words;

	public Words() {
		 this(new LinkedList<Word>());
	}
	
	protected Words(final List<Word> words) {
		this.words = words;
	}
	
	public Boolean add(final Word word) {
		return words.add(word);
	}
	
	public Boolean add(final Words words) {
		Boolean result = true;
		for (Word word : words) {
			result = add(word);
			if (!result) {
				break;
			}
		}
		return result;
	}

	public Integer getSize() {
		return words.size();
	}
	
	@Override
	public Iterator<Word> iterator() {
		return words.iterator();
	}
	
	/**
	 * Iterate the list of words executing each one in turn until all have been executed or false is returned.
	 */
	@Override
	public Result go(final Environment environment) {
		Result result = new Result(true, null, null);
		for (Word word : words) {
			result = word.go(environment);
			if (result.isTrue()) {
				break;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(1024);
		final Iterator<Word> iterator = words.iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next());
			while (iterator.hasNext()) {
				result.append(String.format("\n%s", iterator.next()));
			}
		}
		return result.toString();
	}
}
