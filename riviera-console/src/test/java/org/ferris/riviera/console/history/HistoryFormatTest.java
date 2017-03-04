package org.ferris.riviera.console.history;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class HistoryFormatTest {

    HistoryFormat format;

    @Before
    public void before() {
        format = new HistoryFormat();
    }

    @Test
    public void combinedWithBuildNumberDoublDigit() {
        // setup
        GregorianCalendar gc
            = new GregorianCalendar(2007, Calendar.DECEMBER, 12, 17, 5);

        History s = new History(null,null,1,0,0,99,"1.0.0.99 - First.sql",null, new Timestamp(gc.getTime().getTime()));

        // action
        String actual
            = format.format(s);

        // verify
        Assert.assertEquals(
            "1.0.0.99     (Wed, 12 Dec 2007, 05:05:00.000 PM)    1.0.0.99 - First.sql"
            , actual
        );
    }

    @Test
    public void combinedWithBuildNumberSingleDigit() {
        // setup
        GregorianCalendar gc
            = new GregorianCalendar(2007, Calendar.DECEMBER, 12, 17, 5);

        History s = new History(null,null,1,0,0,1,"1.0.0.1 - First.sql",null,new Timestamp(gc.getTime().getTime()));

        // action
        String actual
            = format.format(s);

        // verify
        Assert.assertEquals(
            "1.0.0.1      (Wed, 12 Dec 2007, 05:05:00.000 PM)    1.0.0.1 - First.sql"
            , actual
        );
    }


    @Test
    public void combinedWithFeatureAndBuildNumbersDoubleDigits() {
        // setup
        GregorianCalendar gc
            = new GregorianCalendar(2007, Calendar.DECEMBER, 12, 17, 5);

        History s = new History(null,null,1,15,0,10,"1.15.0.10 - Wow.sql",null,new Timestamp(gc.getTime().getTime()));

        // action
        String actual
            = format.format(s);

        // verify
        Assert.assertEquals(
            "1.15.0.10    (Wed, 12 Dec 2007, 05:05:00.000 PM)    1.15.0.10 - Wow.sql"
            , actual
        );
    }
}
