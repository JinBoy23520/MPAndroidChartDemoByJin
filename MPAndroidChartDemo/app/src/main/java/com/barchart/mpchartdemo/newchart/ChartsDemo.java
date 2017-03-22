package com.barchart.mpchartdemo.newchart;

import android.graphics.Color;

import com.github.mikephil.chart_3_0_1v.charts.BarChart;
import com.github.mikephil.chart_3_0_1v.charts.LineChart;
import com.github.mikephil.chart_3_0_1v.charts.PieChart;
import com.github.mikephil.chart_3_0_1v.components.AxisBase;
import com.github.mikephil.chart_3_0_1v.data.BarEntry;
import com.github.mikephil.chart_3_0_1v.data.Entry;
import com.github.mikephil.chart_3_0_1v.data.LineDataSet;
import com.github.mikephil.chart_3_0_1v.data.PieEntry;
import com.github.mikephil.chart_3_0_1v.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * 图表使用demo
 * Created by jinB
 */

public class ChartsDemo {

    protected void onCreate() {

        lineChart();
        barChart();
        stackBar();
        groupBar();
        pieChart();
    }



    private void lineChart() {
        LineChart lineChart = null;  //TODO init

        List<Entry>[]entries = new ArrayList[3];
        for (int index = 0, len = entries.length; index < len; index ++) {
            ArrayList<Entry> values = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                float val = (float) (Math.random() * 34) + 3;
                values.add(new Entry(i, val));
            }
            entries[index] = values;
        }
        String [] labels = {"label1", "label2", "label3"};
        int [] cahrtColors = {Color.parseColor("#FF4081"), Color.BLUE, Color.GREEN};


        LineChartEntity lineChartEntity = new LineChartEntity(lineChart, entries, labels, cahrtColors, Color.BLACK, 14f);
        lineChartEntity.drawCircle(true);
        lineChartEntity.toggleFilled(null, cahrtColors, false);
        lineChartEntity.setLineMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        lineChart.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        lineChartEntity.setEnableDashedLine(true);
    }

    private void barChart() {
        BarChart barChart = null; //TODO init

        List<BarEntry>[]entries = new ArrayList[1];
        ArrayList<BarEntry> values = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            float val = (float) (Math.random() * 34) + 3;
            values.add(new BarEntry(i, val));
        }
        entries[0] = values;

        String [] labels = {"label1"};
        int [] cahrtColors = {Color.BLUE};


        BarChartEntity barChartEntity = new BarChartEntity(barChart, entries, labels, cahrtColors, Color.BLACK, 14f);
        barChartEntity.setBarWidth(0.5f);
        barChartEntity.setAxisFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return  value + "月";
            }
        }, null);
    }

    private void stackBar () {
        BarChart barChart = null; //TODO init

        List<BarEntry>[]entries = new ArrayList[1];
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        String [] labels = {"label1", "label2"};
        int [] cahrtColors = {Color.BLUE, Color.RED};

        for (int index = 1; index < 7; index++) {
            // stacked
            barEntries.add(new BarEntry(index, new float[]{(float) (Math.random() * 34) + 3, (float) (Math.random() * 34) + 3}));
        }
        entries[0] = barEntries;

        BarChartEntity barChartEntity = new BarChartEntity(barChart, entries, labels, cahrtColors, Color.BLACK, 14f);
        barChartEntity.setBarWidth(0.5f);
        barChartEntity.setDrawValueAboveBar(false);
        barChartEntity.setAxisFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return  value + "月";
            }
        }, null);
    }

    private void groupBar() {

        BarChart barChart  = null; //TODO init

        List<BarEntry>[]entries = new ArrayList[2];
        String [] labels = {"label1", "label2"};
        int [] cahrtColors = {Color.BLUE, Color.RED};

        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();

        for (int index = 0; index < 5; index++) {
            entries1.add(new BarEntry(index, (float) (Math.random() * 34) + 3));
            // stacked
            entries2.add(new BarEntry(index, (float) (Math.random() * 34) + 3));
        }
        entries[0] = entries1;
        entries[1] = entries2;

        GroupBarChartEntity barChartEntity = new GroupBarChartEntity(barChart, entries, labels, cahrtColors, Color.BLACK, 14f);
        barChartEntity.setBarWidth(0.45f);
        barChartEntity.setDrawValueAboveBar(true);
        barChartEntity.setAxisFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return  value + "月";
            }
        }, null);

    }

    private void pieChart() {

        PieChart pieChart  = null; //TODO init
        String[] mParties = new String[]{"pie A", "pie B", "pie C", "pie D","pie E"};
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        int [] cahrtColors = {Color.BLUE, Color.RED, Color.GREEN, Color.CYAN, Color.MAGENTA};
        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < 6 ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * 100) + 100 / 5), mParties[i % mParties.length]));
        }

        PieChartEntity pieChartEntity = new PieChartEntity(pieChart, entries, mParties, cahrtColors, 12f, Color.WHITE);
        pieChartEntity.setHoleEnabled(Color.TRANSPARENT, 40f, Color.TRANSPARENT, 40f);
        pieChartEntity.setLegendEnabled(false);
    }
}
