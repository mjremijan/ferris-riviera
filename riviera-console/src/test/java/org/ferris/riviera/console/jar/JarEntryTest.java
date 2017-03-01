package org.ferris.riviera.console.jar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.ferris.riviera.console.junit.AssertAnnotations;
import org.junit.Assert;
import org.junit.BeforeClass;
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

    private static Properties validationMessages;

    @BeforeClass
    public static void before() throws FileNotFoundException, IOException {
        validationMessages = new Properties();
        validationMessages.load(new FileInputStream("src/main/resources/ValidationMessages.properties"));
    }


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
            , "111.2222.333- some_really bad descripTion/111.2222.333.44444-abc_zyz 123.789 - cool.sql"
            , "200.0.0     -       spaces/200.0.0.1-            spaces.sql"
        );

        // action & assert
        strings.forEach(s -> {
            JarEntry j = new JarEntry(s);
            Assert.assertTrue(String.format("Pattern doesn't match \"%s\"",s), j.matches());
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
            JarEntry j = new JarEntry(s);
            Assert.assertFalse(String.format("Pattern matches \"%s\" but shouldn't",s), j.matches());
        });
    }


    @Test
    public void allGroups() {
        // setup
        String s =
            "111.2222.333-          some_really bad descripTion/111.2222.333.44444  -          abc_zyz 123.789 - cool.sql";

        // action
        JarEntry m = new JarEntry(s);

        // [0] Entire string        111.2222.333-          some_really bad descripTion/11111.222222.33333.44444  -          abc_zyz 123.789 - cool.sql
        Assert.assertEquals("111.2222.333-          some_really bad descripTion/111.2222.333.44444  -          abc_zyz 123.789 - cool.sql", m.getName());

        // [2] Directory version    111.2222.333
        Assert.assertEquals("111.2222.333", m.getReleaseVersion());

        // [3] Directory title      some_really bad descripTion
        Assert.assertEquals("some_really bad descripTion", m.getReleaseTitle());

        // [4] File name            111.2222.333.44444  -          abc_zyz 123.789 - cool.sql
        Assert.assertEquals("111.2222.333.44444  -          abc_zyz 123.789 - cool.sql", m.getFileName());

        // [5] File version         111.2222.333.44444
        Assert.assertEquals("111.2222.333.44444", m.getVersion());

        // [6] File title           abc_zyz 123.789 - cool
        Assert.assertEquals("abc_zyz 123.789 - cool", m.getFileDescription());
    }


    @Test
    public void minimalGroups() {
        // setup
        String s =
            "1.0.0/1.0.0.10.sql";

        // action
        JarEntry m = new JarEntry(s);

        // validate

        // [0] Entire string        1.0.0/1.0.0.10.sql
        Assert.assertEquals("1.0.0/1.0.0.10.sql", m.getName());

        // [2] Directory version    1.0.0
        Assert.assertEquals("1.0.0", m.getReleaseVersion());

        // [3] Directory title      null
        Assert.assertNull(m.getReleaseTitle());

        // [4] File name            1.0.0.10.sql
        Assert.assertEquals("1.0.0.10.sql", m.getFileName());

        // [5] File version         1.0.0.10
        Assert.assertEquals("1.0.0.10", m.getVersion());

        // [6] File title           null
        Assert.assertNull(m.getFileDescription());
    }


    @Test
    public void compare() {

        LinkedList<JarEntry> list = new LinkedList<>();

        list.add(new JarEntry("1.4.1/1.4.1.3.sql"));
        list.add(new JarEntry("1.0.0/1.0.0.2.sql"));
        list.add(new JarEntry("1.1.0/1.1.0.2.sql"));
        list.add(new JarEntry("1.1.1/1.1.1.0.sql"));
        list.add(new JarEntry("1.0.0/1.0.0.0.sql"));
        list.add(new JarEntry("1.0.0/1.0.0.4.sql"));
        list.add(new JarEntry("1.0.1/1.0.1.0.sql"));
        list.add(new JarEntry("3.0.0/3.0.0.0.sql"));
        list.add(new JarEntry("2.0.0/2.0.0.1.sql"));
        list.add(new JarEntry("1.0.1/1.0.1.1.sql"));
        list.add(new JarEntry("1.0.1/1.0.1.2.sql"));
        list.add(new JarEntry("1.4.1/1.4.1.2.sql"));
        list.add(new JarEntry("1.1.0/1.1.0.1.sql"));
        list.add(new JarEntry("2.1.0/2.1.0.0.sql"));
        list.add(new JarEntry("1.2.0/1.2.0.0.sql"));
        list.add(new JarEntry("1.3.0/1.3.0.0.sql"));
        list.add(new JarEntry("1.4.0/1.4.0.0.sql"));
        list.add(new JarEntry("1.0.0/1.0.0.3.sql"));
        list.add(new JarEntry("1.4.0/1.4.0.2.sql"));
        list.add(new JarEntry("1.4.0/1.4.0.1.sql"));
        list.add(new JarEntry("1.4.1/1.4.1.4.sql"));
        list.add(new JarEntry("2.0.0/2.0.0.0.sql"));
        list.add(new JarEntry("1.4.1/1.4.1.1.sql"));
        list.add(new JarEntry("1.0.0/1.0.0.5.sql"));
        list.add(new JarEntry("1.0.0/1.0.0.5.sql"));

        Collections.sort(list);

        int i = 0;
        JarEntry s;

        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(0, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(0, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(0, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(2, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(0, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(3, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(0, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(4, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(0, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(5, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(0, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(5, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(0, s.getFeature().intValue());
            Assert.assertEquals(1, s.getBug().intValue());
            Assert.assertEquals(0, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(0, s.getFeature().intValue());
            Assert.assertEquals(1, s.getBug().intValue());
            Assert.assertEquals(1, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(0, s.getFeature().intValue());
            Assert.assertEquals(1, s.getBug().intValue());
            Assert.assertEquals(2, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(1, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(1, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(1, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(2, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(1, s.getFeature().intValue());
            Assert.assertEquals(1, s.getBug().intValue());
            Assert.assertEquals(0, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(2, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(0, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(3, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(0, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(4, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(0, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(4, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(1, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(4, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(2, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(4, s.getFeature().intValue());
            Assert.assertEquals(1, s.getBug().intValue());
            Assert.assertEquals(1, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(4, s.getFeature().intValue());
            Assert.assertEquals(1, s.getBug().intValue());
            Assert.assertEquals(2, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(4, s.getFeature().intValue());
            Assert.assertEquals(1, s.getBug().intValue());
            Assert.assertEquals(3, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(4, s.getFeature().intValue());
            Assert.assertEquals(1, s.getBug().intValue());
            Assert.assertEquals(4, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(2, s.getMajor().intValue());
            Assert.assertEquals(0, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(0, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(2, s.getMajor().intValue());
            Assert.assertEquals(0, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(1, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(2, s.getMajor().intValue());
            Assert.assertEquals(1, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(0, s.getBuild().intValue());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(3, s.getMajor().intValue());
            Assert.assertEquals(0, s.getFeature().intValue());
            Assert.assertEquals(0, s.getBug().intValue());
            Assert.assertEquals(0, s.getBuild().intValue());
        }
    }


    @Test
    public void check_field_annotation_list_for_major() {
        // setup
        Field field = FieldUtils.getField(JarEntry.class, "major", true);

        // action
        Max annotation = field.getAnnotation(Max.class);

        // assert
        AssertAnnotations.forField(field, Max.class);
        Assert.assertEquals(
            "@javax.validation.constraints.Max(message={JarEntry.major.Max.message}, groups=[], payload=[], value=99)"
            , annotation.toString());
        Assert.assertTrue(validationMessages.containsKey("JarEntry.major.Max.message"));
    }


    @Test
    public void check_field_annotation_list_for_feature() {
        // setup
        Field field = FieldUtils.getField(JarEntry.class, "feature", true);

        // action
        Max annotation = field.getAnnotation(Max.class);

        // assert
        AssertAnnotations.forField(field, Max.class);
        Assert.assertEquals(
            "@javax.validation.constraints.Max(message={JarEntry.feature.Max.message}, groups=[], payload=[], value=99)"
            , annotation.toString());
        Assert.assertTrue(validationMessages.containsKey("JarEntry.feature.Max.message"));
    }


    @Test
    public void check_field_annotation_list_for_bug() {
        // setup
        Field field = FieldUtils.getField(JarEntry.class, "bug", true);

        // action
        Max annotation = field.getAnnotation(Max.class);

        // assert
        AssertAnnotations.forField(field, Max.class);
        Assert.assertEquals(
            "@javax.validation.constraints.Max(message={JarEntry.bug.Max.message}, groups=[], payload=[], value=99)"
            , annotation.toString());
        Assert.assertTrue(validationMessages.containsKey("JarEntry.bug.Max.message"));
    }


    @Test
    public void check_field_annotation_list_for_build() {
        // setup
        Field field = FieldUtils.getField(JarEntry.class, "build", true);

        // action
        Max annotation = field.getAnnotation(Max.class);

        // assert
        AssertAnnotations.forField(field, Max.class);
        Assert.assertEquals(
            "@javax.validation.constraints.Max(message={JarEntry.build.Max.message}, groups=[], payload=[], value=999)"
            , annotation.toString());
        Assert.assertTrue(validationMessages.containsKey("JarEntry.build.Max.message"));
    }


    @Test
    public void check_field_annotation_list_for_title() {
        // setup
        Field field = FieldUtils.getField(JarEntry.class, "title", true);

        // action
        Size annotation = field.getAnnotation(Size.class);

        // assert
        AssertAnnotations.forField(field, Size.class);
        Assert.assertEquals(
            "@javax.validation.constraints.Size(groups=[], min=0, message={JarEntry.title.Size.message}, max=50, payload=[])"
            , annotation.toString());
        Assert.assertTrue(validationMessages.containsKey("JarEntry.title.Size.message"));
    }


    @Test
    public void check_field_annotation_list_for_fileName() {
        // setup
        Field field = FieldUtils.getField(JarEntry.class, "fileName", true);

        // action
        Size annotation = field.getAnnotation(Size.class);

        // assert
        AssertAnnotations.forField(field, Size.class);
        Assert.assertEquals(
            "@javax.validation.constraints.Size(groups=[], min=1, message={JarEntry.fileName.Size.message}, max=100, payload=[])"
            , annotation.toString());
        Assert.assertTrue(validationMessages.containsKey("JarEntry.fileName.Size.message"));
    }


    @Test
    public void check_field_annotation_list_for_fileDescription() {
        // setup
        Field field = FieldUtils.getField(JarEntry.class, "fileDescription", true);

        // action
        Size annotation = field.getAnnotation(Size.class);

        // assert
        AssertAnnotations.forField(field, Size.class);
        Assert.assertEquals(
            "@javax.validation.constraints.Size(groups=[], min=0, message={JarEntry.fileDescription.Size.message}, max=50, payload=[])"
            , annotation.toString());
        Assert.assertTrue(validationMessages.containsKey("JarEntry.fileDescription.Size.message"));
    }









}
