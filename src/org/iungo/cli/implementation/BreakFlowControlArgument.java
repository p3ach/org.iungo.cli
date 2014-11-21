package org.iungo.cli.implementation;

import org.iungo.context.api.Context;
import org.iungo.result.api.Result;

public class BreakFlowControlArgument extends FlowControlArgument {

	@Override
	public Result go(final Context context) {
		new CLIContext(context).peekBlock().setBreak();
		return Result.TRUE;
	}

}
