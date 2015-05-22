package org.iungo.cli.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.iungo.cli.api.CLI;
import org.iungo.cli.api.CallMainMethodArgument;
import org.iungo.cli.api.CallMethodWord;
import org.iungo.cli.api.Config;
import org.iungo.cli.api.ConfigUtils;
import org.iungo.cli.api.Environment;
import org.iungo.cli.api.Frames;
import org.iungo.cli.api.SimpleCLI;
import org.iungo.cli.api.SimpleMethod;
import org.iungo.cli.api.MethodWords;
import org.iungo.cli.api.Scopes;
import org.iungo.cli.api.Session;
import org.iungo.cli.api.SimpleSession;
import org.iungo.cli.api.Unit;
import org.iungo.cli.implementation.StringLiteralArgument;
import org.iungo.logger.api.ClassLogger;
import org.iungo.logger.api.Logger;
import org.iungo.logger.api.Loggers;
import org.iungo.result.api.Result;
import org.junit.Test;

public class TestCLI {

	private static final ClassLogger logger = Loggers.valueOf(TestCLI.class);
	
	@Test
	public void test() throws MalformedURLException {
		
		
		Result result = ConfigUtils.instance.openFromURL("hello-world", new URL("file:///home/dick/workspace/org.iungo.cli/src/org/iungo/cli/test/hello-world.config"));
//		System.out.println(result);
		
		Config config = result.getValue();
//		System.out.println(config);
		
		result = config.parse();
		System.out.println(result);

		Unit unit = result.getValue();
//		System.out.println(unit);
		
		final CLI cli = new SimpleCLI();
		cli.getConfigs().add(config);
		cli.compile(config.getName());
		
		Environment executeEnvironment = new Environment();
		logger.info(executeEnvironment.toString());
		
		Loggers.getInstance().setLevel(Environment.class.getName(), Logger.DEBUG);
//		Loggers.getInstance().setLevel(SimpleMethod.class.getName(), Logger.DEBUG);
//		Loggers.getInstance().setLevel(Frames.class.getName(), Logger.DEBUG);
//		Loggers.getInstance().setLevel(Scopes.class.getName(), Logger.DEBUG);
//		Loggers.getInstance().setLevel(CallMethodArgument.class.getName(), Logger.DEBUG);
		executeEnvironment.getUnits().add(unit);
		logger.info(executeEnvironment.toString());

		CallMethodWord callMethodArgument = new CallMethodWord(new StringLiteralArgument("hello world"), new StringLiteralArgument("hello world"), new MethodWords());
		executeEnvironment.execute(callMethodArgument);
		logger.info(executeEnvironment.toString());
		
//		CallMainMethodArgument callMainMethodArgument = new CallMainMethodArgument(new StringLiteralArgument("hello world"), new MethodArguments());
//		executeEnvironment.execute(callMainMethodArgument);
		
//		CallMethodArgument callMethodArgument = new CallMethodArgument(new StringLiteralArgument("hello world"), new StringLiteralArgument("m1"), new MethodArguments());
//		result = callMethodArgument.execute(executeEnvironment);
//		System.out.println(result);

//		callMethodArgument = new CallMethodArgument(new StringLiteralArgument("hello world"), new StringLiteralArgument("m2"), new MethodArguments());
//		result = callMethodArgument.execute(executeEnvironment);
//		System.out.println(result);
		
//		callMethodArgument = new CallMethodArgument(new StringLiteralArgument("hello world"), new StringLiteralArgument("m3"), new MethodArguments());
//		result = callMethodArgument.execute(executeEnvironment);
//		System.out.println(result);
		
//		result = cli.parse("define key \"name\" value 0");
//		System.out.println(result);
//		
//		final Session session = new SimpleSession();
//		Loggers.getInstance().setLevel(SimpleSession.class.getName(), Logger.DEBUG);
//		result = session.execute("define key \"name\" value \"H\"");
//		System.out.println(result);
//		
//		result = session.execute("get key \"name\"");
//		System.out.println(result);
//
//		result = session.execute("define key \"wife\" value get key \"name\"");
//		System.out.println(result);
//
//		result = session.execute("set key \"name\" value \"hello world\"");
//		result = session.execute("define key \"url\" value \"file:///home/dick/workspace/org.iungo.cli/src/org/iungo/cli/test/hello-world.config\"");
//		result = session.execute("open config name get key \"name\" from url get key \"url\"");
//		
//		result = session.execute("parse config name get key \"name\"");
		
//		System.out.println(result);
	}

}
