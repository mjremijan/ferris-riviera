package org.ferris.riviera.console.script;

import java.util.ArrayList;
import java.util.Comparator;
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

    public Script getLatestVersion() {
        if (scripts.isEmpty()) {
            return null;
        } else {
            return scripts.get(scripts.size() - 1);
        }
    }

    public Script getLastAppliedVersion() {
        if (scripts.isEmpty()) {
            return null;
        } else {
            return scripts.stream().max(Comparator.comparing(Script::getAppliedOn)).get();
        }
    }

}
