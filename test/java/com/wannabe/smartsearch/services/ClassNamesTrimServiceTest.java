package com.wannabe.smartsearch.services;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by wannabe on 10.02.16.
 */
public class ClassNamesTrimServiceTest {

    private Function<String, String> classNamesTrimService;

    @Before
    public void setUp() throws Exception {
        classNamesTrimService = ClassNamesTrimService.newInstance(new GetClassNamesDummy(), null);
    }

    @Test
    public void testRemoveFaceContent() throws Exception {
        assertThat(classNamesTrimService.apply("a com.dummy.Class1 bc"), is("a bc"));
        assertThat(classNamesTrimService.apply("a com.dummy.Class2 bc"), is("a bc"));
        assertThat(classNamesTrimService.apply("a com.dummy.Class2.method() bc"), is("a bc"));
        assertThat(classNamesTrimService.apply("a com.dummy.Class21.method() bc"), is("a com.dummy.Class21.method() bc"));
        assertThat(classNamesTrimService.apply("a com.dummy.Class2.method(args) bc"), is("a bc"));
        assertThat(classNamesTrimService.apply("a com.dummy.Class2.method(App.java:80) bc"), is("a bc"));
        assertThat(classNamesTrimService.apply("a com.dummy.Class3 bc"), is("a com.dummy.Class3 bc"));
    }

    public static class GetClassNamesDummy implements Supplier<Collection<String>> {

        @Override
        public List<String> get() {
            return asList("com.dummy.Class1", "com.dummy.Class2");
        }
    }
}