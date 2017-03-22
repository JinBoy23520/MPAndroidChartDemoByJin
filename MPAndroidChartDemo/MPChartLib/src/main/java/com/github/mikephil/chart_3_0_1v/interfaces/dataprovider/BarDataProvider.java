package com.github.mikephil.chart_3_0_1v.interfaces.dataprovider;

import com.github.mikephil.chart_3_0_1v.data.BarData;

public interface BarDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BarData getBarData();
    boolean isDrawBarShadowEnabled();
    boolean isDrawValueAboveBarEnabled();
    boolean isHighlightFullBarEnabled();
}
