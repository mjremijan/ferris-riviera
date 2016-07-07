package org.ferris.riviera.console.research;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ImportSql {
    /**
     * http://stackoverflow.com/questions/1497569/how-to-execute-sql-script-file-using-jdbc
     *
     * @param conn
     * @param in
     * @throws SQLException
     */
    public static void importSQL(Connection conn, InputStream in) throws SQLException {
        Scanner s = new Scanner(in);
        //s.useDelimiter("(;(\r)?\n)|(--\n)");
        s.useDelimiter("(;(\r)?\n)|((\r)?\n)?(--)?.*(--(\r)?\n)");
        Statement st = null;
        try {
            st = conn.createStatement();
            while (s.hasNext()) {
                String line = s.next();
                if (line.startsWith("/*!") && line.endsWith("*/")) {
                    int i = line.indexOf(' ');
                    line = line.substring(i + 1, line.length() - " */".length());
                }

                if (line.trim().length() > 0) {
                    st.execute(line);
                }
            }
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }
}
