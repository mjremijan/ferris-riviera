package org.ferris.riviera.console.sql;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@RunWith(MockitoJUnitRunner.class)

class StatementSplitter extends LinkedList<String> {

    public StatementSplitter(String fileContents) throws IOException {
        // http://stackoverflow.com/questions/1497569/how-to-execute-sql-script-file-using-jdbc
        //Scanner s = new Scanner(statements);
        //s.useDelimiter("(;(\r)?\n)|((\r)?\n)?(--)?.*(--(\r)?\n)");

        StringReader sr = new StringReader(fileContents + "\n" + ";");
        LineNumberReader reader = new LineNumberReader(sr);

        StringBuilder sp = new StringBuilder();
        for (String line = reader.readLine(); line != null; line = reader.readLine())
        {
            String trimmed = line.trim();
            if (";".equals(trimmed)) {
                if (sp.length() > 0) {
                    if (';' == sp.charAt(sp.length() - 1)) {
                        sp.deleteCharAt(sp.length() - 1);
                    }
                    super.add(sp.toString().trim());
                    sp.setLength(0);
                }
            } else {
                if (sp.length() > 0) {
                    sp.append("\n");
                }
                sp.append(trimmed);
            }
        }

    }
}

public class StatementSplitterTest {

    @Test
    public void one_statement_multi_line_endswith_semicolon_and_terminated_with_semicolon() throws IOException {
        //setup
        String content = "  select * \n from foo; \n;";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select *\nfrom foo", ss.get(0));
    }



    @Test
    public void one_statement_multi_line_terminated_with_semicolon() throws IOException {
        //setup
        String content = "  select * \n from foo \n;";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select *\nfrom foo", ss.get(0));
    }


    @Test
    public void one_statement_multi_line_not_terminated_with_semicolon() throws IOException {
        //setup
        String content = "  select * \n from foo";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select *\nfrom foo", ss.get(0));
    }


    @Test
    public void one_statement_single_line_padded_endswith_semicolon2_not_terminated_with_semicolon() throws IOException {
        //setup
        String content = "  select * from foo  ;  ";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }


    @Test
    public void one_statement_single_line_padded_endswith_semicolon_not_terminated_with_semicolon() throws IOException {
        //setup
        String content = "  select * from foo;  ";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }


    @Test
    public void one_statement_single_line_padded_not_terminated_with_semicolon() throws IOException {
        //setup
        String content = "  select * from foo  ";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }


    @Test
    public void two_statements_single_line_both_endwith_semicolon_and_both_terminated_with_semicolon() throws IOException {
        //setup
        String content = "select * from foo;\n;\ndelete * from bar;\n;";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(2, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
        Assert.assertEquals("delete * from bar", ss.get(1));
    }


    @Test
    public void two_statements_single_line_both_terminated_with_semicolon() throws IOException {
        //setup
        String content = "select * from foo\n;\ndelete * from bar\n;";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(2, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
        Assert.assertEquals("delete * from bar", ss.get(1));
    }


    @Test
    public void two_statements_single_line_first_terminated_with_semicolon() throws IOException {
        //setup
        String content = "select * from foo\n;\ndelete * from bar";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(2, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
        Assert.assertEquals("delete * from bar", ss.get(1));
    }


    @Test
    public void one_statement_single_line_ending_with_semicolon_and_not_terminated_with_semicolon() throws IOException {
        //setup
        String content = "select * from foo;";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }


    @Test
    public void one_statement_single_line_ending_with_semicolon_and_terminated_with_semicolon() throws IOException {
        //setup
        String content = "select * from foo;\n;";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }


    @Test
    public void one_statement_single_line_terminated_with_semicolon() throws IOException {
        //setup
        String content = "select * from foo\n;";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }


    @Test
    public void one_statement_single_line_and_not_terminated_with_semicolon() throws IOException {
        //setup
        String content = "select * from foo";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }


    @Test
    public void trim_to_empty_multi_line_semicolon_file() throws IOException {
        //setup
        String content = " ;\n    ;\n            ;\n  ;";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }


    @Test
    public void empty_multi_line_semicolon_file() throws IOException {
        //setup
        String content = ";\n;\n;\n;";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }



    @Test
    public void trim_to_empty_single_line_semicolon_file() throws IOException {
        //setup
        String content = "  ;";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }


    @Test
    public void semicolon_file() throws IOException {
        //setup
        String content = ";";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }


    @Test
    public void trim_to_empty_multi_line_file() throws IOException {
        //setup
        String content = " \n    \n            \n  ";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }


    @Test
    public void empty_multi_line_file() throws IOException {
        //setup
        String content = "\n\n\n";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }



    @Test
    public void trim_to_empty_single_line_file() throws IOException {
        //setup
        String content = "  ";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }


    @Test
    public void empty_file() throws IOException {
        //setup
        String content = "";

        //action
        StatementSplitter ss = new StatementSplitter(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }

}
