package com.github.mikephil.chart_3_0_1v.formatter;

import com.github.mikephil.chart_3_0_1v.interfaces.datasets.ILineDataSet;
import com.github.mikephil.chart_3_0_1v.interfaces.dataprovider.LineDataProvider;

/**
 * Interface for providing a custom logic to where the filling line of a LineDataSet
 * should end. This of course only works if setFillEnabled(...) is set to true.
 * 
 * @author Philipp Jahoda
 */
public interface IFillFormatter
{

    /**
     * Returns the vertical (y-axis) position where the filled-line of the
     * LineDataSet should end.
     * 
     * @param dataSet the ILineDataSet that is currently drawn
     * @param dataProvider
     * @return
     */
    float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider);
}
