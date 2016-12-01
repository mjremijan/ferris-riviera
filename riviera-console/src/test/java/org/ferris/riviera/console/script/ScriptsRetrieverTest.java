package org.ferris.riviera.console.script;

import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@RunWith(MockitoJUnitRunner.class)
public class ScriptsRetrieverTest {

    @Mock
    Logger logMock;

    @Mock
    ScriptBuilder builder;

    @Mock
    ConnectionHandler hander;

    @Mock
    ScriptPattern pattern;

    protected ScriptsRetriever retriever;

    @Before
    public void before() {
        retriever = new ScriptsRetriever();
    }


    @Test
    public void retrieveFromDB() {
        // setup

        // action

        // verify
    }
}
