package org.ferris.riviera.console.script;

import java.util.Set;
import javax.validation.ConstraintViolation;
import org.ferris.riviera.console.script.jar.ScriptJarFile;


/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptProcessingEvent {

    // Database
    public static final int FIND_SCRIPT_TABLE = 100;
    public static final int CREATE_SCRIPT_TABLE = 120;
    public static final int RETRIEVE_SCRIPTS_FROM_DATABASE = 130;
    public static final int SHOW_SCRIPTS_FROM_DATABASE = 140;

    // Jar
    public static final int FIND_JAR_FILE = 200;
    public static final int RETRIEVE_SCRIPTS_FROM_JAR = 210;
    public static final int FILTER_FOR_NEW_SCRIPTS_TO_APPLY = 220;
    public static final int VALIDATE_NEW_SCRIPTS_TO_APPLY = 230;
    public static final int SHOW_NEW_SCRIPTS_TO_APPLY = 240;

    // Apply

    private boolean tableThere;
    private boolean tableCreatedSuccessfully;
    private ScriptJarFile scriptJarFile;
    private Scripts scriptsFromDatabase;
    private Scripts scriptsFromJar;
    private Scripts scriptsToApply;
    private Set<ConstraintViolation<Script>> problems;

    public Set<ConstraintViolation<Script>> getProblems() {
        return problems;
    }

    public void setProblems(Set<ConstraintViolation<Script>> problems) {
        this.problems = problems;
    }

    public ScriptJarFile getScriptJarFile() {
        return scriptJarFile;
    }

    public void setScriptJarFile(ScriptJarFile scriptJarFile) {
        this.scriptJarFile = scriptJarFile;
    }


    public boolean isTableCreatedSuccessfully() {
        return tableCreatedSuccessfully;
    }

    public void setTableCreatedSuccessfully(boolean tableCreatedSuccessfully) {
        this.tableCreatedSuccessfully = tableCreatedSuccessfully;
    }

    public boolean isTableThere() {
        return tableThere;
    }

    public void setTableThere(boolean tableThere) {
        this.tableThere = tableThere;
    }

    void setScriptsFromDatabase(Scripts scriptsFromDatabase) {
        this.scriptsFromDatabase
            = scriptsFromDatabase;
    }

    public Scripts getScriptsFromDatabase() {
        return scriptsFromDatabase;
    }

    void setScriptsFromJar(Scripts scripts) {
        this.scriptsFromJar = scripts;
    }

    public Scripts getScriptsFromJar() {
        return scriptsFromJar;
    }

    public void setScriptsToApply(Scripts toApply) {
        scriptsToApply = toApply;
    }

    public Scripts getScriptsToApply() {
        return scriptsToApply;
    }

}
