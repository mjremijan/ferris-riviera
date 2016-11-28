
package org.ferris.riviera.console.script;

import java.util.Calendar;
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
            = new Script(1, 2, 3, 4, "some name", Calendar.getInstance().getTime());

        Assert.assertEquals(314432287, s1.hashCode());
    }

    @Test
    public void testToString() {
        Script s1
            = new Script(1, 0, 0, 0, "some name", Calendar.getInstance().getTime());

        Assert.assertEquals("some name", s1.toString());
    }


    @Test
    public void equals() {
        Script s1
            = new Script(1, 0, 0, 0, "name", Calendar.getInstance().getTime());
        Assert.assertEquals(s1,s1);

        Script s2
            = new Script(1, 0, 0, 0, "name", Calendar.getInstance().getTime());
        Assert.assertEquals(s1, s2);

        Assert.assertFalse(s1.equals(null));

        Assert.assertFalse(s1.equals("foo"));
    }

    @Test
    public void notEquals() {
        Script s1
            = new Script(1, 0, 0, 0, "name", Calendar.getInstance().getTime());
        Script s2
            = new Script(1, 0, 0, 1, "name", Calendar.getInstance().getTime());

        Assert.assertNotEquals(s1, s2);
    }

    @Test
    public void compare() {

        LinkedList<Script> list = new LinkedList<>();

        list.add(new Script(1, 4, 1, 3, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 0, 2, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 1, 0, 2, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 1, 1, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 0, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 0, 4, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 1, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(3, 0, 0, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(2, 0, 0, 1, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 1, 1, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 1, 2, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 4, 1, 2, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 1, 0, 1, "name", Calendar.getInstance().getTime()));
        list.add(new Script(2, 1, 0, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 2, 0, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 3, 0, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 4, 0, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 0, 3, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 4, 0, 2, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 4, 0, 1, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 4, 1, 4, "name", Calendar.getInstance().getTime()));
        list.add(new Script(2, 0, 0, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 4, 1, 1, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 0, 5, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 0, 5, "name", Calendar.getInstance().getTime()));

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

