package org.ferris.riviera.console.script;

public class ScriptTableCreator {

//	protected void loadChangesFromDatabase(
//	        @Observes @Priority(FIND_SCRIPT_TABLE) ScriptRetrievalEvent event
//	    ) {
//	        Connection conn = handler.getConnection();
//	        try {
//	            Statement stmt
//	                = conn.createStatement();
//
//	            //stmt.execute("set schema app");
//
//	            // http://apache-database.10148.n7.nabble.com/DatabaseMetaData-getTables-resultset-empty-td105623.html
//	            String catalog = connectionProperties.getCatalog();
//	            String schemaPattern = connectionProperties.getSchemaPattern();
//	            String tableNamePattern = connectionProperties.getNamePattern();
//	            String[] types = connectionProperties.getTypes();
//	            
//	            ResultSet tables
//	                = conn.getMetaData().getTables(catalog, schemaPattern, tableNamePattern, types);
//	            System.out.printf("TABLES%n");
//
//	            while (tables.next()) {
//	                System.out.printf(
//	                    "TABLE_TYPE=%s, TABLE_CAT=%s, TABLE_SCHEM=%s, TABLE_NAME=%s%n"
//	                    , tables.getString("TABLE_TYPE")
//	                    , tables.getString("TABLE_CAT")
//	                    , tables.getString("TABLE_SCHEM")
//	                    , tables.getString("TABLE_NAME")
//	                );
//	            }
//
//	        } catch (Exception e) {
//	            throw new RuntimeException(e);
//	        }
//	    }
}
