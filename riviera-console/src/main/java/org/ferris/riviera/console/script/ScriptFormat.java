package org.ferris.riviera.console.script;

import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Singleton
public class ScriptFormat {

    protected String versionFormat;
    protected String fileNameFormat;
    protected SimpleDateFormat timestampFormat;
    protected String combinedFormat;

    @PostConstruct
    protected void postConstruct() {
        versionFormat = "%d.%d.%d.%d";
        timestampFormat = new SimpleDateFormat("E, dd MMM yyyy, hh:mm a");
        fileNameFormat = "%s";
        combinedFormat
            = "%-9s    (%s)    %s";
    }

    public String format(Script s) {
        return
        String.format(
            combinedFormat
            , String.format(versionFormat, s.getMajor(),s.getFeature(),s.getBug(),s.getBuild())
            , timestampFormat.format(s.getAppliedOn())
            , String.format(fileNameFormat, s.getName())
        );
    }
}
