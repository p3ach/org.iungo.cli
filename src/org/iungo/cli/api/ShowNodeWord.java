package org.iungo.cli.api;

import org.iungo.cli.api.RequireClassArgument.RequireStringClassArgument;
import org.iungo.id.api.ID;
import org.iungo.result.api.Result;

/**
 * 
 * Requires an argument handle.
 * 
 * @author dick
 *
 */
public class ShowNodeWord implements Word {

	private final Word id;

	public ShowNodeWord(final Word id) {
		super();
		this.id = new RequireStringClassArgument(id);
	}

	@Override
	public Result go(final Environment executeEnvironment) {
		try {
			Result result = executeEnvironment.execute(id);
			if (result.isTrue()) {
				result = new Result(true, null, ID.valueOf((String) result.getValue()));
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}
}
