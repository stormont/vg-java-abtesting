package com.voyagegames.abtesting.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.voyagegames.abtesting.ABChoice;
import com.voyagegames.abtesting.ABResult;
import com.voyagegames.abtesting.ABTest;
import com.voyagegames.abtesting.IRecorder;

public class ABTestTest {
	
	public class TestRecorder implements IRecorder {
		
		public ABResult result;

		@Override
		public void recordResult(final ABResult result) {
			this.result = result;
		}
		
	}
	
	public class TestABTest extends ABTest {
		
		public ABChoice doTestResult;

		public TestABTest(final String identifier, final float weightA, final IRecorder recorder) {
			super(identifier, weightA, recorder);
		}

		@Override
		public Object getData(final ABChoice choice) {
			return choice;
		}

		@Override
		public void doTestA() {
			doTestResult = ABChoice.A;
		}

		@Override
		public void doTestB() {
			doTestResult = ABChoice.B;
		}
		
	}

	@Test
	public void testRun_A() {
		final TestRecorder recorder = new TestRecorder();
		final TestABTest test = new TestABTest("mytest", 1f, recorder);
		
		test.run();
		assertTrue(test.doTestResult == ABChoice.A);
	}

	@Test
	public void testRun_B() {
		final TestRecorder recorder = new TestRecorder();
		final TestABTest test = new TestABTest("mytest", 0f, recorder);
		
		test.run();
		assertTrue(test.doTestResult == ABChoice.B);
	}

	@Test
	public void testRun_either() {
		final TestRecorder recorder = new TestRecorder();
		final TestABTest test = new TestABTest("mytest", 0.5f, recorder);
		
		test.run();
		assertTrue(test.doTestResult == ABChoice.A || test.doTestResult == ABChoice.B);
	}

	@Test
	public void testRecordResult() {
		final TestRecorder recorder = new TestRecorder();
		final ABTest test = new TestABTest("mytest", 1f, recorder);
		
		test.run();
		test.recordResult();
		assertTrue(recorder.result != null);
		assertTrue(recorder.result.identifier.contentEquals("mytest"));
		assertTrue(recorder.result.data == ABChoice.A);
		assertTrue(recorder.result.choice == ABChoice.A);
	}

}
