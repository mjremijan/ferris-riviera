package org.ferris.riviera.console.jar;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Test;

public class JarEntryStatementsTest {

    @Test
    public void multi_statements_with_multi_comments_and_multi_semicolons() throws IOException {
        //setup
        StringBuilder sp = new StringBuilder();
        sp.append("-- select all the people data\n");
        sp.append("\n");
        sp.append("select \n");
        sp.append(" *  --get all columns of data\n");
        sp.append("from\n");
        sp.append("	people; \n");
        sp.append("	\n");
        sp.append("	\n");
        sp.append("--\n");
        sp.append("--[begin] insert new person\n");
        sp.append("--\n");
        sp.append("insert into people\n");
        sp.append("  (fname, lname, ssn, year_born)\n");
        sp.append("values\n");
        sp.append("  (\n");
        sp.append("	  'first name; string'\n");
        sp.append("	, 'last; name -- string'\n");
        sp.append("	, '123-44-5678'\n");
        sp.append("	, 1999\n");
        sp.append("  )\n");
        sp.append(";  --[end] insert new person\n");
        sp.append("\n");
        sp.append("\n");
        sp.append("--\n");
        sp.append("-- find only certain people\n");
        sp.append("--\n");
        sp.append("select\n");
        sp.append("  lname, year_born\n");
        sp.append("from\n");
        sp.append("  people\n");
        sp.append("where\n");
        sp.append("  ssn like '123%'\n");
        sp.append("  and\n");
        sp.append("  year_born > 1950;  --baby boomers\n");
        sp.append("          \n");
        sp.append("\n");

        //action
        JarEntryStatements ss = new JarEntryStatements(sp.toString());

        //verify
        Assert.assertEquals(3, ss.size());
        Assert.assertEquals("select\n*\nfrom\npeople", ss.get(0));
        Assert.assertEquals("insert into people\n(fname, lname, ssn, year_born)\nvalues\n(\n'first name; string'\n, 'last; name -- string'\n, '123-44-5678'\n, 1999\n)", ss.get(1));
    }

    @Test
    public void remove_comments_if_they_exist() {
        Pattern p
            //= Pattern.compile("(?m)--(.*?)$");
            = Pattern.compile("(?m)--[^']*?$");
        Matcher m;
        {
            //          0123456789|123456789|123456789|123456789|
            String s = "select * from foo; -- awesome comment!";
            m = p.matcher(s);
            Assert.assertTrue(m.find());
            Assert.assertEquals(19, m.start());
            Assert.assertEquals(38, m.end());

            s = s.substring(0, 19).trim();
            Assert.assertEquals("select * from foo;", s);
        }
        {
            //          0123456789|123456789|123456789|123456789|
            String s = "--Just a comment by itself";
            m = p.matcher(s);
            Assert.assertTrue(m.find());
            Assert.assertEquals(0, m.start());
            Assert.assertEquals(26, m.end());

            s = s.substring(0, 0).trim();
            Assert.assertEquals("", s);
        }
        {
            //          0123456789 |123456789|123456789 |123456789|123456789|123456789|
            String s = "select * \n -- inline comment \n from foo; --ending comment";
            m = p.matcher(s);

            // -- inline comment
            Assert.assertTrue(m.find());
            Assert.assertEquals(11, m.start());
            Assert.assertEquals(29, m.end());

            // --ending comment
            Assert.assertTrue(m.find());
            Assert.assertEquals(41, m.start());
            Assert.assertEquals(57, m.end());
        }
        {
            //          0123456789 |123456789|123456789 |123456789|123456789|123456789|
            String s = "select * \n -- inline comment \n from foo; --ending comment";
            s = s.replaceAll(p.pattern(), "").trim();
            Assert.assertEquals("select * \n \n from foo;", s);
        }
        {
            //          0123456789 |123456789|123456789 |123456789|123456789|123456789|
            String s = "--";
            s = s.replaceAll(p.pattern(), "").trim();
            Assert.assertEquals("", s);
        }
    }

