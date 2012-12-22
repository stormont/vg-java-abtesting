package com.voyagegames.abtesting.example;

import android.widget.Button;

import com.voyagegames.abtesting.ABChoice;
import com.voyagegames.abtesting.ABTest;
import com.voyagegames.abtesting.IRecorder;
import com.voyagegames.abtesting.R;

public class ButtonABTest extends ABTest {
	
	public final Button view;
	
	private final long mStartMillis;

	public ButtonABTest(final Button view, final String identifier, final float weightA, final IRecorder recorder) {
		super(identifier, weightA, recorder);
		this.view = view;
		mStartMillis = System.currentTimeMillis();
	}

	@Override
	public String getData(final ABChoice choice) {
		final long endMillis = System.currentTimeMillis();
		return "User took " + (endMillis - mStartMillis) + "ms to click with weight A of " + mWeightA;
	}

	@Override
	public void doTestA() {
		view.setText(R.string.hello_world_a);
	}

	@Override
	public void doTestB() {
		view.setText(R.string.hello_world_b);
	}

}
