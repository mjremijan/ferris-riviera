package org.ferris.riviera.console.script;

import java.util.regex.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@RunWith(MockitoJUnitRunner.class)
public class ScriptPatternTest {

    protected ScriptPattern pattern;

    @Before
    public void before() {
        pattern = new ScriptPattern();
    }



    @Test
    public void pattern1() {
        //setup
        String s = "1.0.0/1.0.0.1.sql";

        // action
        Matcher m = pattern.matcher(s);

        // assert
        Assert.assertTrue(m.matches());
        Assert.assertEquals(8, m.groupCount());
        {
            String directoryName = m.group(1);
            Assert.assertEquals("1.0.0", directoryName);

            String directoryVersion = m.group(2);
            Assert.assertEquals("1.0.0", directoryVersion);

            String dashDirectoryDescription = m.group(3);
            Assert.assertEquals(null, dashDirectoryDescription);

            String directoryDescription = m.group(4);
            Assert.assertEquals(null, directoryDescription);
        }
        {
            String fileName = m.group(5);
            Assert.assertEquals("1.0.0.1.sql", fileName);

            String fileVersion = m.group(6);
            Assert.assertEquals("1.0.0.1", fileVersion);

            String dashFileDescription = m.group(7);
            Assert.assertEquals(null, dashFileDescription);

            String fileDescription = m.group(8);
            Assert.assertEquals(null, fileDescription);
        }
    }


    @Test
    public void pattern2() {
        //setup
        String s = "1.0.0 - description/1.0.0.1.sql";

        // action
        Matcher m = pattern.matcher(s);

        // assert
        Assert.assertTrue(m.matches());
        Assert.assertEquals(8, m.groupCount());
        {
            String directoryName = m.group(1);
            Assert.assertEquals("1.0.0 - description", directoryName);

            String directoryVersion = m.group(2);
            Assert.assertEquals("1.0.0", directoryVersion);

            String dashDirectoryDescription = m.group(3);
            Assert.assertEquals(" - description", dashDirectoryDescription);

            String directoryDescription = m.group(4);
            Assert.assertEquals("description", directoryDescription);
        }
        {
            String fileName = m.group(5);
            Assert.assertEquals("1.0.0.1.sql", fileName);

            String fileVersion = m.group(6);
            Assert.assertEquals("1.0.0.1", fileVersion);

            String dashFileDescription = m.group(7);
            Assert.assertEquals(null, dashFileDescription);

            String fileDescription = m.group(8);
            Assert.assertEquals(null, fileDescription);
        }
    }


    @Test
    public void pattern3() {
        //setup
        String s = "1.0.0- description/1.0.0.1.sql";

        // action
        Matcher m = pattern.matcher(s);

        // assert
        Assert.assertTrue(m.matches());
        Assert.assertEquals(8, m.groupCount());
        {
            String directoryName = m.group(1);
            Assert.assertEquals("1.0.0- description", directoryName);

            String directoryVersion = m.group(2);
            Assert.assertEquals("1.0.0", directoryVersion);

            String dashDirectoryDescription = m.group(3);
            Assert.assertEquals("- description", dashDirectoryDescription);

            String directoryDescription = m.group(4);
            Assert.assertEquals("description", directoryDescription);
        }
        {
            String fileName = m.group(5);
            Assert.assertEquals("1.0.0.1.sql", fileName);

            String fileVersion = m.group(6);
            Assert.assertEquals("1.0.0.1", fileVersion);

            String dashFileDescription = m.group(7);
            Assert.assertEquals(null, dashFileDescription);

            String fileDescription = m.group(8);
            Assert.assertEquals(null, fileDescription);
        }
    }


    @Test
    public void pattern4() {
        //setup
        String s = "1.0.0-description/1.0.0.1.sql";

        // action
        Matcher m = pattern.matcher(s);

        // assert
        Assert.assertTrue(m.matches());
        Assert.assertEquals(8, m.groupCount());
        {
            String directoryName = m.group(1);
            Assert.assertEquals("1.0.0-description", directoryName);

            String directoryVersion = m.group(2);
            Assert.assertEquals("1.0.0", directoryVersion);

            String dashDirectoryDescription = m.group(3);
            Assert.assertEquals("-description", dashDirectoryDescription);

            String directoryDescription = m.group(4);
            Assert.assertEquals("description", directoryDescription);
        }
        {
            String fileName = m.group(5);
            Assert.assertEquals("1.0.0.1.sql", fileName);

            String fileVersion = m.group(6);
            Assert.assertEquals("1.0.0.1", fileVersion);

            String dashFileDescription = m.group(7);
            Assert.assertEquals(null, dashFileDescription);

            String fileDescription = m.group(8);
            Assert.assertEquals(null, fileDescription);
        }
    }


