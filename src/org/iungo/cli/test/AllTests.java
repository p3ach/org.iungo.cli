package org.iungo.cli.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SimpleSessionTest.class, TestCLI.class })
public class AllTests {

}
