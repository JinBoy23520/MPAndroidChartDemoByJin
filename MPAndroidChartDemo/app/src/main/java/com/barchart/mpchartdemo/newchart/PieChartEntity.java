package com.barchart.mpchartdemo.newchart;

import com.github.mikephil.chart_3_0_1v.animation.Easing;
import com.github.mikephil.chart_3_0_1v.charts.PieChart;
import com.github.mikephil.chart_3_0_1v.components.Legend;
import com.github.mikephil.chart_3_0_1v.data.PieData;
import com.github.mikephil.chart_3_0_1v.data.PieDataSet;
import com.github.mikephil.chart_3_0_1v.data.PieEntry;
import com.github.mikephil.chart_3_0_1v.formatter.PercentFormatter;

import java.util.List;

/**
 * 饼状图
 * Created by jin
 */

public class PieChartEntity  {

    private PieChart mChart;
    private List<PieEntry> mEntries;
    private String[] labels;
    private int[] mPieColors;
    private int mValueColor;
    private float mTextSize;
    private PieDataSet.ValuePosition mValuePosition;


    public PieChartEntity(PieChart chart, List<PieEntry> entries, String[] labels,
                          int []chartColor,  float textSize, int valueColor, PieDataSet.ValuePosition valuePosition) {
        this.mChart = chart;
        this.mEntries = entries;
        this.labels= labels;
        this.mPieColors = chartColor;
        this.mTextSize= textSize;
        this.mValueColor = valueColor;
        this.mValuePosition = valuePosition;
        initPieChart();
    }

    public PieChartEntity(PieChart chart, List<PieEntry> entries, String[] labels,
                          int []chartColor,  float textSize, int valueColor) {
        this(chart, entries, labels, chartColor, textSize, valueColor, PieDataSet.ValuePosition.INSIDE_SLICE);
    }

    private void initPieChart() {
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setDrawCenterText(false);
        mChart.getDescription().setEnabled(false);
        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);
        mChart.setDrawEntryLabels(true);
        setChartData();
        mChart.animateY(1000, Easing.EasingOption.EaseInOutQuad);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(1f);
        l.setYOffset(0f);
        // entry label styling
        mChart.setEntryLabelColor(mValueColor);
        mChart.setEntryLabelTextSize(mTextSize);
        mChart.setExtraOffsets(10, 10, 10, 10);
    }

    public void setHoleDisenabled () {
        mChart.setDrawHoleEnabled(false);
    }

    /**
     * 中心圆是否可见
     * @param holeColor 中心圆颜色
     * @param holeRadius 半径
     * @param transColor 透明圆颜色
     * @param transRadius 透明圆半径
     */
    public void setHoleEnabled (int holeColor, float holeRadius, int transColor, float transRadius) {
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(holeColor);
        mChart.setTransparentCircleColor(transColor);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(holeRadius);
        mChart.setTransparentCircleRadius(transRadius);
    }

    private void setChartData() {

        PieDataSet dataSet = new PieDataSet(mEntries, "");
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(5f);
//        dataSet.setEntryLabelsColor(mValueColor);
        dataSet.setColors(mPieColors);
        //dataSet.setSelectionShift(0f);
        dataSet.setYValuePosition(mValuePosition);
        dataSet.setXValuePosition(mValuePosition);
        dataSet.setValueLineColor(mValueColor);
        dataSet.setSelectionShift(15f);
        dataSet.setValueLinePart1Length(0.6f);
        dataSet.setValueLineColor(mValueColor);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(mTextSize);
        data.setValueTextColor(mValueColor);
        data.setValueTextColor(mValueColor);
        mChart.setData(data);
        // undo all highlights
        mChart.highlightValues(null);
        mChart.invalidate();
    }

    /**
     * <p>说明文字是否可见</p>
     * @param enabled true 可见,默认可见
     */
    public void setLegendEnabled(boolean enabled) {
        mChart.getLegend().setEnabled(enabled);
        mChart.invalidate();
    }

    public void setPercentValues (boolean showPercent) {
        mChart.setUsePercentValues(showPercent);
    }
}
