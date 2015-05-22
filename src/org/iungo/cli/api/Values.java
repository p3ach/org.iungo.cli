package org.iungo.cli.api;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.iungo.result.api.Result;

/**
 * Values interface designed for strict define, set, get, undefine operations.
 * <p>The method define will fail if the K has already been defined. The methods set/get/undefine will fail if the K has not been defined.
 * <p>The Values.Default implementation is thread safe and caches the V as a Result<V> to aid methods which return the V as a Result<V>.  
 * @author dick
 *
 * @param <K>
 * @param <V>
 */
public interface Values {
	
	class Default implements Values {
		
		static class Value {

			private final Result v;
			
			public Value(final Object v) {
				super();
				this.v = new Result(true, null, v);
			}

			public Result get() {
				return v;
			}
			
			@Override
			public String toString() {
				return v.getValue().toString();
			}
		}

		private final ConcurrentMap<String, Value> values;
		
		public Default() {
			this(new ConcurrentHashMap<String, Value>());
		}
		
		public Default(final ConcurrentMap<String, Value> values) {
			super();
			this.values = values;
		}

		@Override
		public Result isDefined(final String key) {
			final Value value = values.get(key);
			if (value == null) {
				return Result.FALSE;
			}
			return value.get();
		}

		@Override
		public Result define(final String k, final Object v) {
			final Value value = new Value(v);
			if (values.putIfAbsent(k, value) == null) {
				return value.get();
			}
			return Result.FALSE;
		}

		@Override
		public Result undefine(final String k) {
			final Value value = values.remove(k);
			if (value == null) {
				return Result.FALSE;
			}
			return value.get();
		}

		@Override
		public Result get(final String k) {
			final Value value = values.get(k);
			if (value == null) {
				return Result.FALSE;
			}
			return value.get();
		}

		@Override
		public Result set(final String k, final Object v) {
			final Value value = new Value(v);
			if (values.replace(k, value) == null) {
				return Result.FALSE;
			}
			return value.get();
		}
		
	}
	
	/**
	 * If the given K is defined return the mapped V else false.
	 * @param key
	 * @return
	 */
	Result isDefined(String key);

	/**
	 * Define the given K mapped to V returning true and the mapped V if K is not already defined else return false.
	 * @param key
	 * @param value
	 * @return
	 */
	Result define(String key, Object value);
	
	/**
	 * Undefine the given K returning true and the mapped V if it was defined else return false.
	 * @param key
	 * @return
	 */
	Result undefine(String key);
	
	/**
	 * Get the mapped V for the given K returning true and the mapped V else return false.
	 * @param key
	 * @return
	 */
	Result get(String key);
	
	/**
	 * Set the mapped V for the given K returning true and the mapped V else return false if K is not defined.
	 * @param key
	 * @param value
	 * @return
	 */
	Result set(String key, Object value);
}
