
package org.ferris.riviera.console.script;

import java.util.Collections;
import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptTest {


    @Test
    public void testHashCode() {
        Script s1
            = new Script(null, null, 1, 2, 3, 4, null, null, null);

        Assert.assertEquals(314432287, s1.hashCode());
    }

    @Test
    public void testToString() {
        Script s1
            = new Script(
                  null
                , null
                , Integer.MIN_VALUE
                , Integer.MIN_VALUE
                , Integer.MIN_VALUE
                , Integer.MIN_VALUE
                , "some name.sql"
                , null
                , null);

        Assert.assertEquals("some name.sql", s1.toString());
    }


    @Test
    public void equals() {
        Script s1
            = new Script(null, null, 1, 0, 0, 0, null, null, null);
        Assert.assertEquals(s1,s1);

        Script s2
            = new Script(null, null, 1, 0, 0, 0, null, null, null);
        Assert.assertEquals(s1, s2);

        Assert.assertFalse(s1.equals(null));

        Assert.assertFalse(s1.equals("foo"));
    }

    @Test
    public void notEquals() {
        Script s1
            = new Script(null, null, 1, 0, 0, 0, null, null, null);
        Script s2
            = new Script(null, null, 1, 0, 0, 1, null, null, null);

        Assert.assertNotEquals(s1, s2);
    }

    @Test
    public void compare() {

        LinkedList<Script> list = new LinkedList<>();

        list.add(new Script(null, null, 1, 4, 1, 3, null, null, null));
        list.add(new Script(null, null, 1, 0, 0, 2, null, null, null));
        list.add(new Script(null, null, 1, 1, 0, 2, null, null, null));
        list.add(new Script(null, null, 1, 1, 1, 0, null, null, null));
        list.add(new Script(null, null, 1, 0, 0, 0, null, null, null));
        list.add(new Script(null, null, 1, 0, 0, 4, null, null, null));
        list.add(new Script(null, null, 1, 0, 1, 0, null, null, null));
        list.add(new Script(null, null, 3, 0, 0, 0, null, null, null));
        list.add(new Script(null, null, 2, 0, 0, 1, null, null, null));
        list.add(new Script(null, null, 1, 0, 1, 1, null, null, null));
        list.add(new Script(null, null, 1, 0, 1, 2, null, null, null));
        list.add(new Script(null, null, 1, 4, 1, 2, null, null, null));
        list.add(new Script(null, null, 1, 1, 0, 1, null, null, null));
        list.add(new Script(null, null, 2, 1, 0, 0, null, null, null));
        list.add(new Script(null, null, 1, 2, 0, 0, null, null, null));
        list.add(new Script(null, null, 1, 3, 0, 0, null, null, null));
        list.add(new Script(null, null, 1, 4, 0, 0, null, null, null));
        list.add(new Script(null, null, 1, 0, 0, 3, null, null, null));
        list.add(new Script(null, null, 1, 4, 0, 2, null, null, null));
        list.add(new Script(null, null, 1, 4, 0, 1, null, null, null));
        list.add(new Script(null, null, 1, 4, 1, 4, null, null, null));
        list.add(new Script(null, null, 2, 0, 0, 0, null, null, null));
        list.add(new Script(null, null, 1, 4, 1, 1, null, null, null));
        list.add(new Script(null, null, 1, 0, 0, 5, null, null, null));
        list.add(new Script(null, null, 1, 0, 0, 5, null, null, null));

        Collections.sort(list);

        int i = 0;
        Script s;

        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(2, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(3, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(4, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(5, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(5, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(1, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(2, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(1, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(1, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(1, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(2, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(1, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(2, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(3, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(1, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(2, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(1, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(2, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(3, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(4, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(2, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(2, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(1, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(2, s.getMajor());
            Assert.assertEquals(1, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(3, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
    }
}

