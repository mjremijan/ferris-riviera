package org.ferris.riviera.console.script;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptPattern {

    protected Pattern pattern;

    public ScriptPattern() {
        String dirRegex
            = "((\\d+.{1}\\d+.{1}\\d+)(\\s*-\\s*(.+))?)";
        String fileRegex
            = "((\\d+.{1}\\d+.{1}\\d+.{1}\\d+)(\\s*-\\s*(.+))?\\.sql)";
        pattern = Pattern.compile(dirRegex + "/" + fileRegex);
    }

    public Matcher matcher(CharSequence input) {
        Matcher m = pattern.matcher(input);
        m.matches();
        return m;
    }
}
