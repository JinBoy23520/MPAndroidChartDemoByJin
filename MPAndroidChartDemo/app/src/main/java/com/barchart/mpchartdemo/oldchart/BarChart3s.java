package com.barchart.mpchartdemo.oldchart;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * 柱状图类，先放在这里
 * Created by jin on 2016/8/16 0016.
 */

public class BarChart3s {
    public BarChart3s(BarChart chart) {
        // 数据描述
        chart.setDescription("");
        //背景
        chart.setBackgroundColor(0xffffffff);
        //定义数据描述得位置
        //chart.setDescriptionPosition(2,100);
        // 设置描述文字的颜色
        // chart.setDescriptionColor(0xffededed);
        // 动画
        chart.animateY(1000);
        //设置阴影
        //chart.setDrawBarShadow(true);
        //设置无边框
        chart.setDrawBorders(false);
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
        chart.setBorderColor(0xffffffff);
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
        xAxis.setAxisLineColor(0xffffffff);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);
        //设置Y轴
        YAxis leftAxis = chart.getAxisLeft();
        //Y轴颜色
        leftAxis.setAxisLineColor(0xffffffff);
        //Y轴参照线颜色
        leftAxis.setGridColor(0xfff3f3f3);
        //参照线长度
        leftAxis.setAxisLineWidth(5f);
        // 顶部居最大值站距离占比
        leftAxis.setSpaceTop(20f);
        chart.invalidate();
    }

    //写死数据直接添加
    public ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1 = new ArrayList<BarEntry>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList<BarEntry> valueSet2 = new ArrayList<BarEntry>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);

        ArrayList<BarEntry> valueSet3 = new ArrayList<BarEntry>();
        BarEntry v3e1 = new BarEntry(20.000f, 0); // Jan
        valueSet3.add(v3e1);
        BarEntry v3e2 = new BarEntry(60.000f, 1); // Feb
        valueSet3.add(v3e2);
        BarEntry v3e3 = new BarEntry(90.000f, 2); // Mar
        valueSet3.add(v3e3);
        BarEntry v3e4 = new BarEntry(150.000f, 3); // Apr
        valueSet3.add(v3e4);
        BarEntry v3e5 = new BarEntry(120.000f, 4); // May
        valueSet3.add(v3e5);
        BarEntry v3e6 = new BarEntry(40.000f, 5); // Jun
        valueSet3.add(v3e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "数据1注解");
        barDataSet1.setColor(Color.parseColor("#F26077"));
        barDataSet1.setBarShadowColor(Color.parseColor("#01000000"));

        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "数据2注解");
        barDataSet2.setColor(Color.parseColor("#00C0BF"));
        barDataSet2.setBarShadowColor(Color.parseColor("#01000000"));

        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "数据3注解");
        barDataSet3.setColor(Color.parseColor("#DEAD26"));
        barDataSet3.setBarShadowColor(Color.parseColor("#01000000"));

        dataSets = new ArrayList<BarDataSet>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);

        return dataSets;
    }

    //写死X轴数据
    public ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<String>();
        xAxis.add("星期一");
        xAxis.add("星期二");
        xAxis.add("星期三");
        xAxis.add("星期四");
        xAxis.add("星期五");
        xAxis.add("星期六");
        return xAxis;
    }

    //动态添加封装方法
    public ArrayList<BarDataSet> getDataSet(List<BarEntry> valueSet1, List<BarEntry> valueSet2,
                                            String title1, String title2,String color1,String color2) {
        ArrayList<BarDataSet> dataSets = null;
        dataSets = new ArrayList<>();
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, title1);
        barDataSet1.setColor(Color.parseColor(color1));
        barDataSet1.setBarShadowColor(Color.parseColor("#01000000"));
        dataSets.add(barDataSet1);

        if (valueSet2 != null) {
            BarDataSet barDataSet2 = new BarDataSet(valueSet2, title2);
            barDataSet2.setColor(Color.parseColor(color2));
            barDataSet2.setBarShadowColor(Color.parseColor("#01000000"));
            dataSets.add(barDataSet2);
        }
        return dataSets;
    }
}