    @Test
    public void pattern4_1() {
        //setup
        String s = "1.0.0 -/1.0.0.1.sql";

        // action
        Matcher m = pattern.matcher(s);

        // assert
        Assert.assertFalse(m.matches());
    }


    @Test
    public void pattern4_2() {
        //setup
        String s = "1.0.0-/1.0.0.1.sql";

        // action
        Matcher m = pattern.matcher(s);

        // assert
        Assert.assertFalse(m.matches());
    }


    @Test
    public void pattern4_3() {
        //setup
        String s = "1.0.0- /1.0.0.1.sql";

        // action
        Matcher m = pattern.matcher(s);

        // assert
        Assert.assertTrue(m.matches());
        Assert.assertEquals(8, m.groupCount());
        {
            String directoryName = m.group(1);
            Assert.assertEquals("1.0.0- ", directoryName);

            String directoryVersion = m.group(2);
            Assert.assertEquals("1.0.0", directoryVersion);

            String dashDirectoryDescription = m.group(3);
            Assert.assertEquals("- ", dashDirectoryDescription);

            String directoryDescription = m.group(4);
            Assert.assertEquals(" ", directoryDescription);
        }
        {
            String fileName = m.group(5);
            Assert.assertEquals("1.0.0.1.sql", fileName);

            String fileVersion = m.group(6);
            Assert.assertEquals("1.0.0.1", fileVersion);

            String dashFileDescription = m.group(7);
            Assert.assertEquals(null, dashFileDescription);

            String fileDescription = m.group(8);
            Assert.assertEquals(null, fileDescription);
        }
    }



    @Test
    public void pattern5() {
        //setup
        String s = "1.0.0 - abc_zyz 123.789 - cool/1.0.0.1.sql";

        // action
        Matcher m = pattern.matcher(s);

        // assert
        Assert.assertTrue(m.matches());
        Assert.assertEquals(8, m.groupCount());
        {
            String directoryName = m.group(1);
            Assert.assertEquals("1.0.0 - abc_zyz 123.789 - cool", directoryName);

            String directoryVersion = m.group(2);
            Assert.assertEquals("1.0.0", directoryVersion);

            String dashDirectoryDescription = m.group(3);
            Assert.assertEquals(" - abc_zyz 123.789 - cool", dashDirectoryDescription);

            String directoryDescription = m.group(4);
            Assert.assertEquals("abc_zyz 123.789 - cool", directoryDescription);
        }
        {
            String fileName = m.group(5);
            Assert.assertEquals("1.0.0.1.sql", fileName);

            String fileVersion = m.group(6);
            Assert.assertEquals("1.0.0.1", fileVersion);

            String dashFileDescription = m.group(7);
            Assert.assertEquals(null, dashFileDescription);

            String fileDescription = m.group(8);
            Assert.assertEquals(null, fileDescription);
        }
    }


    @Test
    public void pattern6() {
        //setup
        String s = "1.0.0 - abc_zyz 123.789 - cool/1.0.0.1 - description.sql";

        // action
        Matcher m = pattern.matcher(s);

        // assert
        Assert.assertTrue(m.matches());
        Assert.assertEquals(8, m.groupCount());
        {
            String directoryName = m.group(1);
            Assert.assertEquals("1.0.0 - abc_zyz 123.789 - cool", directoryName);

            String directoryVersion = m.group(2);
            Assert.assertEquals("1.0.0", directoryVersion);

            String dashDirectoryDescription = m.group(3);
            Assert.assertEquals(" - abc_zyz 123.789 - cool", dashDirectoryDescription);

            String directoryDescription = m.group(4);
            Assert.assertEquals("abc_zyz 123.789 - cool", directoryDescription);
        }
        {
            String fileName = m.group(5);
            Assert.assertEquals("1.0.0.1 - description.sql", fileName);

            String fileVersion = m.group(6);
            Assert.assertEquals("1.0.0.1", fileVersion);

            String dashFileDescription = m.group(7);
            Assert.assertEquals(" - description", dashFileDescription);

            String fileDescription = m.group(8);
            Assert.assertEquals("description", fileDescription);
        }
    }

