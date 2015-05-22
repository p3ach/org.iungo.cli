package org.iungo.cli.test;

import static org.junit.Assert.*;

import org.iungo.cli.api.Environment;
import org.iungo.cli.api.LiteralWord;
import org.iungo.cli.api.StringLiteralWord;
import org.junit.Test;

public class LiteralWordTest {

	@Test
	public void test() {
		final Environment executeEnvironment = new Environment();
		
		final LiteralWord<Void> voidLiteralWord = new LiteralWord<Void>(null);
		voidLiteralWord.go(executeEnvironment);
		
		final LiteralWord<Integer> integerLiteralWord = new LiteralWord<Integer>(0);
		integerLiteralWord.go(executeEnvironment);

		final String FOO = "foo";
		final StringLiteralWord stringLiteralWord = new StringLiteralWord(FOO);
		assertEquals(stringLiteralWord.go(executeEnvironment).getValue(), FOO);
	}

}
