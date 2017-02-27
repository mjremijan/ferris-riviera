package org.ferris.riviera.console.jar;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *  *
 *  * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 
 */
@RunWith(MockitoJUnitRunner.class)
public class JarEntryTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void parses_a_propertly_formatted_complete_name_ok() {
        // setup
        String name = "1.0.2 - shrubbery/1.0.2.4 - herring.sql";

        // action
        JarEntry s = new JarEntry(name);

        // verify
        Assert.assertEquals("1.0.2", s.getReleaseVersion());
        Assert.assertEquals("shrubbery", s.getReleaseTitle());
        Assert.assertEquals(1, s.getMajor().intValue());
        Assert.assertEquals(0, s.getFeature().intValue());
        Assert.assertEquals(2, s.getBug().intValue());
        Assert.assertEquals(4, s.getBuild().intValue());
        Assert.assertEquals("1.0.2.4 - herring.sql", s.getFileName());
        Assert.assertEquals("herring", s.getFileDescription());
    }


    @Test
    public void parses_a_propertly_formatted_minimal_name_ok() {
        // setup
        String name = "1.0.2/1.0.2.4.sql";

        // action
        JarEntry s = new JarEntry(name);

        // verify
        Assert.assertEquals("1.0.2", s.getReleaseVersion());
        Assert.assertNull(s.getReleaseTitle());
        Assert.assertEquals(1, s.getMajor().intValue());
        Assert.assertEquals(0, s.getFeature().intValue());
        Assert.assertEquals(2, s.getBug().intValue());
        Assert.assertEquals(4, s.getBuild().intValue());
        Assert.assertEquals("1.0.2.4.sql", s.getFileName());
        Assert.assertNull(s.getFileDescription());
    }

    @Test
    public void RuntimeException_thrown_if_directory_and_fileame_versions_dont_match() {
        // setup
        String name = "1.0.99/1.0.2.4.sql";
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Script file version does not match directory version \"1.0.99/1.0.2.4.sql\"");

        // action
        JarEntry s = new JarEntry(name);
    }

}
