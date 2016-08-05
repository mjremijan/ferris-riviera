package org.ferris.riviera.console.script;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptHistory {

    private ArrayList<Script> scripts;

    public ScriptHistory(List<Script> scripts) {
        this.scripts = new ArrayList<>(scripts);
    }

    public List<Script> list() {
        return scripts;
    }

    public int size() {
        return scripts.size();
    }

    public Script current() {
        if (scripts.isEmpty()) {
            return null;
        } else {
            return scripts.get(scripts.size() - 1);
        }
    }

}
