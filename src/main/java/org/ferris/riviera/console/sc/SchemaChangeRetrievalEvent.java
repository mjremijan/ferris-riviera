package org.ferris.riviera.console.sc;

import java.util.List;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class SchemaChangeRetrievalEvent {
    public static int LOAD_SCHEMA_CHANGES_FROM_DATABASE = 10;

    public static int LOAD_CHANGESCRIPTS_FROM_BUILD = 20;
    public static int PRINT_TWEETS_FROM_TWITTER = 25;
    public static int FIND_NEW_TWEETS_FROM_THIS_RUN = 30;
    public static int CHECK_NEW_TWEETS_FROM_THIS_RUN_AGAINST_PREFERENCES = 35;
    public static int SAVE_NEW_TWEETS_FROM_THIS_RUN = 40;
    public static int REMOVE_TWEETS_FROM_LAST_RUNS_THAT_ARE_TOO_OLD = 45;

    private List<SchemaChange> changeScriptsFromDatabase;
}
