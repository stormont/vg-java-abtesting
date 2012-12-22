package com.voyagegames.abtesting;

import java.util.Random;


public abstract class ABTest implements IABTest {
	
	static private final Random GENERATOR = new Random();

	protected final String mIdentifier;
	protected final float mWeightA;
	
	private final IRecorder mRecorder;
	
	private ABChoice mChoice;
	
	public ABTest(final String identifier, final float weightA, final IRecorder recorder) {
		mIdentifier = identifier;
		mWeightA = weightA;
		mRecorder = recorder;
	}

	@Override
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
