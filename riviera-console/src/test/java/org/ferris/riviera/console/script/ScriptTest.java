
package org.ferris.riviera.console.script;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.reflect.FieldUtils;
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
    public void field_annotation_list_for_releaseVersion() {
        // setup
        Field field = FieldUtils.getField(Script.class, "releaseVersion", true);

        // assert
        Assert.assertNotNull(field);
        AssertAnnotations.forField(field, NotNull.class, Size.class);
    }

    @Test
    public void field_annotation_values_for_releaseVersion() {
        // setup
        Field field = FieldUtils.getField(Script.class, "releaseVersion", true);

        // action
        Size size = field.getAnnotation(Size.class);
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert
        Assert.assertNotNull(size);
        Assert.assertEquals(
            "@javax.validation.constraints.Size(groups=[], min=1, message={javax.validation.constraints.Size.message}, max=8, payload=[])"
            , size.toString());
        Assert.assertNotNull(notNull);
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={javax.validation.constraints.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
    }

    @Test
    public void field_annotation_list_for_releaseTitle() {
        // setup
        Field field = FieldUtils.getField(Script.class, "releaseTitle", true);

        // assert
        Assert.assertNotNull(field);
        AssertAnnotations.forField(field, Size.class);
    }

    @Test
    public void field_annotation_values_for_releaseTitle() {
        // setup
        Field field = FieldUtils.getField(Script.class, "releaseTitle", true);

        // action
        Size size = field.getAnnotation(Size.class);

        // assert
        Assert.assertNotNull(size);
        Assert.assertEquals(
            "@javax.validation.constraints.Size(groups=[], min=0, message={javax.validation.constraints.Size.message}, max=50, payload=[])"
            , size.toString());
    }

    @Test
    public void field_annotation_list_for_major() {
        // setup
        Field field = FieldUtils.getField(Script.class, "major", true);

        // assert
        Assert.assertNotNull(field);
        AssertAnnotations.forField(field, NotNull.class);
    }

    @Test
    public void field_annotation_values_for_major() {
        // setup
        Field field = FieldUtils.getField(Script.class, "major", true);

        // action
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert
        Assert.assertNotNull(notNull);
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={javax.validation.constraints.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
    }

    @Test
    public void field_annotation_list_for_feature() {
        // setup
        Field field = FieldUtils.getField(Script.class, "feature", true);

        // assert
        Assert.assertNotNull(field);
        AssertAnnotations.forField(field, NotNull.class);
    }

    @Test
    public void field_annotation_values_for_feature() {
        // setup
        Field field = FieldUtils.getField(Script.class, "feature", true);

        // action
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert
        Assert.assertNotNull(notNull);
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={javax.validation.constraints.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
    }

    @Test
    public void field_annotation_list_for_bug() {
        // setup
        Field field = FieldUtils.getField(Script.class, "bug", true);

        // assert
        Assert.assertNotNull(field);
        AssertAnnotations.forField(field, NotNull.class);
    }

    @Test
    public void field_annotation_values_for_bug() {
        // setup
        Field field = FieldUtils.getField(Script.class, "bug", true);

        // action
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert
        Assert.assertNotNull(notNull);
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={javax.validation.constraints.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
    }


    @Test
    public void field_annotation_list_for_build() {
        // setup
        Field field = FieldUtils.getField(Script.class, "build", true);

        // assert
        Assert.assertNotNull(field);
        AssertAnnotations.forField(field, NotNull.class);
    }

    @Test
    public void field_annotation_values_for_build() {
        // setup
        Field field = FieldUtils.getField(Script.class, "build", true);

        // action
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert
        Assert.assertNotNull(notNull);
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={javax.validation.constraints.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
    }

    @Test
    public void field_annotation_list_for_fileName() {
        // setup
        Field field = FieldUtils.getField(Script.class, "fileName", true);

        // assert
        Assert.assertNotNull(field);
        AssertAnnotations.forField(field, Size.class, NotNull.class);
    }

    @Test
    public void field_annotation_values_for_fileName() {
        // setup
        Field field = FieldUtils.getField(Script.class, "fileName", true);

        // action
        Size size = field.getAnnotation(Size.class);
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert
        Assert.assertNotNull(size);
        Assert.assertEquals(
            "@javax.validation.constraints.Size(groups=[], min=1, message={javax.validation.constraints.Size.message}, max=100, payload=[])"
            , size.toString());
        Assert.assertNotNull(notNull);
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={javax.validation.constraints.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
    }

    @Test
    public void field_annotation_list_for_fileDescription() {
        // setup
        Field field = FieldUtils.getField(Script.class, "fileDescription", true);

        // assert
        Assert.assertNotNull(field);
        AssertAnnotations.forField(field, Size.class);
    }

    @Test
    public void field_annotation_values_for_fileDescription() {
        // setup
        Field field = FieldUtils.getField(Script.class, "fileDescription", true);

        // action
        Size size = field.getAnnotation(Size.class);

        // assert
        Assert.assertNotNull(size);
        Assert.assertEquals(
            "@javax.validation.constraints.Size(groups=[], min=1, message={javax.validation.constraints.Size.message}, max=50, payload=[])"
            , size.toString());
    }

    @Test
    public void field_annotation_list_for_appliedOn() {
        // setup
        Field field = FieldUtils.getField(Script.class, "appliedOn", true);

        // assert
        Assert.assertNotNull(field);
        AssertAnnotations.forField(field, NotNull.class);
    }

    @Test
    public void field_annotation_values_for_appliedOn() {
        // setup
        Field field = FieldUtils.getField(Script.class, "appliedOn", true);

        // action
        NotNull notNull = field.getAnnotation(NotNull.class);

        // assert
        Assert.assertNotNull(notNull);
        Assert.assertEquals(
            "@javax.validation.constraints.NotNull(message={javax.validation.constraints.NotNull.message}, groups=[], payload=[])"
            , notNull.toString());
    }
}

