
package org.ferris.riviera.console.history;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class HistoryTest {

    @Test
    public void testHashCode() {
        // setup
        History s1
            = new History(null, null, 1, 2, 3, 4, null, null, null);
        // validate
        Assert.assertEquals(31914243L, s1.hashCode());
    }

    @Test
    public void testToString() {
        // setup
        History s1
            = new History(
                  null
                , null
                , null
                , null
                , null
                , null
                , "1.2.3.4 - some name.sql"
                , null
                , null);
        // validate
        Assert.assertEquals("1.2.3.4 - some name.sql", s1.toString());
    }


    @Test
    public void test_equals() {
        // setup
        @SuppressWarnings("UnnecessaryBoxing")
        History s1
            = new History(null, null, new Integer(1), new Integer(0), new Integer(0), new Integer(0), null, null, null);
        @SuppressWarnings("UnnecessaryBoxing")
        History s2
            = new History(null, null, new Integer(1), new Integer(0), new Integer(0), new Integer(0), null, null, null);
        // validate
        Assert.assertEquals(s1,s1);
        Assert.assertEquals(s2,s2);
    }

    @Test
    public void test_not_equals() {
        // prepare
        @SuppressWarnings("UnnecessaryBoxing")
        History s1
            = new History(null, null, new Integer(1), new Integer(0), new Integer(0), new Integer(0), null, null, null);
        @SuppressWarnings("UnnecessaryBoxing")
        History s2
            = new History(null, null, new Integer(2), new Integer(0), new Integer(0), new Integer(0), null, null, null);

        // validate
        Assert.assertNotEquals(s1, s2);
        Assert.assertNotEquals(s1, null);
        Assert.assertNotEquals(s1, "foo");
    }
}

