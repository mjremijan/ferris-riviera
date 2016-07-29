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
import org.ferris.riviera.console.script.Script;
import org.ferris.riviera.console.script.ScriptBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ImportSql {
    
    ScriptBuilder builder;
    
    @Before
    public void before() {
        builder = new ScriptBuilder();
    }   
    
    @Test
    public void readFromJar() throws Exception
    {
        File f = new File("src/test/misc/ddl-1.0-SNAPSHOT.jar");
        JarFile jarFile = new JarFile(f);
        
        Enumeration<JarEntry> jarEntries
            = jarFile.entries();
        
        Map<Script,JarEntry> scripts
            = new HashMap<>();
                
        while (jarEntries.hasMoreElements()) {
            JarEntry je = jarEntries.nextElement();
            Script sc = builder.setJarEntry(je).build();
            if (sc != null) {
                scripts.put(sc, je);
            }
        }
        
        scripts.forEach(
            (k,v)->System.out.printf("Script: %s, JarEntry: %s%n",k.toString(), v.getName())
        );
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
