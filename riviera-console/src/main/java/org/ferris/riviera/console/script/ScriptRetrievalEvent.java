package org.ferris.riviera.console.script;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptRetrievalEvent {

    public static final int FIND_SCRIPT_TABLE = 10;

    public static final int CREATE_SCRIPT_TABLE = 20;

    public static final int RETRIEVE_SCRIPTS_FROM_DATABASE = 30;

    public static final int SHOW_SCRIPTS_FROM_DATABASE = 40;

    public static final int RETRIEVE_SCRIPTS_FROM_JAR = 50;

    public static final int FILTER_FOR_NEW_SCRIPTS_TO_APPLY = 60;

    public static final int SHOW_NEW_SCRIPTS_TO_APPLY = 70;

    private boolean tableThere;
    private boolean tableCreatedSuccessfully;
    private Scripts scriptsFromDatabase;
    private Scripts scriptsFromJar;
    private Scripts scriptsToApply;
    private String scriptJarFileName;

    public boolean isTableCreatedSuccessfully() {
        return tableCreatedSuccessfully;
    }

    public String getScriptJarFileName() {
        return scriptJarFileName;
    }

    public void setScriptJarFileName(String scriptJarFileName) {
        this.scriptJarFileName = scriptJarFileName;
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
