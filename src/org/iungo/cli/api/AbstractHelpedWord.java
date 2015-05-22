package org.iungo.cli.api;

import org.iungo.result.api.Result;

public abstract class AbstractHelpedWord implements HelpedWord {

	@Override
	public Result go(final Environment environment) {
		Result result = environment.permit(this);
		if (result.isTrue()) {
			result = environment.help(this);
		}
		return result;
	}

}
