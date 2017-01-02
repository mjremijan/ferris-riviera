
package org.ferris.riviera.console.script;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Properties;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.ferris.riviera.console.script.constraints.ReleaseVersion;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ScriptTest {

    static Properties validationMessages;

    @BeforeClass
    public static void before() throws FileNotFoundException, IOException {
        validationMessages = new Properties();
        validationMessages.load(new FileInputStream("src/main/resources/ValidationMessages.properties"));
    }

    @Test
    public void testHashCode() {
        Script s1
            = new Script(null, null, 1, 2, 3, 4, null, null, null);
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
    public void class_annotation_list() {
        // assert
        AssertAnnotations.forType(Script.class, ReleaseVersion.class);

        // action
        ReleaseVersion releaseVersion = Script.class.getAnnotation(ReleaseVersion.class);
        // assert releaseVersion
        Assert.assertEquals(
            "@org.ferris.riviera.console.script.constraints.ReleaseVersion(message={Script.ReleaseVersion.message}, groups=[], payload=[])"
            , releaseVersion.toString());
        Assert.assertTrue(validationMessages.containsKey("Script.ReleaseVersion.message"));
    }


    @Test
    public void field_annotation_list_for_releaseVersion() {
        // setup
        Field field = FieldUtils.getField(Script.class, "releaseVersion", true);

        // action
        Size size = field.getAnnotation(Size.class);
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert
        AssertAnnotations.forField(field, NotNull.class, Size.class);

        // assert size
        Assert.assertEquals(
            "@javax.validation.constraints.Size(groups=[], min=1, message={Script.releaseVersion.Size.message}, max=8, payload=[])"
            , size.toString());
        Assert.assertTrue(validationMessages.containsKey("Script.releaseVersion.Size.message"));

        // assert notnull
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={Script.releaseVersion.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
        Assert.assertTrue(validationMessages.containsKey("Script.releaseVersion.NotNull.message"));
    }


    @Test
    public void field_annotation_list_for_releaseTitle() {
        // setup
        Field field = FieldUtils.getField(Script.class, "releaseTitle", true);

        // action
        Size size = field.getAnnotation(Size.class);

        // assert
        AssertAnnotations.forField(field, Size.class);
        Assert.assertEquals(
            "@javax.validation.constraints.Size(groups=[], min=0, message={Script.releaseTitle.Size.message}, max=50, payload=[])"
            , size.toString());
        Assert.assertTrue(validationMessages.containsKey("Script.releaseTitle.Size.message"));
    }

    @Test
    public void field_annotation_list_for_major() {
        // setup
        Field field = FieldUtils.getField(Script.class, "major", true);
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert notnull
        AssertAnnotations.forField(field, NotNull.class);
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={Script.major.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
        Assert.assertTrue(validationMessages.containsKey("Script.major.NotNull.message"));
    }

    @Test
    public void field_annotation_list_for_feature() {
        // setup
        Field field = FieldUtils.getField(Script.class, "feature", true);
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert notnull
        AssertAnnotations.forField(field, NotNull.class);
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={Script.feature.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
        Assert.assertTrue(validationMessages.containsKey("Script.feature.NotNull.message"));
    }

    @Test
    public void field_annotation_list_for_bug() {
        // setup
        Field field = FieldUtils.getField(Script.class, "bug", true);
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert notnull
        AssertAnnotations.forField(field, NotNull.class);
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={Script.bug.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
        Assert.assertTrue(validationMessages.containsKey("Script.bug.NotNull.message"));
    }

    @Test
    public void field_annotation_list_for_build() {
        // setup
        Field field = FieldUtils.getField(Script.class, "build", true);
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert notnull
        AssertAnnotations.forField(field, NotNull.class);
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={Script.build.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
        Assert.assertTrue(validationMessages.containsKey("Script.build.NotNull.message"));
    }

    @Test
    public void field_annotation_list_for_fileName() {
        // setup
        Field field = FieldUtils.getField(Script.class, "fileName", true);

        // action
        Size size = field.getAnnotation(Size.class);
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert
        AssertAnnotations.forField(field, Size.class, NotNull.class);

        // assert size
        Assert.assertNotNull(size);
        Assert.assertEquals(
            "@javax.validation.constraints.Size(groups=[], min=1, message={Script.fileName.Size.message}, max=100, payload=[])"
            , size.toString());
        Assert.assertTrue(validationMessages.containsKey("Script.fileName.Size.message"));

        // assert notnull
        Assert.assertNotNull(notNull);
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={Script.fileName.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
        Assert.assertTrue(validationMessages.containsKey("Script.fileName.NotNull.message"));
    }

    @Test
    public void field_annotation_list_for_fileDescription() {
        // setup
        Field field = FieldUtils.getField(Script.class, "fileDescription", true);

        // action
        Size size = field.getAnnotation(Size.class);

        // assert
        AssertAnnotations.forField(field, Size.class);

        // assert size
        Assert.assertEquals(
            "@javax.validation.constraints.Size(groups=[], min=0, message={Script.fileDescription.Size.message}, max=50, payload=[])"
            , size.toString());
        Assert.assertTrue(validationMessages.containsKey("Script.fileDescription.Size.message"));
    }

    @Test
    public void field_annotation_list_for_appliedOn() {
        // setup
        Field field = FieldUtils.getField(Script.class, "appliedOn", true);

        // action
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert
        AssertAnnotations.forField(field, NotNull.class);

        // assert notnull
        Assert.assertNotNull(notNull);
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={Script.appliedOn.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
        Assert.assertTrue(validationMessages.containsKey("Script.appliedOn.NotNull.message"));
    }
}

