package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * 
 * @author Dick
 * 
 */
public class KeyValueWord implements Word {

	public static class KeyValueEntry {
	
		private final Object key;
		
		private final Object value;
		
		public KeyValueEntry(final Object key, final Object value) {
			super();
			this.key = key;
			this.value = value;
		}
	
		public Object getKey() {
			return key;
		}
	
		public Object getValue() {
			return value;
		}
	
	}

	private final Word key;
	
	private final Word value;

	public KeyValueWord(final Word key, final Word value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * Return a KeyValueEntry Result.
	 */
	@Override
	public Result go(Environment environment) {
		try {
			/*
			 * Check if this is permitted.
			 */
			Result result = environment.permit(this);
			if (result.isTrue()) {
				/*
				 * Get the String key.
				 */
				result = key.go(environment);
				if (result.isTrue()) {
					final String key = (String) result.getValue();
					/*
					 * Get the Object value.
					 */
					result = value.go(environment);
					if (result.isTrue()) {
						final Object value = result.getValue();
						/*
						 * Return KeyValueEntry Result.
						 */
						return new Result(true, null, new KeyValueEntry(key, value));
					}
				}
			}
			/*
			 * Return false result.
			 */
			return result;
		} catch (final Exception exception) {
			/*
			 * We died...
			 */
			return Result.valueOf(exception);
		}
	};
}
