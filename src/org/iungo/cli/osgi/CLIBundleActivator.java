package org.iungo.cli.osgi;

import org.iungo.result.api.Result;
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
	
	public Result requireTrue(final Result result) {
		if (!result.getState()) {
			throw new UnsupportedOperationException(result.getText());
		}
		return result;
	}
}
