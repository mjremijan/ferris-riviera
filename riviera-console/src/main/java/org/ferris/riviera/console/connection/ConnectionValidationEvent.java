package org.ferris.riviera.console.connection;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ConnectionValidationEvent {

    public static final int VALIDATE = 100;
    public static final int VIEW = 200;

    protected String validationSql;
    protected String validationResult;

    void setValidationSql(String validationSql) {
        this.validationSql = validationSql;
    }

    public String getValidationSql() {
        return validationSql;
    }

    void setValidationResult(String validationResult) {
        this.validationResult = validationResult;
    }

    public String getValidationResult() {
        return validationResult;
    }
}
