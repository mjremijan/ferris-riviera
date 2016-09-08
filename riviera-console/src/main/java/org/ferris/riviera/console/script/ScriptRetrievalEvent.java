package org.ferris.riviera.console.script;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptRetrievalEvent {

    public static final int FIND_SCRIPT_TABLE = 10;

    public static final int CREATE_SCRIPT_TABLE = 20;

    public static final int RETRIEVE_SCRIPT_HISTORY_FROM_DATABASE = 30;

    public static final int SHOW_SCRIPT_HISTORY = 40;

    private boolean tableThere;
    private boolean tableCreatedSuccessfully;
    private ScriptHistory scriptHistoryFromDatabase;

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

    void setScriptHistoryFromDatabase(ScriptHistory scriptHistoryFromDatabase) {
        this.scriptHistoryFromDatabase
            = scriptHistoryFromDatabase;
    }

    public ScriptHistory getScriptHistoryFromDatabase() {
        return scriptHistoryFromDatabase;
    }

}
