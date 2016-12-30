package org.ferris.riviera.console.script;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Scripts {

    private ArrayList<Script> scripts;

    public Scripts(List<Script> scripts) {
        this.scripts = new ArrayList<>(scripts);
    }

    public Scripts(Scripts copyMe) {
        this(copyMe.scripts);
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
            return scripts.stream()
                .filter(s -> s.getAppliedOn() != null)
                .max(Comparator.comparing(Script::getAppliedOn)).get();
        }
    }

    public Scripts removeAll(Scripts fromDatabase) {
        scripts.removeAll(fromDatabase.scripts);
        return this;
    }

    public boolean isEmpty() {
        return scripts.isEmpty();
    }

    public void forEach(Consumer<? super Script> action) {
        scripts.forEach(action);
    }
}
