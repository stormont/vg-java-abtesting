package com.voyagegames.abtesting;

// An interface for performing an A/B test
public interface IABTest extends Runnable {
	
	// User-defined data resulting from the A/B choice
	public Object getData(ABChoice choice);
	// Initializes the test for choice A
	public void doTestA();
	// Initializes the test for choice B
	public void doTestB();
	// Records the outcome of the A/B test
	public void recordResult();

}
