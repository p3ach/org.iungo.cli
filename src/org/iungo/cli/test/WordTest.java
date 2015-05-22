package org.iungo.cli.test;

import static org.junit.Assert.*;

import org.iungo.cli.api.Environment;
import org.iungo.cli.api.Word;
import org.iungo.result.api.Result;
import org.junit.Test;

public class WordTest {

	@Test
	public void test() {
		final Word<Integer> word = new Word<Integer>() {
			@Override
			public Result<? extends Integer> go(Environment executeEnvironment) {
				return new Result<Integer>(true, null, 0);
			}
		};
		word.go(null);
	}

}
