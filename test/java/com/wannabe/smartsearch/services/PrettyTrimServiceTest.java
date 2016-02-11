package com.wannabe.smartsearch.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by wannabe on 11.02.16.
 */
public class PrettyTrimServiceTest {

	private DataTrimService prettyTrimService;

	@Before
	public void setUp() throws Exception {
		prettyTrimService = PrettyTrimService.getInstance();
	}

	@Test
	public void testRemoveFaceContent() throws Exception {
		//given
		String data1 = "a  bc";
		String data2 = " ab  c ";
		String data3 = "abc";
		//when

		//then
		assertEquals("a bc", prettyTrimService.removeFaceContent(data1));
		assertEquals("ab c", prettyTrimService.removeFaceContent(data2));
		assertEquals(data3, prettyTrimService.removeFaceContent(data3));
	}
}