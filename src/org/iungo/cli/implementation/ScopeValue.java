package org.iungo.cli.implementation;

public class ScopeValue {

	protected Object value = null;
	
	public ScopeValue(final Object value) {
		super();
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(final Object value) {
		this.value = value;
	}
}
