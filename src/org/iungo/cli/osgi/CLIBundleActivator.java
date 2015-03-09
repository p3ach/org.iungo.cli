package org.iungo.cli.osgi;

import java.net.URL;

import org.iungo.cli.api.CLI;
import org.iungo.cli.api.CallMethodArgument;
import org.iungo.cli.api.Config;
import org.iungo.cli.api.LiteralArgument;
import org.iungo.cli.api.MethodArguments;
import org.iungo.cli.api.SimpleMethod;
import org.iungo.cli.implementation.SimpleCLI;
import org.iungo.cli.implementation.DefaultValues;
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
		
		final CLI cli = new SimpleCLI();
		cli.openFromURL("hello-world", new URL("file:///home/dick/workspace/org.iungo.cli/src/org/iungo/cli/test/hello-world.config"));
		final Config config = cli.get("hello-world");
		final Context context = ((ContextAPI) getAPI(ContextAPI.class)).createContext();
		Result result = config.compile(context);
		System.out.println(result);

		CallMethodArgument callMethodArgument = new CallMethodArgument(new LiteralArgument("hello world"), new LiteralArgument(SimpleMethod.MAIN_METHOD_NAME), new MethodArguments());
		callMethodArgument.execute(context);
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
	
	public Result requireTrue(final Result result) {
		if (!result.getState()) {
			throw new UnsupportedOperationException(result.getText());
		}
		return result;
	}
}
