package com.barchart.mpchartdemo;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barchart.mpchartdemo.entity.RealListEntity;
import com.barchart.mpchartdemo.entity.YoyListEntity;
import com.barchart.mpchartdemo.newchart.BarChartEntity;
import com.barchart.mpchartdemo.newchart.LineChartEntity;
import com.barchart.mpchartdemo.newchart.PieChartEntity;
import com.barchart.mpchartdemo.view.NewMarkerView;
import com.barchart.mpchartdemo.view.StringUtils;
import com.github.mikephil.chart_3_0_1v.animation.Easing;
import com.github.mikephil.chart_3_0_1v.charts.BarChart;
import com.github.mikephil.chart_3_0_1v.charts.LineChart;
import com.github.mikephil.chart_3_0_1v.charts.PieChart;
import com.github.mikephil.chart_3_0_1v.components.AxisBase;
import com.github.mikephil.chart_3_0_1v.components.Legend;
import com.github.mikephil.chart_3_0_1v.data.BarEntry;
import com.github.mikephil.chart_3_0_1v.data.Entry;
import com.github.mikephil.chart_3_0_1v.data.LineDataSet;
import com.github.mikephil.chart_3_0_1v.data.PieDataSet;
import com.github.mikephil.chart_3_0_1v.data.PieEntry;
import com.github.mikephil.chart_3_0_1v.formatter.IAxisValueFormatter;
import com.github.mikephil.chart_3_0_1v.formatter.IValueFormatter;
import com.github.mikephil.chart_3_0_1v.utils.ViewPortHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * 源码新版图表使用demo 涉及修改源码
 * Created by jinB
 */
public class NewFragment extends Fragment {
    private View mView;
    private LineChart lineChart;
    private BarChart mBarChart;

