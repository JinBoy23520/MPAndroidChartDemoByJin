package com.barchart.mpchartdemo.newchart;

import com.github.mikephil.chart_3_0_1v.charts.BarChart;
import com.github.mikephil.chart_3_0_1v.charts.BarLineChartBase;
import com.github.mikephil.chart_3_0_1v.components.YAxis;
import com.github.mikephil.chart_3_0_1v.data.BarData;
import com.github.mikephil.chart_3_0_1v.data.BarDataSet;
import com.github.mikephil.chart_3_0_1v.data.BarEntry;
import com.github.mikephil.chart_3_0_1v.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * 叠加柱状图
 * Created by jin
 */

public class StackBarChartEntry extends BaseChartEntity<BarEntry> {

    public StackBarChartEntry(BarLineChartBase chart, List<BarEntry>[] entries, String[] labels, int[] chartColor, int valueColor, float textSize) {
        super(chart, entries, labels, chartColor, valueColor, textSize);
    }

    @Override
    protected void setChartData() {
        BarDataSet barDataSet;
        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            barDataSet = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            barDataSet.setValues(mEntries[0]);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            barDataSet = new BarDataSet(mEntries[0], "");
            barDataSet.setStackLabels(labels);
            barDataSet.setColors(mChartColors);
            barDataSet.setValueTextColor(mValueColor);
            barDataSet.setValueTextSize(mTextSize);
            barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(barDataSet);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(mTextSize);

            data.setBarWidth(0.9f);

            ((BarChart)mChart).setFitBars(true);
            ((BarChart)mChart).setDrawValueAboveBar(false);
            ((BarChart)mChart).setHighlightFullBarEnabled(false);
            mChart.setData(data);
        }
    }

    /**
     * <p>设置bar宽度</p>
     * @param barWidth float
     */
    public void setBarWidth(float barWidth) {
        ((BarChart)mChart).getData().setBarWidth(barWidth);

    }
}
