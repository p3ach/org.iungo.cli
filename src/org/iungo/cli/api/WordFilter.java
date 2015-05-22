package org.iungo.cli.api;

import org.iungo.filter.api.Filter;
import org.iungo.result.api.Result;

public interface WordFilter extends Filter<Word> {

	static WordFilter PERMIT_ALL = new WordFilter() {
		@Override
		public Result go(final Word word) {
			return Filter.PERMIT_RESULT;
		}
	};
}
