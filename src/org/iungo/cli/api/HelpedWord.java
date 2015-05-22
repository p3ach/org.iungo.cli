package org.iungo.cli.api;

import org.iungo.result.api.Result;

/**
 * When a Word requires help to execute it can implement HelpedWord and call Environment.help(this) from it's execute(Environment) method.
 * <p>Environment will call HelpWord.prepare(Environment) and if the Result is true attempt to call the appropriate WordHandle.go().
 * The WordHandle.go() can then "help" the Word to execute.
 * 
 * @author Dick
 *
 */
public interface HelpedWord extends Word {

	Result prepare(Environment environment);
}
