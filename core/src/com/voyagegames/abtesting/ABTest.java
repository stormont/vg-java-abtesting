package com.voyagegames.abtesting;

import java.util.Random;

// An abstract class for performing an A/B test
public abstract class ABTest implements IABTest {
	
	static protected final Random GENERATOR = new Random();

	protected final String mIdentifier;
	protected final float mWeightA;
	protected final IRecorder mRecorder;
	
	protected ABChoice mChoice;
	
	// Constructs the A/B test
	//  identifier - should be a unique identifier for this specific test
	//  weightA    - a decimal value between 0-1, indicating the percentage weight to place on test A
	//  recorder   - an interface for recording the test results
	public ABTest(final String identifier, final float weightA, final IRecorder recorder) {
		mIdentifier = identifier;
		mWeightA = weightA;
		mRecorder = recorder;
	}

	@Override
	// Sets up the A/B test, based on the initialized weighting
	public void run() {
		mChoice = determineChoice();
		
		switch (mChoice) {
		case A:
			doTestA();
			break;
		case B:
			doTestB();
			break;
		}
	}
	
	// Records the result of the A/B test
	public void recordResult() {
		mRecorder.recordResult(new ABResult(mChoice, mIdentifier, getData(mChoice)));
	}
	
	private ABChoice determineChoice() {
		if (mWeightA >= 1f) {
			return ABChoice.A;
		} else if (mWeightA <= 0f) {
			return ABChoice.B;
		} else {
			if (GENERATOR.nextFloat() < mWeightA) {
				return ABChoice.A;
			} else {
				return ABChoice.B;
			}
		}
	}

}
