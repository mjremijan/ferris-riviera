package org.ferris.riviera.console.connection;

import javax.enterprise.inject.Vetoed;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Vetoed
public class ConnectionProperties {

    protected String url;
    protected String username;
    protected String password;
    protected String validationSql;

    protected String schemaSql;

    protected String[] types;
    protected String catalog;
    protected String schemaPattern;
    protected String namePattern;

    public ConnectionProperties(
        String url, String username, String password, String validationSql, String setSchema, String[] types, String catalog, String schemaPattern, String namePattern
    ) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validationSql = validationSql;

        this.schemaSql = setSchema;

        this.types = types;
        this.catalog = catalog;
        this.schemaPattern = schemaPattern;
        this.namePattern = namePattern;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSchemaSql() {
        return schemaSql;
    }

    public String[] getTypes() {
        return types;
    }

    public String getCatalog() {
        return catalog;
    }

    public String getSchemaPattern() {
        return schemaPattern;
    }

    public String getNamePattern() {
        return namePattern;
    }

    public String getValidationSql() {
        return validationSql;
    }

}