    private List<RealListEntity> realList;
    private List<YoyListEntity> yoyList;
    private List<Entry> values1, values2;
    private RealListEntity realListEntity;
    private YoyListEntity yoyListEntity;
    private DecimalFormat mFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_new, container, false);
        test();
        initViews();
        updatePieChart();
        updateThePieChart();
        updataBarChart();
        return mView;
    }

    /**
     * 下面json转实体类
     */
    public void test() {
        String data = "{\"realList\":[{\"amount\":\"3740\",\"month\":\"1\",\"year\":\"2017\"},{\"amount\":\"2382\",\"month\":\"2\",\"year\":\"2017\"},{\"amount\":\"3329\",\"month\":\"3\",\"year\":\"2017\"},{\"amount\":\"463\",\"month\":\"4\",\"year\":\"2017\"}],\n" +
                "\"yoyList\":[{\"amount\":\"4571\",\"month\":\"1\",\"year\":\"2016\"},{\"amount\":\"1630\",\"month\":\"2\",\"year\":\"2016\"},{\"amount\":\"2589\",\"month\":\"3\",\"year\":\"2016\"},{\"amount\":\"2180\",\"month\":\"4\",\"year\":\"2016\"},{\"amount\":\"3089\",\"month\":\"5\",\"year\":\"2016\"},{\"amount\":\"4906\",\"month\":\"6\",\"year\":\"2016\"},{\"amount\":\"5741\",\"month\":\"7\",\"year\":\"2016\"},{\"amount\":\"3611\",\"month\":\"8\",\"year\":\"2016\"},{\"amount\":\"2458\",\"month\":\"9\",\"year\":\"2016\"},{\"amount\":\"2608\",\"month\":\"10\",\"year\":\"2016\"},{\"amount\":\"5437\",\"month\":\"11\",\"year\":\"2016\"},{\"amount\":\"4219\",\"month\":\"12\",\"year\":\"2016\"}]}";
        try {
            JSONObject object = new JSONObject(data);
            JSONArray jsonArray = object.getJSONArray("realList");
            realList = new ArrayList<>();
            for (int i = 0, count = jsonArray.length(); i < count; i++) {
                //改了这里
                JSONObject jsonObject = jsonArray.optJSONObject(i);//{"amount":"3740","month":"1","year":"2017"}
                RealListEntity realListEntity = new RealListEntity();
                String amount = jsonObject.optString("amount");
                String month = jsonObject.optString("month");
                String year = jsonObject.optString("year");
                realListEntity.setAmount(amount);
                realListEntity.setMonth(month);
                realListEntity.setYear(year);
                realList.add(realListEntity);
            }

            JSONArray jsonArray1 = object.getJSONArray("yoyList");
            yoyList = new ArrayList<>();
            for (int i = 0, count = jsonArray1.length(); i < count; i++) {
                //改了这里
                JSONObject jsonObject = jsonArray1.optJSONObject(i);//{"amount":"3740","month":"1","year":"2017"}
                YoyListEntity yoyListEntity = new YoyListEntity();
                String amount = jsonObject.optString("amount");
                String month = jsonObject.optString("month");
                String year = jsonObject.optString("year");
                yoyListEntity.setAmount(amount);
                yoyListEntity.setMonth(month);
                yoyListEntity.setYear(year);
                yoyList.add(yoyListEntity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initViews() {
        mFormat = new DecimalFormat("#,###.##");
        lineChart = (LineChart) mView.findViewById(R.id.new_lineChart);

        values1 = new ArrayList<>();
        values2 = new ArrayList<>();
        for (int i = 0; i < yoyList.size(); i++) {
            yoyListEntity = yoyList.get(i);
            String amount = yoyListEntity.getAmount();
            if (amount != null) {
                float f = 0;
                try {
                    f = Float.parseFloat(amount);
                } catch (Exception e) {
                    e.printStackTrace();
                    f = 0;
                }
                Entry entry = new Entry(i + 1, f);
                values1.add(entry);
            }
        }

        for (int i = 0; i < realList.size(); i++) {
            realListEntity = realList.get(i);
            String amount = realListEntity.getAmount();
            if (amount != null) {
                float f = 0;
                try {
                    f = Float.parseFloat(amount);
                } catch (Exception e) {
                    e.printStackTrace();
                    f = 0;
                }
                Entry entry = new Entry(i + 1, f);
                values2.add(entry);
            }
        }


        Drawable[] drawables = {
                ContextCompat.getDrawable(getActivity(), R.drawable.chart_thisyear_blue),
                ContextCompat.getDrawable(getActivity(), R.drawable.chart_callserice_call_casecount)
        };
        int[] callDurationColors = {Color.parseColor("#45A2FF"), Color.parseColor("#5fd1cc")};
        String thisYear = "";
        if (realList.size() > 0) {
            thisYear = realList.get(0).getYear();
        }

        String lastYear = "";
        if (yoyList.size() > 0) {
            lastYear = yoyList.get(0).getYear();
        }
        String[] labels = new String[]{thisYear, lastYear};
        updateLinehart(yoyList, realList, lineChart, callDurationColors, drawables, "", values1, values2, labels);
    }

    /**
     * 双平滑曲线传入数据，添加markview，添加实体类单位
     *
     * @param yoyList
     * @param realList
     * @param lineChart
     * @param colors
     * @param drawables
     * @param unit
     * @param values2
     * @param values1
     * @param labels
     */
    private void updateLinehart(final List<YoyListEntity> yoyList, final List<RealListEntity> realList, LineChart lineChart, int[] colors, Drawable[] drawables,
                                final String unit, List<Entry> values2, List<Entry> values1, final String[] labels) {
        List<Entry>[] entries = new ArrayList[2];
        entries[0] = values1;
        entries[1] = values2;
        LineChartEntity lineChartEntity = new LineChartEntity(lineChart, entries, labels, colors, Color.parseColor("#999999"), 12f);
        lineChartEntity.drawCircle(true);
        lineChart.setScaleMinima(1.0f, 1.0f);
        toggleFilled(lineChartEntity, drawables, colors);

        /**
         * 这里切换平滑曲线或者折现图
         */
//        lineChartEntity.setLineMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineChartEntity.setLineMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineChartEntity.initLegend(Legend.LegendForm.CIRCLE, 12f, Color.parseColor("#999999"));
        lineChartEntity.updateLegendOrientation(Legend.LegendVerticalAlignment.TOP, Legend.LegendHorizontalAlignment.RIGHT, Legend.LegendOrientation.HORIZONTAL);
        lineChartEntity.setAxisFormatter(
                new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        if (value == 1.0f) {
                            return mFormat.format(value) + "月";
                        }
                        String monthStr = mFormat.format(value);
                        if (monthStr.contains(".")) {
                            return "";
                        } else {
                            return monthStr;
                        }
//                        return mMonthFormat.format(value);
                    }
                },
                new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return mFormat.format(value) + unit;
                    }
                });

        lineChartEntity.setDataValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return mFormat.format(value) + unit;
            }
        });

        final NewMarkerView markerView = new NewMarkerView(getActivity(), R.layout.custom_marker_view_layout);
        markerView.setCallBack(new NewMarkerView.CallBack() {
            @Override
            public void onCallBack(float x, String value) {
                int index = (int) (x);
                if (index < 0) {
                    return;
                }
                if (index > yoyList.size() && index > realList.size()) {
                    return;
                }
                String textTemp = "";

                if (index <= yoyList.size()) {
                    if (!StringUtils.isEmpty(textTemp)) {
                    }
                    textTemp += yoyList.get(index - 1).getYear() + "." + index + "  " + mFormat.format(Float.parseFloat(yoyList.get(index - 1).getAmount())) + unit;
                }

                if (index <= realList.size()) {
                    textTemp += "\n";
                    textTemp += realList.get(index - 1).getYear() + "." + index + "  " + mFormat.format(Float.parseFloat(realList.get(index - 1).getAmount())) + unit;
                }
                markerView.getTvContent().setText(textTemp);
            }
        });
        lineChartEntity.setMarkView(markerView);
        lineChart.getData().setDrawValues(false);
    }

    /**
     * 双平滑曲线添加线下的阴影
     *
     * @param lineChartEntity
     * @param drawables
     * @param colors
     */
    private void toggleFilled(LineChartEntity lineChartEntity, Drawable[] drawables, int[] colors) {
        if (android.os.Build.VERSION.SDK_INT >= 18) {

            lineChartEntity.toggleFilled(drawables, null, true);
        } else {
            lineChartEntity.toggleFilled(null, colors, true);
        }
    }

    /**
     * 添加数据均匀饼装图
     */
    public void updatePieChart() {
        int[] colors = {Color.parseColor("#faa74c"), Color.parseColor("#58D4C5"), Color.parseColor("#36a3eb"), Color.parseColor("#cc435f"), Color.parseColor("#f1ea56"),
                Color.parseColor("#f49468"), Color.parseColor("#d5932c"), Color.parseColor("#34b5cc"), Color.parseColor("#8169c6"), Color.parseColor("#ca4561"), Color.parseColor("#fee335")};
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        for (int i = 0; i <= 5; i++) {
            PieEntry pieEntry = new PieEntry(60, "项目" + i + "占比");
            entries.add(pieEntry);
        }

        for (int i = 6; i <= 7; i++) {
            PieEntry pieEntry = new PieEntry(100, "项目" + i + "占比");
            entries.add(pieEntry);
        }

        PieEntry pieEntry = new PieEntry(100, "项目8占比");
        entries.add(pieEntry);

        if (entries.size() != 0) {
            PieChart new_pie_chart = (PieChart) mView.findViewById(R.id.new_pie_chart);
            PieChartEntity pieChartEntity = new PieChartEntity(new_pie_chart, entries, new String[]{"", "", ""}, colors, 12f, Color.GRAY, PieDataSet.ValuePosition.OUTSIDE_SLICE);
            pieChartEntity.setHoleEnabled(Color.TRANSPARENT, 40f, Color.TRANSPARENT, 40f);
            pieChartEntity.setLegendEnabled(false);
            pieChartEntity.setPercentValues(true);
        }
    }

    /**
     * 添加数据不均匀饼装图，避免文字重合
     */
    public void updateThePieChart() {
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        PieChart new_the_pie_chart = (PieChart) mView.findViewById(R.id.new_the_pie_chart);
        int[] colors = {Color.parseColor("#faa74c"), Color.parseColor("#58D4C5"), Color.parseColor("#36a3eb"), Color.parseColor("#cc435f"), Color.parseColor("#f1ea56"),
                Color.parseColor("#f49468"), Color.parseColor("#d5932c"), Color.parseColor("#34b5cc"), Color.parseColor("#8169c6"), Color.parseColor("#ca4561"), Color.parseColor("#fee335")};

        for (int i = 1; i <= 8; i++) {
            int all = 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8;
            PieEntry pieEntry = new PieEntry(i, "", i > 3);
            entries.add(pieEntry);
        }

//        PieChartEntity pieChartEntity = new PieChartEntity(new_the_pie_chart, entries, new String[]{"", "", ""}, colors, 12f, Color.WHITE);
        /**
         * 下面注释的位置与上面显示一样，为向外文字显示
         */
        PieChartEntity pieChartEntity = new PieChartEntity(new_the_pie_chart, entries, new String[]{"", "", ""}, colors, 12f, Color.GRAY, PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieChartEntity.setHoleEnabled(Color.TRANSPARENT, 40f, Color.TRANSPARENT, 40f);
        pieChartEntity.setLegendEnabled(false);
        pieChartEntity.setPercentValues(true);
    }

    private void updataBarChart() {
        mBarChart = (BarChart) mView.findViewById(R.id.new_the_bar_chart);
        List<BarEntry>[] entries = new ArrayList[3];
        final String[] labels = {
                getActivity().getResources().getString(R.string.actual_value),
                getActivity().getResources().getString(R.string.budget_value),
                getActivity().getResources().getString(R.string.yoy_value),};
        int[] cahrtColors = {
                Color.parseColor("#45A2FF"),
                Color.parseColor("#58D4C5"),
                Color.parseColor("#FDB25F")};

        final double[] values = new double[3];
        ArrayList<BarEntry> entries1 = new ArrayList<>();
        float actualValue = 10086;
        float budgetValue = 1001;
        float yoyValue = 12580;
        entries1.add(new BarEntry(0.5f, actualValue));
        entries1.add(new BarEntry(1.3f, budgetValue));
        entries1.add(new BarEntry(2.1f, yoyValue));


        values[0] = actualValue;

        values[1] = budgetValue;

        values[2] = yoyValue;

        entries[0] = entries1;

        if (mBarChart.getData() != null) {
            mBarChart.getData().clearValues();
        }
        BarChartEntity barChartEntity = new BarChartEntity(mBarChart, entries, labels, cahrtColors, Color.parseColor("#999999"), 13f);
        barChartEntity.setBarWidth(0.55f);
        barChartEntity.setDrawValueAboveBar(true);
        mBarChart.setPinchZoom(false);
        mBarChart.setScaleEnabled(false);

        mBarChart.animateY(2000, Easing.EasingOption.EaseInOutQuart);
        barChartEntity.setAxisFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "";
            }
        }, null);

        mBarChart.getLegend().setEnabled(false);
        mBarChart.getData().setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return labels[entry.getX() == 0.5f ? 0 : (entry.getX() == 1.3f ? 1 : 2)]
                        + "\n"
                        + mFormat.format(values[entry.getX() == 0.5f ? 0 : (entry.getX() == 1.3f ? 1 : 2)]);
            }
        });


        float yMax = mBarChart.getData().getYMax() == 0 ? 100f : mBarChart.getData().getYMax();
        float delta = yMax / 5.5f;
        mBarChart.getAxisLeft().setAxisMaximum(yMax + delta);

        float yMin = mBarChart.getData().getYMin();
        if (yMin == 0) {
            yMin = 0;
        }
        float deltaMin = yMin / 5.0f;
        mBarChart.getAxisLeft().setAxisMinimum(yMin - deltaMin);
        
    }

}
