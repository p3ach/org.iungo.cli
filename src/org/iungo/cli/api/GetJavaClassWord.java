package org.iungo.cli.api;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.iungo.common.api.ClassUtils;
import org.iungo.id.api.ID;
import org.iungo.message.api.Message;
import org.iungo.node.api.Node;
import org.iungo.result.api.Result;
import org.iungo.util.concurrent.api.Cache;
import org.iungo.util.concurrent.api.WeakValueConcurrentCache;

/**
 * Get the Java Class for the given identifier.
 * 
 * @author dick
 *
 */
public class GetJavaClassWord implements Word {

	// TODO Make the cache ThreadLocal..?
	private static final Cache<String, Class<?>> cache = new WeakValueConcurrentCache<>();
	
	static {
		cache.add(Boolean.TYPE.getName(), Boolean.TYPE);
		cache.add(Integer.TYPE.getName(), Integer.TYPE);
		cache.add(Long.TYPE.getName(), Long.TYPE);

		cache.add(Void.class.getName(), Void.class);
		
		cache.add(Boolean.class.getName(), Boolean.class);
		cache.add(Integer.class.getName(), Integer.class);
		cache.add(Long.class.getName(), Long.class);
		cache.add(String.class.getName(), String.class);

		cache.add(ID.class.getName(), ID.class);
		cache.add(Message.class.getName(), Message.class);
		cache.add(Node.class.getName(), Node.class);
	}
	
	private static final Cache.Go<String, Class<?>> GO = new Cache.Go<String, Class<?>>() {
		@Override
		public Class<?> get(final String key) {
			try {
				return ClassUtils.forName(key);
			} catch (final Exception exception) {
				return null;
			}
		}
	}; 
	
	private final Word c;

	public GetJavaClassWord(final Word c) {
		super();
		this.c = c;
	}

	@Override
	public Result go(final Environment executeEnvironment) {
		try {
			Result result = executeEnvironment.execute(c);
			if (result.isTrue()) {
				final String n = result.getValue();
				final Class<?> c = cache.get(n, GO);
				result = (c == null ? new Result(false, null, null) : new Result(true, c.toString(), c));
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	};
}
