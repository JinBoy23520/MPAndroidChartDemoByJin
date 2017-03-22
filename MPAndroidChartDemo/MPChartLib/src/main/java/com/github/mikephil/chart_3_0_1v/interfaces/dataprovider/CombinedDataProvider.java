package com.github.mikephil.chart_3_0_1v.interfaces.dataprovider;

import com.github.mikephil.chart_3_0_1v.data.CombinedData;

/**
 * Created by philipp on 11/06/16.
 */
public interface CombinedDataProvider extends LineDataProvider, BarDataProvider, BubbleDataProvider, CandleDataProvider, ScatterDataProvider {

    CombinedData getCombinedData();
}
