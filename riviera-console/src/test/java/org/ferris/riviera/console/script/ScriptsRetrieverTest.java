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
    ScriptBuilder builderMock;

    @Mock
    ConnectionHandler handlerMock;

    @Mock
    ScriptPattern patternMock;

    protected ScriptsRetriever retriever;

    @Before
    public void before() {
        retriever = new ScriptsRetriever();
        retriever.log = logMock;
        retriever.builder = builderMock;
        retriever.handler = handlerMock;
        retriever.pattern = patternMock;
    }


    @Test
    public void retrieveFromDB() {
        // setup


        // action

        // verify
    }
}
