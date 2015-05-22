package org.iungo.cli.api;

import org.iungo.id.api.ID;
import org.iungo.result.api.Result;

/**
 * Close the Node for the given ID.
 * The ExecuteEnvironment needs to have a defined ArgumentHandle...
 *  
 * @author Dick
 *
 */
public class CloseNodeWord implements Word {

	private final Word id;
	
	public CloseNodeWord(final Word id) {
		super();
		this.id = id;
	}

	@Override
	public Result go(final Environment executeEnvironment) {
		try {
			Result result = executeEnvironment.execute(id);
			if (result.isTrue()) {
				final ID id = ID.valueOf((String) result.getValue());
				result = new Result(true, null, id);
			}
			return result;
		} catch (final Exception exception) {
			return Result.valueOf(exception);
		}
	}

}
