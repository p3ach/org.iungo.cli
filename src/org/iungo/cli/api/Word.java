package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * A word is the basic building block for the CLI.
 * 
 * @author Dick
 * 
 */
public interface Word {
	
	Result go(Environment environment);
}
