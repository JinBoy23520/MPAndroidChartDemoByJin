package com.github.mikephil.chart_3_0_1v.listener;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnDrawLineChartTouchListener extends SimpleOnGestureListener implements OnTouchListener {

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}

}
