package org.ferris.riviera.console.execute;

import javax.enterprise.inject.Vetoed;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Vetoed
public class ExecuteProperties {

    protected Boolean executeSql;

    public ExecuteProperties(
        Boolean executeSql
    ) {
        this.executeSql = executeSql;
    }

    public Boolean getExecuteSql() {
        return executeSql;
    }
}
