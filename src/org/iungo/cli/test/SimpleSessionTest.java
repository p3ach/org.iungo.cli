package org.iungo.cli.test;

import static org.junit.Assert.*;

import org.iungo.cli.api.SimpleSession;
import org.iungo.logger.api.Loggers;
import org.iungo.result.api.Result;
import org.junit.Test;

public class SimpleSessionTest {

	@Test
	public void test() {
		final SimpleSession simpleSession = new SimpleSession();
		
		System.out.println(simpleSession.go("define key \"foo\" value \"bar\"").getValue());

		System.out.println(simpleSession.go("get key \"foo\"").getValue());
		
		System.out.println(simpleSession.go("set key \"foo\" value \"hello world\"").getValue());

		System.out.println(simpleSession.go("get key \"foo\"").getValue());
		
	}

}
