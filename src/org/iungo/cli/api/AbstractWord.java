package org.iungo.cli.api;

import org.iungo.result.api.Result;

public class AbstractWord implements Word {

	@Override
	public Result go(final Environment environment) {
		return environment.permit(this);
	}

}
