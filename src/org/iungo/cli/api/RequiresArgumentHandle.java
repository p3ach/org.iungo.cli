package org.iungo.cli.api;

import org.iungo.result.api.Result;

public abstract class RequiresArgumentHandle implements Word {

	/**
	 * By default we check if the ExecuteEnvironment has an ArgumentHandle for this. 
	 */
	@Override
	public Result go(final Environment executeEnvironment) {
		return Result.valueOf(executeEnvironment.getHelpWordHandles().get(this.getClass().getName()) != null);
	}

}
