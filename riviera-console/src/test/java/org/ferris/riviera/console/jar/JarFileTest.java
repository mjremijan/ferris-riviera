package org.ferris.riviera.console.jar;

import org.ferris.riviera.console.jarentry.JarEntry;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@RunWith(MockitoJUnitRunner.class)
public class JarFileTest {

    @Mock
    Logger logMock;

    @Test
    public void test_getComplement() throws Exception {
        // setup
        JarFile jf
            = new JarFile(new File("src/test/misc/ddl-1.0-SNAPSHOT.jar"));
        LinkedList<String> ll
            = new LinkedList<>();
        ll.add("1.0.0.1");
        ll.add("1.0.0.2");

        // action
        List<JarEntry> entries
            = jf.getComplement(ll);

        // assert
        Assert.assertEquals(2, entries.size());
        Assert.assertEquals("1.0.0.3", entries.get(0).getVersion());
        Assert.assertEquals("1.0.1.1", entries.get(1).getVersion());
    }
}
