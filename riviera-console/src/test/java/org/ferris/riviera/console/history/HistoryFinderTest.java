package org.ferris.riviera.console.history;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.log4j.Logger;
import org.ferris.riviera.console.connection.ConnectionHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan  
 */
@RunWith(MockitoJUnitRunner.class)
public class HistoryFinderTest {

    @Mock
    Logger logMock;

    @Mock
    ConnectionHandler handlerMock;

    @Mock
    Connection connMock;

    @Mock
    Statement stmtMock;

    @Mock
    ResultSet rsMock;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();


    HistoryFinder finder;
    HistoryFinderEvent event;

    @Before
    public void before() throws Exception {
        event = new HistoryFinderEvent();
        finder = new HistoryFinder();
        finder.log = logMock;
        finder.handler = handlerMock;
        Mockito.when(finder.handler.getConnection()).thenReturn(connMock);
        Mockito.when(connMock.createStatement()).thenReturn(stmtMock);
        Mockito.when(stmtMock.executeQuery(Mockito.anyString())).thenReturn(rsMock);
    }


    @Test
    public void ResultSet_builds_object_with_no_errors() throws SQLException {
        // setup
        AtomicBoolean called = new AtomicBoolean();
        called.set(false);

        Mockito.when(rsMock.next()).then(i -> {
            if (!called.get()) {
                called.set(true);
                return true;
            } else {
                return false;
            }
        });

        Mockito.when(rsMock.getString("RELEASE_VERSION")).thenReturn("1.2.3");
        Mockito.when(rsMock.getString("RELEASE_TITLE")).thenReturn("release title");
        Mockito.when(rsMock.getInt("MAJOR")).thenReturn(1);
        Mockito.when(rsMock.getInt("FEATURE")).thenReturn(2);
        Mockito.when(rsMock.getInt("BUG")).thenReturn(3);
        Mockito.when(rsMock.getInt("BUILD")).thenReturn(4);
        Mockito.when(rsMock.getString("FILE_NAME")).thenReturn("rs_mock_file - cool.sql");
        Mockito.when(rsMock.getString("FILE_DESCRIPTION")).thenReturn("cool");
        Mockito.when(rsMock.getTimestamp("APPLIED_ON")).thenReturn(new Timestamp(123123123));

        // action
        finder.retrieveScriptsFromDatabaseOrderedAscending(event);

        // verify
        HistoryList list = event.getHistory();
        {
            Assert.assertNotNull(list);
            Assert.assertEquals(1, list.size());
        }

        History s = list.get(0);
        {
            Assert.assertEquals("1.2.3", s.getReleaseVersion());
            Assert.assertEquals("release title", s.getReleaseTitle());
            Assert.assertEquals(1, s.getMajor().intValue());
            Assert.assertEquals(2, s.getFeature().intValue());
            Assert.assertEquals(3, s.getBug().intValue());
            Assert.assertEquals(4, s.getBuild().intValue());
            Assert.assertEquals("rs_mock_file - cool.sql", s.getFileName());
            Assert.assertEquals("cool", s.getFileDescription());
            Assert.assertEquals(123123123, s.getAppliedOn().getTime());
        }
    }

    @Test
    public void RuntimeException_rethrown_if_SQLException() throws SQLException {
        // setup
        Mockito.when(rsMock.next()).thenThrow(new SQLException("junit test"));
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("junit test");

        // action
        finder.retrieveScriptsFromDatabaseOrderedAscending(event);
    }
}
