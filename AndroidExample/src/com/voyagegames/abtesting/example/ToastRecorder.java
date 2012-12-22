package com.voyagegames.abtesting.example;

import android.content.Context;
import android.widget.Toast;

import com.voyagegames.abtesting.ABResult;
import com.voyagegames.abtesting.IRecorder;

public class ToastRecorder implements IRecorder {
	
	private final Context mContext;
	
	public ToastRecorder(final Context context) {
		mContext = context;
	}
	
	public void recordResult(final ABResult result) {
		final String text = result.identifier + ":" + result.choice.ordinal() + " - " + result.data;
		Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
	}

}
