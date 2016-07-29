package org.ferris.riviera.console.research;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author student
 */
public class RegexMatching {

    String directoryNameRegex, fileNameRegex, jarEntryRegex;

    @Before
    public void before() {
        directoryNameRegex 
            = "((\\d+.{1}\\d+.{1}\\d+)(\\s+-\\s+(.+))?)";
        
        fileNameRegex
            = "((\\d+.{1}\\d+.{1}\\d+.{1}\\d+)(\\s+-\\s+(.+))?\\.sql)";
        
        jarEntryRegex
            = directoryNameRegex + "/" + fileNameRegex;
    }
    
    
    @Test
    public void jarEntrySimplest() {
        String s1 = "1.0.0/1.0.0.1.sql";
        
        Pattern p = Pattern.compile(jarEntryRegex);
        Matcher m = p.matcher(s1);
        
        Assert.assertTrue(m.matches());
        Assert.assertEquals(8, m.groupCount());
        
        {
            String directoryName = m.group(1);
            Assert.assertEquals("1.0.0", directoryName);

            String directoryVersion = m.group(2);
            Assert.assertEquals("1.0.0", directoryVersion);

            String directoryDescription = m.group(4);
            Assert.assertEquals(null, directoryDescription);
        }
        {
            String fileName = m.group(5);
            Assert.assertEquals("1.0.0.1.sql", fileName);

            String fileVersion = m.group(6);
            Assert.assertEquals("1.0.0.1", fileVersion);

            String fileDescription = m.group(8);
            Assert.assertEquals(null, fileDescription);
        }
    }
    
    @Test
    public void jarEntryMostComplicated() {
        String s1 = "133.12.131 - here is a cool release/133.12.131.5555 - wow this is a rough name.sql";

        Pattern p = Pattern.compile(jarEntryRegex);
        Matcher m = p.matcher(s1);
        
        Assert.assertTrue(m.matches());
        Assert.assertEquals(8, m.groupCount());

        {
            String directoryName = m.group(1);
            Assert.assertEquals("133.12.131 - here is a cool release", directoryName);

            String directoryVersion = m.group(2);
            Assert.assertEquals("133.12.131", directoryVersion);

            String directoryDescription = m.group(4);
            Assert.assertEquals("here is a cool release", directoryDescription);
        }
        {
            String fileName = m.group(5);
            Assert.assertEquals("133.12.131.5555 - wow this is a rough name.sql", fileName);

            String fileVersion = m.group(6);
            Assert.assertEquals("133.12.131.5555", fileVersion);

            String fileDescription = m.group(8);
            Assert.assertEquals("wow this is a rough name", fileDescription);
        }
    }
}
