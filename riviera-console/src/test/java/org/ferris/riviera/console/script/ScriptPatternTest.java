package org.ferris.riviera.console.script;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
    public void matches() {
        // true matches
        List<String> strings = new LinkedList<>();
        Collections.addAll(strings
            , "1.0.0/1.0.0.1.sql"
            , "1.0.0 - description/1.0.0.1.sql"
            , "1.0.0- description/1.0.0.1.sql"
            , "1.0.0-description/1.0.0.1.sql"
            , "1.0.0 - abc_zyz 123.789 - cool/1.0.0.1.sql"
            , "1.0.0 - abc_zyz 123.789 - cool/1.0.0.1 - description.sql"
            , "1.0.0 - abc_zyz 123.789 - cool/1.0.0.1- description.sql"
            , "1.0.0/1.0.0.1-description.sql"
            , "1.0.0/1.0.0.1-abc_zyz 123.789-   cool.sql"
            , "111.2222.333- some_really bad descripTion/11111.222222.33333.44444-abc_zyz 123.789 - cool.sql"
        );

        // action & assert
        strings.forEach(s -> {
            Matcher m = pattern.matcher(s);
            Assert.assertTrue(String.format("Pattern doesn't match \"%s\"",s), m.matches());
        });

        // false matches
        strings = new LinkedList<>();
        Collections.addAll(strings
            , "1.0.0 - /1.0.0.1.sql"
            , "1.0.0-/1.0.0.1.sql"
            , "1.0.0 -/1.0.0.1.sql"
            , "1.0.0- /1.0.0.1.sql"
        );
        // action & assert
        strings.forEach(s -> {
            Matcher m = pattern.matcher(s);
            Assert.assertFalse(String.format("Pattern matches \"%s\" but shouldn't",s), m.matches());
        });
    }

    @Test
    public void allGroups() {
        // setup
        String s =
            "111.2222.333- some_really bad descripTion/11111.222222.33333.44444-abc_zyz 123.789 - cool.sql";

        // action
        Matcher m
            = pattern.matcher(s);

        // validate
        Assert.assertEquals(6, m.groupCount());

        // [0] Entire string        111.2222.333- some_really bad descripTion/11111.222222.33333.44444-abc_zyz 123.789 - cool.sql
        Assert.assertEquals("111.2222.333- some_really bad descripTion/11111.222222.33333.44444-abc_zyz 123.789 - cool.sql", m.group(0));

        // [1] Directory name       111.2222.333- some_really bad descripTion
        Assert.assertEquals("111.2222.333- some_really bad descripTion", m.group(1));

        // [2] Directory version    111.2222.333
        Assert.assertEquals("111.2222.333", m.group(2));

        // [3] Directory title      some_really bad descripTion
        Assert.assertEquals("some_really bad descripTion", m.group(3));

        // [4] File name            11111.222222.33333.44444-abc_zyz 123.789 - cool.sql
        Assert.assertEquals("11111.222222.33333.44444-abc_zyz 123.789 - cool.sql", m.group(4));

        // [5] File version         11111.222222.33333.44444
        Assert.assertEquals("11111.222222.33333.44444", m.group(5));

        // [6] File title           abc_zyz 123.789 - cool
        Assert.assertEquals("abc_zyz 123.789 - cool", m.group(6));

    }

    @Test
    public void minimalGroups() {
        // setup
        String s =
            "1.0.0/1.0.0.10.sql";

        // action
        Matcher m
            = pattern.matcher(s);

        // validate
        Assert.assertEquals(6, m.groupCount());

        // [0] Entire string        1.0.0/1.0.0.10.sql
        Assert.assertEquals("1.0.0/1.0.0.10.sql", m.group(0));

        // [1] Directory name       1.0.0
        Assert.assertEquals("1.0.0", m.group(1));

        // [2] Directory version    1.0.0
        Assert.assertEquals("1.0.0", m.group(2));

        // [3] Directory title      null
        Assert.assertNull(m.group(3));

        // [4] File name            1.0.0.10.sql
        Assert.assertEquals("1.0.0.10.sql", m.group(4));

        // [5] File version         1.0.0.10
        Assert.assertEquals("1.0.0.10", m.group(5));

        // [6] File title           null
        Assert.assertNull( m.group(6));
    }

}
