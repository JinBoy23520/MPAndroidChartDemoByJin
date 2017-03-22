package com.barchart.mpchartdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barchart.mpchartdemo.R;
import com.barchart.mpchartdemo.oldchart.BarChart3s;
import com.barchart.mpchartdemo.oldchart.LineChartDouble;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;

/**
 * jar包图表使用demo
 * Created by jinB
 */
public class OldFragment extends Fragment {
    private View mView;
    private BarChart3s mBarChart3s;
    private LineChartDouble lineChartDouble;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_old, container, false);
        BarChart barChart = (BarChart) mView.findViewById(R.id.barChart);
        LineChart linechart = (LineChart) mView.findViewById(R.id.lineChart);
        mBarChart3s = new BarChart3s(barChart);
        lineChartDouble = new LineChartDouble(linechart);
        LineData lineData = new LineData(lineChartDouble.getXAxisValues(), lineChartDouble.getDataSet(true,"#8845a2ff","#8822cdc6"));
        BarData data = new BarData(mBarChart3s.getXAxisValues(), mBarChart3s.getDataSet());
        // 设置数据
        barChart.setData(data);
        linechart.setData(lineData);
        return mView;
    }
}
