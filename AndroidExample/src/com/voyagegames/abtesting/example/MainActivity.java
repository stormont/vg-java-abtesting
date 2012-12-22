package com.voyagegames.abtesting.example;

import java.sql.Timestamp;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.voyagegames.abtesting.ABResult;
import com.voyagegames.abtesting.R;
import com.voyagegames.abtesting.SQLiteRecorder;

public class MainActivity extends Activity {
	
	private enum TestIdentifier {
		BUTTON_TEST
	}
	
	SQLiteRecorder mRecorder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mRecorder = new SQLiteRecorder(this);
		
		final Button log = (Button)findViewById(R.id.log);
		log.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(final View view) {
					logResults();
				}
			});
		
		final ButtonABTest btn = new ButtonABTest((Button)findViewById(R.id.button), TestIdentifier.BUTTON_TEST.toString(), 0.5f, mRecorder);
		btn.view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(final View view) {
					btn.recordResult();
				}
			});
		btn.run();
	}
	
	private void logResults() {
		final List<ABResult> results = mRecorder.select();
		
		for (final ABResult r : results) {
			Log.e("ABResult", "(" + new Timestamp(r.timestamp).toString() + ") " + r.identifier + ":" + r.choice.toString() + " - " + r.data);
		}
	}

}
