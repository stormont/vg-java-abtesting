package com.voyagegames.abtesting.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.voyagegames.abtesting.ABChoice;
import com.voyagegames.abtesting.ABResult;

public class ABResultTest {

	@Test
	public void testABResult() {
		final long start = System.currentTimeMillis();
		final ABResult result = new ABResult(ABChoice.A, "result", "mydata");
		final long end = System.currentTimeMillis();
		
		assertTrue(result != null);
		assertTrue(result.choice == ABChoice.A);
		assertTrue(result.identifier.contentEquals("result"));
		assertTrue(((String)result.data).contentEquals("mydata"));
		assertTrue(result.choice == ABChoice.A);
		assertTrue(result.timestamp >= start);
		assertTrue(result.timestamp <= end);
	}

}
