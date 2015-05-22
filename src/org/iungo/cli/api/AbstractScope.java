package org.iungo.cli.api;


public abstract class AbstractScope implements Scope {

	private final Values values;
	
	public AbstractScope() {
		super();
		values = createValues();
	}

	/**
	 * Override in sub class if you need to change the default values class from SimpleValues().
	 * @return SimpleValues()
	 */
	protected Values createValues() {
		return new Values.Default();
	}
	
	@Override
	public Values getValues() {
		return values;
	}
	
	@Override
	public String toString() {
		return String.format("Values [\n%s\n]", values);
	}
}
