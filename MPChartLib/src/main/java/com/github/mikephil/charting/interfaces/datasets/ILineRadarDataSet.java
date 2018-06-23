package com.github.mikephil.charting.interfaces.datasets;

import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.data.Entry;

/**
 * Created by Philipp Jahoda on 21/10/15.
 */
public interface ILineRadarDataSet<T extends Entry> extends ILineScatterCandleRadarDataSet<T> {

    /**
     * Returns the color that is used for filling the line surface area.
     *
     * @return
     */
    int getFillColor();

    /**
     * Returns the drawable used for filling the area below the line.
     *
     * @return
     */
    Drawable getFillDrawable();

    /**
     * Returns the alpha value that is used for filling the line surface,
     * default: 85
     *
     * @return
     */
    int getFillAlpha();

    /**
     * Returns the stroke-width of the drawn line
     *
     * @return
     */
    float getLineWidth();

    /**
     * Returns true if filled drawing is enabled, false if not
     *
     * @return
     */
    boolean isDrawFilledEnabled();

    /**
     * Set to true if the DataSet should be drawn filled (surface), and not just
     * as a line, disabling this will give great performance boost. Please note that this method
     * uses the canvas.clipPath(...) method for drawing the filled area.
     * For devices with API level < 18 (Android 4.3), hardware acceleration of the chart should
     * be turned off. Default: false
     * <p>
     * 填充曲线图绘制区域(背景色)
     * <p>
     * 设置为true，如果DataSet被填充则不仅仅是画一条线，那么禁用DataSet将极大地提高性能。注意：禁止这将给予极大的性能提升！
     * 请注意，此方法使用canvas.clipPath(...)方法绘制填充区域。对于API级别＜18（Android 4.3）的设备，应该关闭图表的硬件加速。
     * 默认值：false 。
     *
     * @param enabled
     */
    void setDrawFilled(boolean enabled);
}
