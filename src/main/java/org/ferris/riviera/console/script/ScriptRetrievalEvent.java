package org.ferris.riviera.console.script;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptRetrievalEvent {
	
	public static final int FIND_SCRIPT_TABLE = 10;
	
	public static final int CREATE_SCRIPT_TABLE = 20;
	
    public static final int LOAD_SCRIPTS_FROM_DATABASE = 30;
    
    private boolean tableThere;

	public boolean isTableThere() {
		return tableThere;
	}

	public void setTableThere(boolean tableThere) {
		this.tableThere = tableThere;
	}
        
}
