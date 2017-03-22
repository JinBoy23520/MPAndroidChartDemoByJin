package com.barchart.mpchartdemo.newchart;

import com.github.mikephil.chart_3_0_1v.charts.BarChart;
import com.github.mikephil.chart_3_0_1v.charts.BarLineChartBase;
import com.github.mikephil.chart_3_0_1v.components.YAxis;
import com.github.mikephil.chart_3_0_1v.data.BarData;
import com.github.mikephil.chart_3_0_1v.data.BarDataSet;
import com.github.mikephil.chart_3_0_1v.data.BarEntry;

import java.util.List;

/**
 *
 * 组合柱状图
 * Created by jin
 */

public class GroupBarChartEntity extends BaseChartEntity<BarEntry> {

    public GroupBarChartEntity(BarLineChartBase chart, List<BarEntry>[] entries, String[] labels, int[] chartColor, int valueColor, float textSize) {
        super(chart, entries, labels, chartColor, valueColor, textSize);

    }

    @Override
    protected void setChartData() {

        BarDataSet[] barDataSets = new BarDataSet[mEntries.length];
        if (mChart.getData() != null && mChart.getData().getDataSetCount() == mEntries.length) {
            for(int index = 0, len = mEntries.length; index < len; index ++) {
                List<BarEntry> list = mEntries[index];
                barDataSets[index] = (BarDataSet) mChart.getData().getDataSetByIndex(index);
                barDataSets[index].setValues(list);
            }
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        }  else {
            for (int index = 0, len = mEntries.length; index < len; index ++) {
                barDataSets[index] = new BarDataSet(mEntries[index], labels[index]);
                barDataSets[index].setAxisDependency(YAxis.AxisDependency.LEFT);
                barDataSets[index].setColor(mChartColors[index]);
                barDataSets[index].setValueTextColor(mValueColor);
                barDataSets[index].setValueTextSize(mTextSize);
            }
        }

        float groupSpace = 0.45f;
        float barSpace = 0.0f; // x2 dataset
        float barWidth = 0.45f; // x2 dataset

        BarData data = new BarData(barDataSets);
        data.setBarWidth(barWidth);
        // make this BarData object grouped
        data.groupBars(0, groupSpace, barSpace);
        mChart.setData(data);

    }

    /**
     * <p>设置bar宽度</p>
     * @param barWidth float
     */
    public void setBarWidth(float barWidth) {
        float groupSpace = 1f;
        float barSpace = 0.0f; // x2 dataset
//        float barWidth = 0.45f; // x2 dataset
        ((BarChart)mChart).getData().setBarWidth(barWidth);
        ((BarChart)mChart).getData().groupBars(0, groupSpace, barSpace);


    }

    public void setDrawValueAboveBar(boolean aboveBar) {
        ((BarChart)mChart).setDrawValueAboveBar(aboveBar);
    }
}