    @Test
    public void pattern7() {
        //setup
        String s = "1.0.0 - abc_zyz 123.789 - cool/1.0.0.1- description.sql";

        // action
        Matcher m = pattern.matcher(s);

        // assert
        Assert.assertTrue(m.matches());
        Assert.assertEquals(8, m.groupCount());
        {
            String directoryName = m.group(1);
            Assert.assertEquals("1.0.0 - abc_zyz 123.789 - cool", directoryName);

            String directoryVersion = m.group(2);
            Assert.assertEquals("1.0.0", directoryVersion);

            String dashDirectoryDescription = m.group(3);
            Assert.assertEquals(" - abc_zyz 123.789 - cool", dashDirectoryDescription);

            String directoryDescription = m.group(4);
            Assert.assertEquals("abc_zyz 123.789 - cool", directoryDescription);
        }
        {
            String fileName = m.group(5);
            Assert.assertEquals("1.0.0.1- description.sql", fileName);

            String fileVersion = m.group(6);
            Assert.assertEquals("1.0.0.1", fileVersion);

            String dashFileDescription = m.group(7);
            Assert.assertEquals("- description", dashFileDescription);

            String fileDescription = m.group(8);
            Assert.assertEquals("description", fileDescription);
        }
    }

    @Test
    public void pattern8() {
        //setup
        String s = "1.0.0/1.0.0.1-description.sql";

        // action
        Matcher m = pattern.matcher(s);

        // assert
        Assert.assertTrue(m.matches());
        Assert.assertEquals(8, m.groupCount());
        {
            String directoryName = m.group(1);
            Assert.assertEquals("1.0.0", directoryName);

            String directoryVersion = m.group(2);
            Assert.assertEquals("1.0.0", directoryVersion);

            String dashDirectoryDescription = m.group(3);
            Assert.assertEquals(null, dashDirectoryDescription);

            String directoryDescription = m.group(4);
            Assert.assertEquals(null, directoryDescription);
        }
        {
            String fileName = m.group(5);
            Assert.assertEquals("1.0.0.1-description.sql", fileName);

            String fileVersion = m.group(6);
            Assert.assertEquals("1.0.0.1", fileVersion);

            String dashFileDescription = m.group(7);
            Assert.assertEquals("-description", dashFileDescription);

            String fileDescription = m.group(8);
            Assert.assertEquals("description", fileDescription);
        }
    }

    @Test
    public void pattern9() {
        //setup
        String s = "1.0.0/1.0.0.1-abc_zyz 123.789 - cool.sql";

        // action
        Matcher m = pattern.matcher(s);

        // assert
        Assert.assertTrue(m.matches());
        Assert.assertEquals(8, m.groupCount());
        {
            String directoryName = m.group(1);
            Assert.assertEquals("1.0.0", directoryName);

            String directoryVersion = m.group(2);
            Assert.assertEquals("1.0.0", directoryVersion);

            String dashDirectoryDescription = m.group(3);
            Assert.assertEquals(null, dashDirectoryDescription);

            String directoryDescription = m.group(4);
            Assert.assertEquals(null, directoryDescription);
        }
        {
            String fileName = m.group(5);
            Assert.assertEquals("1.0.0.1-abc_zyz 123.789 - cool.sql", fileName);

            String fileVersion = m.group(6);
            Assert.assertEquals("1.0.0.1", fileVersion);

            String dashFileDescription = m.group(7);
            Assert.assertEquals("-abc_zyz 123.789 - cool", dashFileDescription);

            String fileDescription = m.group(8);
            Assert.assertEquals("abc_zyz 123.789 - cool", fileDescription);
        }
    }


    @Test
    public void pattern10() {
        //setup
        String s = "111.2222.333- some_really bad descripTion/11111.222222.33333.44444-abc_zyz 123.789 - cool.sql";

        // action
        Matcher m = pattern.matcher(s);

        // assert
        Assert.assertTrue(m.matches());
        Assert.assertEquals(8, m.groupCount());
        {
            String directoryName = m.group(1);
            Assert.assertEquals("111.2222.333- some_really bad descripTion", directoryName);

            String directoryVersion = m.group(2);
            Assert.assertEquals("111.2222.333", directoryVersion);

            String dashDirectoryDescription = m.group(3);
            Assert.assertEquals("- some_really bad descripTion", dashDirectoryDescription);

            String directoryDescription = m.group(4);
            Assert.assertEquals("some_really bad descripTion", directoryDescription);
        }
        {
            String fileName = m.group(5);
            Assert.assertEquals("11111.222222.33333.44444-abc_zyz 123.789 - cool.sql", fileName);

            String fileVersion = m.group(6);
            Assert.assertEquals("11111.222222.33333.44444", fileVersion);

            String dashFileDescription = m.group(7);
            Assert.assertEquals("-abc_zyz 123.789 - cool", dashFileDescription);

            String fileDescription = m.group(8);
            Assert.assertEquals("abc_zyz 123.789 - cool", fileDescription);
        }
    }
}