    @Test
    public void two_statements_multi_line_and_with_first_statement_endswith_semicolon_and_not_terminated_with_semicolon_and_second_statement_endswith_semicolon() throws IOException {
        //setup
        String content = "  select    * \n from foo; \n\n\n\n\n\n delete \n * \n from\n  bar ;";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(2, ss.size());
        Assert.assertEquals("select    *\nfrom foo", ss.get(0));
        Assert.assertEquals("delete\n*\nfrom\nbar", ss.get(1));
    }

    @Test
    public void two_statements_multi_line_and_with_first_statement_endswith_semicolon_and_terminated_with_semicolon_and_second_statement_endswith_semicolon() throws IOException {
        //setup
        String content = "  select    * \n from foo; \n;\n delete \n * \n from\n  bar ;";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(2, ss.size());
        Assert.assertEquals("select    *\nfrom foo", ss.get(0));
        Assert.assertEquals("delete\n*\nfrom\nbar", ss.get(1));
    }

    @Test
    public void one_statement_multi_line_endswith_semicolon_and_terminated_with_semicolon() throws IOException {
        //setup
        String content = "  select * \n from foo; \n;";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select *\nfrom foo", ss.get(0));
    }

    @Test
    public void one_statement_multi_line_terminated_with_semicolon() throws IOException {
        //setup
        String content = "  select * \n from foo \n;";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select *\nfrom foo", ss.get(0));
    }

    @Test
    public void one_statement_multi_line_not_terminated_with_semicolon() throws IOException {
        //setup
        String content = "  select * \n from foo";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select *\nfrom foo", ss.get(0));
    }

    @Test
    public void one_statement_single_line_padded_endswith_semicolon2_not_terminated_with_semicolon() throws IOException {
        //setup
        String content = "  select * from foo  ;  ";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }

    @Test
    public void one_statement_single_line_padded_endswith_semicolon_not_terminated_with_semicolon() throws IOException {
        //setup
        String content = "  select * from foo;  ";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }

    @Test
    public void one_statement_single_line_padded_not_terminated_with_semicolon() throws IOException {
        //setup
        String content = "  select * from foo  ";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }

    @Test
    public void two_statements_single_line_both_endwith_semicolon_and_both__not_terminated_with_semicolon() throws IOException {
        //setup
        String content = "select * from foo; \n delete * from bar;";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(2, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
        Assert.assertEquals("delete * from bar", ss.get(1));
    }

    @Test
    public void two_statements_single_line_both_endwith_semicolon_and_both_terminated_with_semicolon() throws IOException {
        //setup
        String content = "select * from foo;\n;\ndelete * from bar;\n;";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

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
        JarEntryStatements ss = new JarEntryStatements(content);

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
        JarEntryStatements ss = new JarEntryStatements(content);

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
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }

    @Test
    public void one_statement_single_line_ending_with_semicolon_and_terminated_with_semicolon() throws IOException {
        //setup
        String content = "select * from foo;\n;";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }

    @Test
    public void one_statement_single_line_terminated_with_semicolon() throws IOException {
        //setup
        String content = "select * from foo\n;";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }

    @Test
    public void one_statement_single_line_and_not_terminated_with_semicolon() throws IOException {
        //setup
        String content = "select * from foo";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(1, ss.size());
        Assert.assertEquals("select * from foo", ss.get(0));
    }

    @Test
    public void trim_to_empty_multi_line_semicolon_file() throws IOException {
        //setup
        String content = " ;\n    ;\n            ;\n  ;";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }

    @Test
    public void empty_multi_line_semicolon_file() throws IOException {
        //setup
        String content = ";\n;\n;\n;";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }

    @Test
    public void trim_to_empty_single_line_semicolon_file() throws IOException {
        //setup
        String content = "  ;";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }

    @Test
    public void semicolon_file() throws IOException {
        //setup
        String content = ";";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }

    @Test
    public void trim_to_empty_multi_line_file() throws IOException {
        //setup
        String content = " \n    \n            \n  ";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }

    @Test
    public void empty_multi_line_file() throws IOException {
        //setup
        String content = "\n\n\n";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }

    @Test
    public void trim_to_empty_single_line_file() throws IOException {
        //setup
        String content = "  ";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }

    @Test
    public void empty_file() throws IOException {
        //setup
        String content = "";

        //action
        JarEntryStatements ss = new JarEntryStatements(content);

        //verify
        Assert.assertEquals(0, ss.size());
    }

}
