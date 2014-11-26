package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.api.MethodParameter;
import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public class DefaultMethodArgument extends DefaultValue implements MethodParameter {
	
	public DefaultMethodArgument(final Argument parameter) {
		super(parameter);
	}

	@Override
	public Result go(final Context context) {
		return ((Argument) getValue()).go(context);
	}

	@Override
	public <T> T setValue(T value) {
		throw new UnsupportedOperationException();
	}
}
