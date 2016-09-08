package org.ferris.riviera.console.connection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ConnectionProxy implements InvocationHandler {

    protected Connection connection;

    protected ConnectionProxy(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;
        if (!method.getName().equals("close")) {
            try {
                result = method.invoke(connection, args);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
