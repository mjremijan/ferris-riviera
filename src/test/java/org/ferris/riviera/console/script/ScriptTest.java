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
package org.ferris.riviera.console.script;

import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author student
 */
public class ScriptTest {

    @Test
    public void equals() {
        Script s1 
            = new Script(1, 0, 0, 0, "name", Calendar.getInstance().getTime());
        Script s2 
            = new Script(1, 0, 0, 0, "name", Calendar.getInstance().getTime());
        
        Assert.assertEquals(s1, s2);        
    }
    
    @Test
    public void notEquals() {
        Script s1 
            = new Script(1, 0, 0, 0, "name", Calendar.getInstance().getTime());
        Script s2 
            = new Script(1, 0, 0, 1, "name", Calendar.getInstance().getTime());
        
        Assert.assertNotEquals(s1, s2);        
    }
    
    @Test
    public void compare() {
        
        LinkedList<Script> list = new LinkedList<>();
        
        list.add(new Script(1, 4, 1, 3, "name", Calendar.getInstance().getTime()));        
        list.add(new Script(1, 0, 0, 2, "name", Calendar.getInstance().getTime()));   
        list.add(new Script(1, 1, 0, 2, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 1, 1, 0, "name", Calendar.getInstance().getTime()));       
        list.add(new Script(1, 0, 0, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 0, 4, "name", Calendar.getInstance().getTime()));        
        list.add(new Script(1, 0, 1, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(3, 0, 0, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(2, 0, 0, 1, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 1, 1, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 1, 2, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 4, 1, 2, "name", Calendar.getInstance().getTime()));        
        list.add(new Script(1, 1, 0, 1, "name", Calendar.getInstance().getTime()));
        list.add(new Script(2, 1, 0, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 2, 0, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 3, 0, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 4, 0, 0, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 0, 3, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 4, 0, 2, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 4, 0, 1, "name", Calendar.getInstance().getTime()));                
        list.add(new Script(1, 4, 1, 4, "name", Calendar.getInstance().getTime()));
        list.add(new Script(2, 0, 0, 0, "name", Calendar.getInstance().getTime()));   
        list.add(new Script(1, 4, 1, 1, "name", Calendar.getInstance().getTime()));
        list.add(new Script(1, 0, 0, 5, "name", Calendar.getInstance().getTime()));
        
        Collections.sort(list);
                
        int i = 0;
        Script s;
      
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(2, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(3, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(4, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(5, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(1, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(2, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(1, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(1, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(1, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(2, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(1, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(2, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(3, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(1, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(2, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(1, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(2, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(3, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(1, s.getMajor());
            Assert.assertEquals(4, s.getFeature());
            Assert.assertEquals(1, s.getBug());
            Assert.assertEquals(4, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(2, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(2, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(1, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(2, s.getMajor());
            Assert.assertEquals(1, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
        s = list.get(i++);
        {
            Assert.assertEquals(3, s.getMajor());
            Assert.assertEquals(0, s.getFeature());
            Assert.assertEquals(0, s.getBug());
            Assert.assertEquals(0, s.getBuild());
        }
    }
}

