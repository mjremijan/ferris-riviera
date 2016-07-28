package org.ferris.riviera.console.connection;

import javax.inject.Singleton;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ConnectionProperties {

    protected String url, username, password;

    protected String setSchema;

    protected String[] types;
    protected String catalog;
    protected String schemaPattern;
    protected String namePattern;

    public ConnectionProperties(
            String url, String username, String password, String setSchema, String[] types, String catalog, String schemaPattern, String namePattern
    ) {
        this.url = url;
        this.username = username;
        this.password = password;

        this.setSchema = setSchema;

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

    public String getSetSchema() {
        return setSchema;
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

}
