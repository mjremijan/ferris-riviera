package org.ferris.riviera.console.script;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
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
public class ScriptBuilderTest {

    @Mock
    Logger logMock;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();;

    ScriptBuilder builder;

    @Before
    public void before() {
        builder = new ScriptBuilder();
        builder.log = logMock;
    }

    @Test
    public void result_set_ok() throws SQLException {
        // setup
        ResultSet
            rsMock = Mockito.mock(ResultSet.class);
        Mockito.when(rsMock.getInt("MAJOR")).thenReturn(1);
        Mockito.when(rsMock.getInt("FEATURE")).thenReturn(2);
        Mockito.when(rsMock.getInt("BUG")).thenReturn(3);
        Mockito.when(rsMock.getInt("BUILD")).thenReturn(4);
        Mockito.when(rsMock.getString("NAME")).thenReturn("rs mock");
        Mockito.when(rsMock.getDate("APPLIED_ON")).thenReturn(new Date(123123123));

        // action
        builder.setResultSet(rsMock);
        Script s = builder.build();

        // verify
        Assert.assertEquals(1, s.getMajor());
        Assert.assertEquals(2, s.getFeature());
        Assert.assertEquals(3, s.getBug());
        Assert.assertEquals(4, s.getBuild());
        Assert.assertEquals("rs mock", s.getName());
        Assert.assertEquals(123123123, s.getAppliedOn().getTime());
    }

    @Test
    public void result_set_sqlexception() {
        // setup
        ResultSet
            rsMock = Mockito.mock(ResultSet.class);
        try {
            Mockito.when(rsMock.getInt("MAJOR")).thenThrow(new SQLException("junit test"));
        } catch (SQLException ignore) {}
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("junit test");

        // action
        builder.setResultSet(rsMock);
        Script s = builder.build();
    }

}
