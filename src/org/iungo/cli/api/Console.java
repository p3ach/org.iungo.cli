package org.iungo.cli.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.iungo.common.api.DaemonThreadFactory;
import org.iungo.logger.api.Logger;
import org.iungo.logger.api.Loggers;
import org.iungo.result.api.Result;

public class Console implements Runnable {

	private static final Logger logger = Loggers.create(Console.class.getName());
	
	private static final Console instance = new Console();
	
	public synchronized static Console getInstance() {
		return instance;
	}
	
	private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	private volatile Session session;

	public Console() {
		super();
		DaemonThreadFactory.valueOf(this, Console.class.getName()).start();
	}

	public Session getSession() {
		return session;
	}
	
	public synchronized void setSession(final Session session) {
		this.session = session;
	}
	
	/**
	 * We catch Throwable!
	 * <p>As the Console may be the only way of getting the system closed cleanly we catch Throwable, log it as a warn and try to get more text input.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				String text = in.readLine();
				logger.info(session.execute(text).toString());
			} catch (final Throwable throwable) {
				logger.warn(Result.valueOf(throwable).toString());;
			}
		}
	}
}
