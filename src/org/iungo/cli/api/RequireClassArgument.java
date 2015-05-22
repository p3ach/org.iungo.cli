package org.iungo.cli.api;

import org.iungo.id.api.ID;
import org.iungo.message.api.Message;
import org.iungo.node.api.Node;
import org.iungo.result.api.Result;

/**
 * 
 * Test that the given c argument is assignable for the given a.
 *  
 * @author Dick
 *
 */
public class RequireClassArgument implements Word {

	/*
	 * java.lang.* convenience arguments.
	 */
	
	/**
	 * Test that java.lang.Boolean is assignable for the given a.
	 * 
	 * @author Dick
	 *
	 */
	public static class RequireBooleanClassArgument extends RequireClassArgument {
		public static final Result result = new Result(true, null, Boolean.class);
		
		public RequireBooleanClassArgument(final Word a) {
			super(new Word() {
				@Override
				public Result go(final Environment executeEnvironment) {
					return result;
				}
			}, a);
		}
	}

	/**
	 * Test that java.lang.String is assignable for the given a.
	 * 
	 * @author Dick
	 *
	 */
	public static class RequireStringClassArgument extends RequireClassArgument {
		public static final Result result = new Result(true, null, String.class);
		
		public RequireStringClassArgument(final Word a) {
			super(new Word() {
				@Override
				public Result go(final Environment executeEnvironment) {
					return result;
				}
			}, a);
		}
	}

	/**
	 * Test that java.lang.Integer is assignable for the given a.
	 * 
	 * @author Dick
	 *
	 */
	public static class RequireIntegerClassArgument extends RequireClassArgument {
		public static final Result result = new Result(true, null, Integer.class);
		
		public RequireIntegerClassArgument(final Word a) {
			super(new Word() {
				@Override
				public Result go(final Environment executeEnvironment) {
					return result;
				}
			}, a);
		}
	}
	
	/**
	 * Test that java.lang.Long is assignable for the given a.
	 * 
	 * @author Dick
	 *
	 */
	public static class RequireLongClassArgument extends RequireClassArgument {
		public static final Result result = new Result(true, null, Long.class);
		
		public RequireLongClassArgument(final Word a) {
			super(new Word() {
				@Override
				public Result go(final Environment executeEnvironment) {
					return result;
				}
			}, a);
		}
	}

	/*
	 * org.iungo.* convenience arguments.
	 */

	/**
	 * Test that org.iungo.id.api.ID is assignable for the given a.
	 * 
	 * @author Dick
	 *
	 */
	public static class RequireIDClassArgument extends RequireClassArgument {
		public static final Result result = new Result(true, null, ID.class);
		
		public RequireIDClassArgument(final Word a) {
			super(new Word() {
				@Override
				public Result go(final Environment executeEnvironment) {
					return result;
				}
			}, a);
		}
	}

	/**
	 * Test that org.iungo.message.api.Message is assignable for the given a.
	 * 
	 * @author Dick
	 *
	 */
	public static class RequireMessageClassArgument extends RequireClassArgument {
		public static final Result result = new Result(true, null, Message.class);
		
		public RequireMessageClassArgument(final Word a) {
			super(new Word() {
				@Override
				public Result go(final Environment executeEnvironment) {
					return result;
				}
			}, a);
		}
	}

	/**
	 * Test that org.iungo.node.api.Node is assignable for the given a.
	 * 
	 * @author Dick
	 *
	 */
	public static class RequireNodeClassArgument extends RequireClassArgument {
		public static final Result result = new Result(true, null, Node.class);
		
		public RequireNodeClassArgument(final Word a) {
			super(new Word() {
				@Override
				public Result go(final Environment executeEnvironment) {
					return result;
				}
			}, a);
		}
	}
	
	/**
	 * The Class<?> to test.
	 */
	private final Word c;
	
	/**
	 * The argument to test.
	 */
	private final Word a;
	
	public RequireClassArgument(final Word c, final Word a) {
		super();
		this.c = c;
		this.a = a;
	}

	@Override
	public Result go(final Environment executeEnvironment) {
		try {
			Result result = executeEnvironment.execute(c);
			if (result.isTrue()) {
				final Class<?> c = result.getValue();
				result = executeEnvironment.execute(a);
				if (result.isTrue()) {
					final Object a = result.getValue();
					if (!c.isAssignableFrom(a.getClass())) {
						result = new Result(false, String.format("Class [%s] is not assignable from class [%s].", a.getClass().toString(), c.toString()), null);
					}
				}
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

}
