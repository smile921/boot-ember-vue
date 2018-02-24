package org.smile921.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

public class RegPatternTest {
    @Before
    public void setUp() throws Exception {
        String pattern= "/ui/.*";
        Pattern pat = Pattern.compile(pattern);
        String target = "/ui/ss";
        boolean flag = pat.matcher(target).matches();
        System.out.print(flag);
    }

    @Test
    public void test(){

    }

    @After
    public void tearDown() throws Exception {
    }
}
