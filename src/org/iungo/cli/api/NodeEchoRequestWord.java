package org.iungo.cli.api;

import org.iungo.id.api.ID;
import org.iungo.result.api.Result;

public class NodeEchoRequestWord implements Word {

	private final Word id;

	public NodeEchoRequestWord(final Word id) {
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
