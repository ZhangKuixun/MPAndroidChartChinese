package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;

/**
 * Created by Philipp Jahoda on 20/09/15.
 * Custom formatter interface that allows formatting of
 * axis labels before they are being drawn.
 * 自定义格式化程序接口，允许在绘制轴坐标之前对其进行格式化。
 */
public interface IAxisValueFormatter {

    /**
     * Called when a value from an axis is to be formatted
     * before being drawn. For performance reasons, avoid excessive calculations
     * and memory allocations inside this method.
     *
     * @param value the value to be formatted
     * @param axis  the axis the value belongs to
     * @return
     */
    String getFormattedValue(float value, AxisBase axis);
}
