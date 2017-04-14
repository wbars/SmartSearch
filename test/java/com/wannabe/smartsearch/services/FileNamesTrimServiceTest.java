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
 * Created by wannabe on 11.02.16.
 */
public class FileNamesTrimServiceTest {

    private Function<String, String> fileNamesTrimService;

    @Before
    public void setUp() throws Exception {
        fileNamesTrimService = FileNamesTrimService.newInstance(new GetFileNamesDummy(), null);
    }

    @Test
    public void testRemoveFaceContent() throws Exception {
        assertThat(fileNamesTrimService.apply("a App.java bc"), is("a bc"));
        assertThat(fileNamesTrimService.apply("a Cls.java:12 bc"), is("a bc"));
        assertThat(fileNamesTrimService.apply("a Cls.php:12 bc"), is("a bc"));
        assertThat(fileNamesTrimService.apply("a com.dummy.Class3 bc"), is("a com.dummy.Class3 bc"));
    }

    public static class GetFileNamesDummy implements Supplier<Collection<String>> {

        @Override
        public List<String> get() {
            return asList("App", "Cls");
        }
    }
}