package com.wannabe.smartsearch.services;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;

/**
 * Created by wannabe on 11.02.16.
 */
public class FileNamesTrimServiceTest {

	private FileNamesTrimService fileNamesTrimService;

	@Before
	public void setUp() throws Exception {
		fileNamesTrimService = new FileNamesTrimService(new GetFileNamesDummy(), null);
	}

	@Test
	public void testRemoveFaceContent() throws Exception {
		//given
		String data1 = "a App.java bc";
		String data2 = "a Cls.java:12 bc";
		String data3 = "a com.dummy.Class3 bc";
		//when

		//then
		assertEquals("a  bc", fileNamesTrimService.removeFaceContent(data1));
		assertEquals("a  bc", fileNamesTrimService.removeFaceContent(data2));
		assertEquals(data3, fileNamesTrimService.removeFaceContent(data3));
	}

	public static class GetFileNamesDummy implements Callable<Set<String>> {

		@Override
		public Set<String> call() throws Exception {
			return new HashSet<>(
				Arrays.asList(
					"App.java", "Cls.java"
				)
			);
		}
	}
}