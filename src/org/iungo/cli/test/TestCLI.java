package org.iungo.cli.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.iungo.cli.api.CallMethodArgument;
import org.iungo.cli.api.Config;
import org.iungo.cli.api.ConfigUtils;
import org.iungo.cli.api.ExecuteEnvironment;
import org.iungo.cli.api.Method;
import org.iungo.cli.api.MethodArguments;
import org.iungo.cli.api.Scopes;
import org.iungo.cli.api.Unit;
import org.iungo.cli.implementation.StringLiteralArgument;
import org.iungo.logger.api.Logger;
import org.iungo.logger.api.Loggers;
import org.iungo.result.api.Result;
import org.junit.Test;

public class TestCLI {
	
	@Test
	public void test() throws MalformedURLException {
		Result result = ConfigUtils.instance.open("hello-world", new URL("file:///home/dick/workspace/org.iungo.cli/src/org/iungo/cli/test/hello-world.config"));
//		System.out.println(result);
		
		Config config = result.getValue();
//		System.out.println(config);
		
		result = config.compile();
		System.out.println(result);

		Unit unit = result.getValue();
//		System.out.println(unit);
		
		ExecuteEnvironment executeEnvironment = new ExecuteEnvironment();
		Loggers.getInstance().setLevel(ExecuteEnvironment.class.getName(), Logger.DEBUG);
		Loggers.getInstance().setLevel(Method.class.getName(), Logger.DEBUG);
		Loggers.getInstance().setLevel(Scopes.class.getName(), Logger.DEBUG);
		Loggers.getInstance().setLevel(CallMethodArgument.class.getName(), Logger.DEBUG);
		executeEnvironment.getUnits().add(unit);
		System.out.println(executeEnvironment);

		CallMethodArgument callMethodArgument = new CallMethodArgument(new StringLiteralArgument("hello world"), new StringLiteralArgument("m1"), new MethodArguments());
		result = callMethodArgument.execute(executeEnvironment);
		System.out.println(result);

		callMethodArgument = new CallMethodArgument(new StringLiteralArgument("hello world"), new StringLiteralArgument("m2"), new MethodArguments());
		result = callMethodArgument.execute(executeEnvironment);
		System.out.println(result);
		
		callMethodArgument = new CallMethodArgument(new StringLiteralArgument("hello world"), new StringLiteralArgument("m3"), new MethodArguments());
		result = callMethodArgument.execute(executeEnvironment);
		System.out.println(result);

	}

}
