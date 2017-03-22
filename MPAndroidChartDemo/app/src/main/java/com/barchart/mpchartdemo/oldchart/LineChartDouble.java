package com.barchart.mpchartdemo.oldchart;

import android.graphics.Color;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 柱状图类，先放在这里
 * Created by jin on 2016/12/01
 */

public class LineChartDouble {
    public LineChartDouble(LineChart chart) {
        // 数据描述
        chart.setDescription("");
        //背景
        chart.setBackgroundColor(0xffffffff);
        //定义数据描述得位置
        //chart.setDescriptionPosition(2,100);
        // 设置描述文字的颜色
        // chart.setDescriptionColor(0xffededed);
        // 动画Line
        chart.animateY(1000);
        //设置阴影
        //chart.setDrawBarShadow(true);
        //设置边框
        chart.setDrawBorders(true);
        // 设置是否可以触摸
        chart.setTouchEnabled(true);
        // 是否可以拖拽
        chart.setDragEnabled(true);
        // 是否可以缩放
        chart.setScaleEnabled(true);
        //设置网格背景
        chart.setGridBackgroundColor(0xffffffff);

        //设置边线宽度
        chart.setBorderWidth(0);
        //设置边线颜色
        chart.setBorderColor(0xfff5f5f5);
        // 集双指缩放
        chart.setPinchZoom(false);
        // 隐藏右边的坐标轴
        chart.getAxisRight().setEnabled(false);
        // 隐藏左边的左边轴
        chart.getAxisLeft().setEnabled(true);
        Legend mLegend = chart.getLegend(); // 设置比例图标示
        // 设置窗体样式
        mLegend.setForm(Legend.LegendForm.SQUARE);
        //设置图标位置
        mLegend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        // 字体
        mLegend.setFormSize(4f);
        //是否显示注释
        mLegend.setEnabled(false);
        // 字体颜色
//        mLegend.setTextColor(Color.parseColor("#7e7e7e"));
        //设置X轴位置
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 前面xAxis.setEnabled(false);则下面绘制的Grid不会有"竖的线"（与X轴有关）
        // 上面第一行代码设置了false,所以下面第一行即使设置为true也不会绘制AxisLine
        //设置轴线得颜色
        xAxis.setAxisLineColor(0xfff5f5f5);
//        xAxis.setDrawAxisLine(false);
//        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);
        //设置网色
        xAxis.setGridColor(0xfff5f5f5);
        //设置X轴字颜色
        xAxis.setTextColor(0xff666666);
        //设置Y轴
        YAxis leftAxis = chart.getAxisLeft();
        //Y轴颜色
        leftAxis.setAxisLineColor(0xfff5f5f5);
        //Y轴参照线颜色
        leftAxis.setGridColor(0xfff5f5f5);
        //设置Y轴字颜色
        leftAxis.setTextColor(0xff666666);
        //参照线长度
//        leftAxis.setAxisLineWidth(5f);
        // 顶部居最大值站距离占比
        leftAxis.setSpaceTop(20f);
        chart.invalidate();
    }
    public ArrayList<LineDataSet> getDataSet(boolean a,String underLineColor1,String underLineColor2) {
        ArrayList<LineDataSet> dataSets = null;
        ArrayList<Entry> valueSet1 = new ArrayList<Entry>();
        Entry v1e1 = new Entry(120.000f, 0); // Jan
        valueSet1.add(v1e1);
        Entry v1e2 = new Entry(160.000f, 1); // Feb
        valueSet1.add(v1e2);
        Entry v1e3 = new Entry(180.000f, 2); // Mar
        valueSet1.add(v1e3);
        Entry v1e4 = new Entry(220.000f, 3); // Apr
        valueSet1.add(v1e4);
        Entry v1e5 = new Entry(230.000f, 4); // May
        valueSet1.add(v1e5);
        Entry v1e6 = new Entry(230.000f, 5); // Jun
        valueSet1.add(v1e6);
        Entry v1e7 = new Entry(230.000f, 6); // Jan
        valueSet1.add(v1e7);
        Entry v1e8 = new Entry(220.000f, 7); // Feb
        valueSet1.add(v1e8);
        Entry v1e9 = new Entry(220.000f, 8); // Mar
        valueSet1.add(v1e9);
        Entry v1e10 = new Entry(250.000f, 9); // Apr
        valueSet1.add(v1e10);
        Entry v1e11 = new Entry(270.000f, 10); // May
        valueSet1.add(v1e11);
        Entry v1e12 = new Entry(270.000f, 11); // Jun
        valueSet1.add(v1e12);

        ArrayList<Entry> valueSet2 = new ArrayList<Entry>();
        Entry v2e1 = new Entry(100.000f, 0); // Jan
        valueSet2.add(v2e1);
        Entry v2e2 = new Entry(130.000f, 1); // Feb
        valueSet2.add(v2e2);
        Entry v2e3 = new Entry(140.000f, 2); // Mar
        valueSet2.add(v2e3);
        Entry v2e4 = new Entry(180.000f, 3); // Apr
        valueSet2.add(v2e4);
        Entry v2e5 = new Entry(190.000f, 4); // May
        valueSet2.add(v2e5);
        Entry v2e6 = new Entry(200.000f, 5); // Jun
        valueSet2.add(v2e6);
        Entry v2e7 = new Entry(200.000f, 6); // Jan
        valueSet2.add(v2e7);
        Entry v2e8 = new Entry(190.000f, 7); // Feb
        valueSet2.add(v2e8);
        Entry v2e9 = new Entry(180.000f, 8); // Mar
        valueSet2.add(v2e9);
        Entry v2e10 = new Entry(220.000f, 9); // Apr
        valueSet2.add(v2e10);
        Entry v2e11 = new Entry(240.000f, 10); // May
        valueSet2.add(v2e11);
        Entry v2e12 = new Entry(230.000f, 11); // Jun
        valueSet2.add(v2e12);

        LineDataSet barDataSet1 = new LineDataSet(valueSet1, "数据1注解");
        barDataSet1.setColor(Color.parseColor("#45a2ff"));
        //设置外圈颜色
        barDataSet1.setCircleColor(Color.parseColor("#45a2ff"));
        //圈大小
        barDataSet1.setCircleSize(3);
        //设置内圈颜色
        barDataSet1.setCircleColorHole(Color.parseColor("#ffffff"));

        LineDataSet barDataSet2 = new LineDataSet(valueSet2, "数据2注解");
        barDataSet2.setColor(Color.parseColor("#22cdc6"));
        //设置外圈颜色
        barDataSet2.setCircleColor(Color.parseColor("#22cdc6"));
        //圈大小
        barDataSet2.setCircleSize(3);
        barDataSet1.setDrawFilled(a);
        barDataSet2.setDrawFilled(a);
        //设置内圈颜色
        barDataSet2.setCircleColorHole(Color.parseColor("#ffffff"));
        //设置线下阴影颜色
        barDataSet1.setFillColor(Color.parseColor(underLineColor1));
        //设置线下阴影颜色
        barDataSet2.setFillColor(Color.parseColor(underLineColor2));

        dataSets = new ArrayList<LineDataSet>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }
    public ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<String>();
        for (int j = 0; j < 12; j++){
            xAxis.add((j+1)+"");
        }
        return xAxis;
    }

    public ArrayList<LineDataSet> getDataSet(boolean a,List<Entry> valueSet1, List<Entry> valueSet2,String outLineColor1,
                                             String outLineColor2,String underLineColor1,String underLineColor2) {
        ArrayList<LineDataSet> dataSets = null;
        dataSets = new ArrayList<LineDataSet>();
        LineDataSet barDataSet1 = new LineDataSet(valueSet1, "数据1注解");
        barDataSet1.setColor(Color.parseColor(outLineColor1));
        //设置外圈颜色
        barDataSet1.setCircleColor(Color.parseColor(outLineColor1));
        //圈大小
        barDataSet1.setCircleSize(3);
        //设置内圈颜色
        barDataSet1.setCircleColorHole(Color.parseColor("#ffffff"));
        //设置线下阴影颜色
        barDataSet1.setFillColor(Color.parseColor(underLineColor1));
        dataSets.add(barDataSet1);
        if (valueSet2 != null){
            LineDataSet barDataSet2 = new LineDataSet(valueSet2, "数据2注解");
            barDataSet2.setColor(Color.parseColor(outLineColor2));
            //设置外圈颜色
            barDataSet2.setCircleColor(Color.parseColor(outLineColor2));
            //圈大小
            barDataSet2.setCircleSize(3);
            //设置内圈颜色
            barDataSet2.setCircleColorHole(Color.parseColor("#ffffff"));
            //设置线下阴影颜色
            barDataSet2.setFillColor(Color.parseColor(underLineColor2));
            barDataSet1.setDrawFilled(a);
            barDataSet2.setDrawFilled(a);
            dataSets.add(barDataSet2);
        }
        return dataSets;
    }
}
