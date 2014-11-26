package org.iungo.cli.osgi;

import java.net.URL;

import org.iungo.cli.api.CLI;
import org.iungo.cli.api.Config;
import org.iungo.cli.api.Method;
import org.iungo.cli.implementation.CallMethodArgument;
import org.iungo.cli.implementation.DefaultCLI;
import org.iungo.cli.implementation.DefaultMethodArguments;
import org.iungo.cli.implementation.DefaultValues;
import org.iungo.cli.implementation.LiteralArgument;
import org.iungo.context.api.Context;
import org.iungo.context.api.ContextAPI;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class CLIBundleActivator implements BundleActivator {

	protected static volatile CLIBundleActivator instance = null;
	
	public static CLIBundleActivator getInstance() {
		return instance;
	}
	
	protected volatile BundleContext bundleContext = null;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(final BundleContext bundleContext) throws Exception {
		this.bundleContext = bundleContext;
		instance = this;
		
		final CLI cli = new DefaultCLI();
		cli.openConfig("hello-world", new URL("file:///home/dick/workspace/org.iungo.cli/src/org/iungo/cli/test/hello-world.config"));
		final Config config = cli.getConfig("hello-world");
		final Context context = ((ContextAPI) getAPI(ContextAPI.class)).createContext();
		Result result = config.compile(context);
		System.out.println(result);

		CallMethodArgument callMethodArgument = new CallMethodArgument(new LiteralArgument("hello world"), new LiteralArgument(Method.MAIN_METHOD_NAME), new DefaultMethodArguments());
		callMethodArgument.go(context);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(final BundleContext bundleContext) throws Exception {
		instance = null;
		this.bundleContext = null;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAPI(final Class<?> c) {
		return (T) bundleContext.getService(bundleContext.getServiceReference(c));
	}
	
	public Result createResult(final Boolean state, final String text, final Object value) {
		return ((ResultAPI) getAPI(ResultAPI.class)).create(state, text, value);
	}
}
