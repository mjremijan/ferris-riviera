package org.ferris.riviera.console.research;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
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
            System.out.printf("%s%n", je.toString());
            Script sc = builder.setJarEntry(je).build();
            if (sc != null) {
                scripts.put(sc, je);
            }
        }

        scripts.forEach(
            (k,v)->System.out.printf("Script: %s, JarEntry: %s%n",k.toString(), v.getName())
        );

        scripts.forEach(
            (k,v)-> {
                try {
                    InputStream is = jarFile.getInputStream(v);
                    String result = new BufferedReader(new InputStreamReader(is)).lines()
                            .parallel().collect(Collectors.joining("\n"));

                    //System.out.printf("---------------------%n%s---------------------%n",result);

                    List<String> l
                            = split(result);

                    l.forEach(x -> System.out.printf(">>> %s%n",x));

                } catch (IOException ex) {
                   throw new RuntimeException(ex);
                }
            }
        );

    }


    /**
     * http://stackoverflow.com/questions/1497569/how-to-execute-sql-script-file-using-jdbc
     *
     * @param conn
     * @param in
     * @throws SQLException
     */
    public List<String> split(String sql)
    {
        Scanner s = new Scanner(sql);
        //s.useDelimiter("(;(\r)?\n)|(--\n)");
        s.useDelimiter("(;(\r)?\n)|((\r)?\n)?(--)?.*(--(\r)?\n)");

        List<String> l = new LinkedList<>();
        try {
            while (s.hasNext()) {
                String line = s.next();
                if (line.startsWith("/*!") && line.endsWith("*/")) {
                    int i = line.indexOf(' ');
                    line = line.substring(i + 1, line.length() - " */".length());
                }

                if (line.trim().length() > 0) {
                    l.add(line);
                }
            }
        } finally {
        }
        return l;
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
