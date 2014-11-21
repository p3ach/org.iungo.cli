package org.iungo.cli.implementation;

import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public class ContinueFlowControlArgument extends FlowControlArgument {

	@Override
	public Result go(final Context context) {
		new CLIContext(context).peekBlock().setContinue();
		return Result.TRUE;
	}

}
