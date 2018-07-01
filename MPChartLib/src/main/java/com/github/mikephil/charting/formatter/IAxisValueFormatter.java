package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;

/**
 * Created by Philipp Jahoda on 20/09/15.
 * <p>
 * 自定义格式化程序接口，允许在绘制轴坐标之前对其进行格式化。
 */
public interface IAxisValueFormatter {

    /**
     * Called when a value from an axis is to be formatted
     * before being drawn. For performance reasons, avoid excessive calculations
     * and memory allocations inside this method.
     * <p>
     * 当一个轴的值在绘制之前被格式化时。出于性能原因，避免在该方法中进行过多的计算和内存分配。
     *
     * @param value 要格式化的值
     * @param axis  值所属的轴
     * @return
     */
    String getFormattedValue(float value, AxisBase axis);
}
