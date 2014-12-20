package com.Technion.ie.Utils;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UtilsTest {
	
	private Utils calssUnderTest;
	private String line = "Test split sentence .";
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void splitToTokenstest() {
		String[] tokens = calssUnderTest.splitToTokens(line);
		Assert.assertEquals("size of the return split is not correcet", 4 , tokens.length);
		Assert.assertTrue("first tokent is not Test", tokens[0].equals("Test"));
		Assert.assertTrue("first tokent is not split", tokens[1].equals("split"));
		Assert.assertTrue("first tokent is not sentence", tokens[2].equals("sentence"));
		Assert.assertTrue("first tokent is not .", tokens[3].equals("."));
	}

}
