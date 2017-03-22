package com.github.mikephil.chart_3_0_1v.interfaces.dataprovider;

import com.github.mikephil.chart_3_0_1v.components.YAxis;
import com.github.mikephil.chart_3_0_1v.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
