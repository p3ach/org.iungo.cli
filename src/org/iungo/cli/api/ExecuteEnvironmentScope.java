package org.iungo.cli.api;

import org.iungo.id.api.ID;
import org.iungo.result.api.Result;

public class ExecuteEnvironmentScope extends BlockScope {

	public static final String RESULT_STATE = (new ID(ExecuteEnvironmentScope.class.getName(), Result.class.getName(), "State")).toString();

	public static final String RESULT_TEXT = (new ID(ExecuteEnvironmentScope.class.getName(), Result.class.getName(), "Text")).toString();
	
	public static final String RESULT_VALUE = (new ID(ExecuteEnvironmentScope.class.getName(), Result.class.getName(), "Value")).toString();
	
	public ExecuteEnvironmentScope() {
		super(null);
		define(RESULT_STATE, Result.UNDEFINED.getState());
		define(RESULT_TEXT, Result.UNDEFINED.getText());
		define(RESULT_VALUE, Result.UNDEFINED.getValue());
	}

	@Override
	public Block getBlock() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return String.format("%s [\n%s\n]", ExecuteEnvironmentScope.class.getName(), super.toString());
	}
}
