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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.log4j.Logger;
import org.junit.Assert;
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
public class JarFilesAndClassLoadersTest {

    private static final Class<?>[] parameters = new Class[]{URL.class};
    
    private static void addURL(URL u) throws IOException {
        URLClassLoader sysloader = (URLClassLoader)ClassLoader.getSystemClassLoader();
        Class<?> sysclass = URLClassLoader.class;
        try {
            Method method = sysclass.getDeclaredMethod("addURL",parameters);
            method.setAccessible(true);
            method.invoke(sysloader,new Object[]{ u }); 
        } catch (Throwable t) {
            t.printStackTrace();
            throw new IOException("Error, could not add URL to system classloader");
        }        
    }
    
    @Mock
    Logger logMock;

    JarFile jar;
    URL url;
    
    @Before
    public void before() throws Exception {
        jar = new JarFile("src/test/misc/derby.jar");
        url = new File("src/test/misc/derby.jar").getAbsoluteFile().toURI().toURL();
    }
    
    private List<String> getClassNames() {
        List<String> classNames
            = new LinkedList<>();

        for (Enumeration<JarEntry> list = jar.entries(); list.hasMoreElements();) {
            JarEntry entry = list.nextElement();
            String name = entry.getName();
            if (name.endsWith(".class")) {
                name = name.substring(0, name.length() - 6);
                name = name.replaceAll("/", ".");
                name = name.replaceAll("\\\\", ".");
                classNames.add(name);
            }
        }
        
        return classNames;
    }
    
    
    @Test
    public void read_all_classes_in_a_jar() throws Exception {        
        List<String> classNames = getClassNames();                
        Assert.assertFalse(classNames.isEmpty());
        Assert.assertEquals(1511, classNames.size());
    }
    
    
    @Test
    public void dynamically_find_driver_classes() throws Exception {

        addURL(url);
        
        List<String> classNames
            = getClassNames();
        
        boolean found = false;
        for (String className : classNames) {
            try { 
                Class clzz = this.getClass().getClassLoader().loadClass(className);                
                if (java.sql.Driver.class.isAssignableFrom(clzz)) {
                    if ("org.apache.derby.jdbc.EmbeddedDriver".equals(clzz.getName())) {
                        found = true;
                        break;
                    }
                }       
            } 
            catch (NoClassDefFoundError ignore) {}
            catch (ClassNotFoundException ignore) {ignore.printStackTrace();}
        }
        
        Assert.assertTrue("The EmbeddedDriver class was not found", found);
    }
}
