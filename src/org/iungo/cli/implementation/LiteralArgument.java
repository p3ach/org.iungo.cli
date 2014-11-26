package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.osgi.CLIBundleActivator;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;
import org.iungo.result.api.ResultAPI;

public class LiteralArgument implements Argument {
	
	public static final LiteralArgument NULL = new LiteralArgument(null) {
		@Override
		public String toString() {
			return "literal null";
		}
	};
	
	public static final LiteralArgument FALSE = new LiteralArgument(false);
	
	public static final LiteralArgument TRUE = new LiteralArgument(true);
	
	public static final LiteralArgument EMPTY_STRING = new LiteralArgument("");
	
	protected final Result result;
	
	public LiteralArgument(final Object value) {
		super();
		this.result = ((ResultAPI) CLIBundleActivator.getInstance().getAPI(ResultAPI.class)).create(true, null, value);
	}

	@Override
	public Result go(final Context context) {
		return result;
	}

	@Override
	public String toString() {
		return "literal";
	}

}
