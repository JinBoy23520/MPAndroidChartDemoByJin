package com.github.mikephil.chart_3_0_1v.interfaces.dataprovider;

import com.github.mikephil.chart_3_0_1v.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
