package org.iungo.cli.api;


public interface Arguments extends Argument, Iterable<Argument> {

	Boolean addArgument(Argument argument);
}
