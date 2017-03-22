package org.ferris.riviera.console.jar;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.LinkedList;

/**
 *  *
 *  * @author Michael Remijan mjremijan@yahoo.com @mjremijan  
 */
public class JarEntryStatements extends LinkedList<String> {

    private static final long serialVersionUID = 1989475038247087234L;

    public JarEntryStatements(String fileContents) throws IOException {
        StringReader sr = new StringReader(fileContents + "\n" + ";");
        LineNumberReader reader = new LineNumberReader(sr);
        StringBuilder sp = new StringBuilder();
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            String lineTrimmed = line.trim();
            if (!lineTrimmed.isEmpty()) {
                String lineRemovedCommentsTrimmed
                    = //"(?m)--(.*?)$"
                    //[^,;]
                    lineTrimmed.replaceAll( //"(?m)--(.*?)$"
                        //[^,;]
                        "(?m)--[^']*?$", "").trim();
                if (lineRemovedCommentsTrimmed.isEmpty()) {
                    continue;
                }
                if (';' == lineRemovedCommentsTrimmed.charAt(lineRemovedCommentsTrimmed.length() - 1)) {
                    String lineNoSemicolon = lineRemovedCommentsTrimmed.substring(0, lineRemovedCommentsTrimmed.length() - 1).trim();
                    if (!lineNoSemicolon.isEmpty()) {
                        if (sp.length() > 0) {
                            sp.append("\n");
                        }
                        sp.append(lineNoSemicolon);
                    }
                    if (sp.length() > 0) {
                        super.add(sp.toString());
                        sp.setLength(0);
                    }
                } else {
                    if (sp.length() > 0) {
                        sp.append("\n");
                    }
                    sp.append(lineRemovedCommentsTrimmed);
                }
            }
        }
    }

}
