package com.github.mikephil.chart_3_0_1v.interfaces.dataprovider;

import com.github.mikephil.chart_3_0_1v.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.chart_3_0_1v.utils.Transformer;
import com.github.mikephil.chart_3_0_1v.components.YAxis;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(YAxis.AxisDependency axis);
    boolean isInverted(YAxis.AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
