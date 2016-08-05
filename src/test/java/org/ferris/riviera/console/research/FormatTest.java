/*
 * Copyright 2016 The Ferris Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ferris.riviera.console.research;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class FormatTest {

    String versionFormat;
    String fileNameFormat;
    SimpleDateFormat timestampFormat;
    String combinedFormat;

    @Before
    public void before() {
        versionFormat
            = "%d.%d.%d.%d";

        timestampFormat
            = new SimpleDateFormat("E, dd MMM yyyy, hh:mm a");

        fileNameFormat
            = "%s";

        combinedFormat
            = "%-8s on (%s) \"%s\"";
    }

    @Test
    public void combinedWithBuildNumberDoublDigit() {
        GregorianCalendar gc
            = new GregorianCalendar(2007, Calendar.DECEMBER, 12, 17, 5);

        Assert.assertEquals(
            "1.0.0.99 on (Wed, 12 Dec 2007, 05:05 PM) \"1.0.0.99 - First.sql\""
            ,
            String.format(
                combinedFormat
                , String.format(versionFormat, 1,0,0,99)
                , timestampFormat.format(gc.getTime())
                , String.format(fileNameFormat, "1.0.0.99 - First.sql")
            )
        );
    }

    @Test
    public void combinedWithBuildNumberSingleDigit() {
        GregorianCalendar gc
            = new GregorianCalendar(2007, Calendar.DECEMBER, 12, 17, 5);

        Assert.assertEquals(
            "1.0.0.0  on (Wed, 12 Dec 2007, 05:05 PM) \"1.0.0.0 - First.sql\""
            ,
            String.format(
                combinedFormat
                , String.format(versionFormat, 1,0,0,0)
                , timestampFormat.format(gc.getTime())
                , String.format(fileNameFormat, "1.0.0.0 - First.sql")
            )
        );
    }

    @Test
    public void filename() {
        GregorianCalendar gc
            = new GregorianCalendar(2007, Calendar.DECEMBER, 12, 17, 5);

        Assert.assertEquals(
            "1.0.0.0 - First ddl change.sql", String.format(fileNameFormat, "1.0.0.0 - First ddl change.sql"));
    }

    @Test
    public void timestamp() {
        // Wed, 12 Dec 2007, 05:05 PM
        GregorianCalendar gc
            = new GregorianCalendar(2007, Calendar.DECEMBER, 12, 17, 5);

        Assert.assertEquals("Wed, 12 Dec 2007, 05:05 PM", timestampFormat.format(gc.getTime()));
    }

    @Test
    public void version() {
        // 1.0.0.0
        Assert.assertEquals("1.1.1.1", String.format(versionFormat, 1,1,1,1));
    }



}
