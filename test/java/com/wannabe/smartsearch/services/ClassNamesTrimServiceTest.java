package com.wannabe.smartsearch.services;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;

/**
 * Created by wannabe on 10.02.16.
 */
public class ClassNamesTrimServiceTest {

	private DataTrimService classNamesTrimService;

	@Before
	public void setUp() throws Exception {
		classNamesTrimService = ClassNamesTrimService.newInstance(new GetClassNamesDummy(), null);
	}

	@Test
	public void testRemoveFaceContent() throws Exception {
		//given
		String data1 = "a com.dummy.Class1 bc";
		String data2 = "a com.dummy.Class2 bc";
		String data3 = "a com.dummy.Class3 bc";
		String data4 = "a com.dummy.Class2.method() bc";
		String data5 = "a com.dummy.Class2.method(args) bc";
		String data6 = "a com.dummy.Class2.method(App.java:80) bc";
		//when

		//then
		assertEquals("a  bc", classNamesTrimService.removeFaceContent(data1));
		assertEquals("a  bc", classNamesTrimService.removeFaceContent(data2));
		assertEquals(data3, classNamesTrimService.removeFaceContent(data3));
		assertEquals("a  bc", classNamesTrimService.removeFaceContent(data4));
		assertEquals("a  bc", classNamesTrimService.removeFaceContent(data5));
		assertEquals("a  bc", classNamesTrimService.removeFaceContent(data6));
	}

	public static class GetClassNamesDummy implements Callable<Set<String>> {

		@Override
		public Set<String> call() throws Exception {
			return new HashSet<>(Arrays.asList("com.dummy.Class1", "com.dummy.Class2"));
		}
	}
}