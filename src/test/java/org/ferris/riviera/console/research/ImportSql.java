package org.ferris.riviera.console.research;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ImportSql {
    
    Pattern directoryNamePattern;

    @Before
    public void before() {
        directoryNamePattern
            = Pattern.compile("(\\d+.{1}\\d+.{1}\\d+)(\\s+-\\s+(.+))?/");
    }
    
    @Test
    public void readFromJar() throws Exception
    {
        File f = new File("src/test/misc/ddl-1.0-SNAPSHOT.jar");
        JarFile jarFile = new JarFile(f);
        
        Enumeration<JarEntry> jarEntries
             = jarFile.entries();
        
        Map<String,String> dirs
                = new HashMap<>();
                
        while (jarEntries.hasMoreElements()) {
            JarEntry je = jarEntries.nextElement();
            if (je.isDirectory()) {
                Matcher matcher = directoryNamePattern.matcher(je.getName());
                if (matcher.matches()) {
                    String name = matcher.group(0);
                }
            }
            System.out.printf("-----%n");
            System.out.printf("Name %s%n", je.getName());
            System.out.printf("isDirectory %b%n", je.isDirectory());
        }
    }
    
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
