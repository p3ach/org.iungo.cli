package org.iungo.cli.api;

import org.iungo.result.api.Result;
import org.iungo.tdb.api.TDBNodeContext;

public class CreateTDBNodeArgument extends CreateNodeArgument {

	private final Argument location;
	
	public CreateTDBNodeArgument(final Argument location) {
		super();
		this.location = location;
	}

	@Override
	public Result execute(final ExecuteEnvironment executeEnvironment) {
		Result result = null;
		try {
			result = executeEnvironment.execute(location);
			if (result.isTrue()) {
				final TDBNodeContext tdbNodeContext = new TDBNodeContext();
				tdbNodeContext.putLocation((String) result.getValue());
				result = new Result(true, null, tdbNodeContext);
			}
			return result;
		} catch (final Exception exception) {
			result = Result.valueOf(exception);
			return result;
		}
	}

}
