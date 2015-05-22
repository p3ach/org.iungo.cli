package org.iungo.cli.api;

/**
 * 
 * @author Dick
 *
 */
public abstract class AbstractOpenConfigWord implements Word {

	private final Word name;

	public AbstractOpenConfigWord(final Word name) {
		super();
		this.name = name;
	}

	public Word getName() {
		return name;
	}
}
