package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * 
 * @author Dick
 *
 */
public abstract class AbstractDefineValueWord implements Word {
	
	private final Word key;
	
	private final Word value;
	
	public AbstractDefineValueWord(final Word key, final Word value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	@Override
	public Result go(final Environment environment) {
		try {
			Result result = environment.permit(this);
			if (result.isTrue()) {
				/*
				 * Get the key.
				 */
				result = key.go(environment);
				if (result.isTrue()) {
					final String key = (String) result.getValue(); 
					/*
					 * Get the value.
					 */
					result = value.go(environment);
					if (result.isTrue()) {
						/*
						 * Define the key and value.
						 */
						result = define(environment, key, result.getValue());
					}
				}
			}
			/*
			 * Return result.
			 */
			return result;
		} catch (final Exception exception) {
			/*
			 * We died...
			 */
			return Result.valueOf(exception);
		}
	}
	
	/**
	 * Override to actually define the key and value.
	 * 
	 * @param executeEnvironment
	 * @param key
	 * @param value
	 * @return
	 */
	protected abstract Result define(Environment environment, String key, Object value);
}
