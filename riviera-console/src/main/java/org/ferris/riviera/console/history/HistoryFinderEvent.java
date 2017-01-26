package org.ferris.riviera.console.history;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class HistoryFinderEvent {
    public static final int FIND = 100;

    protected HistoryList history;

    protected void setHistory(HistoryList list) {
        history = list;
    }

    protected HistoryList getHistory() {
        return history;
    }
}
