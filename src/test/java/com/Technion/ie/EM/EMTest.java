package com.Technion.ie.EM;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EMTest {
	
	private EM ClassUnderTest;
	private String key = "table+atable";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSplitKey() {
		String wordEN = (key.split("\\+"))[0];
		Assert.assertEquals("English word is not table", "table", wordEN);
	}

}
