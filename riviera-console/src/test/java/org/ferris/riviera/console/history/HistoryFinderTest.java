package org.ferris.riviera.console.history;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

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

    @Before
    public void before() throws Exception {
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
        Mockito.when(rsMock.next()).thenAnswer(new Answer<Boolean>(){
            private Boolean called = Boolean.FALSE;
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                if (called) {
                    return Boolean.FALSE;
                } else {
                    called = Boolean.TRUE;
                    return Boolean.TRUE;
                }
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
        Mockito.when(rsMock.getDate("APPLIED_ON")).thenReturn(new Date(123123123));

        HistoryFinderEvent event
            = new HistoryFinderEvent();

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

//    @Test
//    public void result_set_sqlexception() {
//        // setup
//        ResultSet rsMock = Mockito.mock(ResultSet.class);
//        try {
//            Mockito.when(rsMock.getInt("MAJOR")).thenThrow(new SQLException("junit test"));
//        } catch (SQLException ignore) {
//        }
//        expectedEx.expect(RuntimeException.class);
//        expectedEx.expectMessage("junit test");
//
//        // action
//        builder.setResultSet(rsMock);
//        Script s = builder.build();
//    }
//
//    @Test
//    public void matcher_ok() {
//        // setup
//        Matcher m = new ScriptJarPattern().matcher("1.0.2 - shrubbery/1.0.2.4 - herring.sql");
//
//        // action
//        builder.setMatcher(m);
//        Script s = builder.build();
//
//        // verify
//        Assert.assertEquals("1.0.2", s.getReleaseVersion());
//        Assert.assertEquals("shrubbery", s.getReleaseTitle());
//        Assert.assertEquals(1, s.getMajor().intValue());
//        Assert.assertEquals(0, s.getFeature().intValue());
//        Assert.assertEquals(2, s.getBug().intValue());
//        Assert.assertEquals(4, s.getBuild().intValue());
//        Assert.assertEquals("1.0.2.4 - herring.sql", s.getFileName());
//        Assert.assertEquals("herring", s.getFileDescription());
//        Assert.assertEquals(null, s.getAppliedOn());
//    }
//
//    @Test
//    public void matcher_try() {
//        // setup
//        Matcher m = new ScriptJarPattern().matcher2("1.0.2 - shrubbery/1.0.2.4 - herring.sql");
//        m.matches();
//        Assert.assertEquals("1.0.2 - shrubbery/1.0.2.4 - herring.sql", m.group());
//        Assert.assertEquals("1.0.2.4", m.group(5));
//    }
}
