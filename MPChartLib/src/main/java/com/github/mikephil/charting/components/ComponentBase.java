
package com.github.mikephil.charting.components;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;

import com.github.mikephil.charting.utils.Utils;

/**
 * 这个类封装了轴(Axis)、图例(Legend)和分界线(LimitLines)有共同点的东西。
 *
 * @author Philipp Jahoda
 */
public abstract class ComponentBase {

    /**
     * 指示是否启用该轴/图例的标志
     */
    protected boolean mEnabled = true;

    /**
     * 该组件在X轴上的偏移像素量
     */
    protected float mXOffset = 5f;

    /**
     * 该组件在Y轴上的偏移像素量
     */
    protected float mYOffset = 5f;

    /**
     * 用于标签的字体
     */
    protected Typeface mTypeface = null;

    /**
     * 标签的文本大小
     */
    protected float mTextSize = Utils.convertDpToPixel(10f);

    /**
     * 用于标签的文本颜色
     */
    protected int mTextColor = Color.BLACK;


    public ComponentBase() {

    }

    /**
     * Returns the used offset on the x-axis for drawing the axis or legend
     * labels. This offset is applied before and after the label.
     *
     * @return
     */
    public float getXOffset() {
        return mXOffset;
    }

    /**
     * Sets the used x-axis offset for the labels on this axis.
     *
     * @param xOffset
     */
    public void setXOffset(float xOffset) {
        mXOffset = Utils.convertDpToPixel(xOffset);
    }

    /**
     * Returns the used offset on the x-axis for drawing the axis labels. This
     * offset is applied before and after the label.
     *
     * @return
     */
    public float getYOffset() {
        return mYOffset;
    }

    /**
     * Sets the used y-axis offset for the labels on this axis. For the legend,
     * higher offset means the legend as a whole will be placed further away
     * from the top.
     */
    public void setYOffset(float yOffset) {
        mYOffset = Utils.convertDpToPixel(yOffset);
    }

    /**
     * 返回标签使用的字体，如果没有设置，返回null
     */
    public Typeface getTypeface() {
        return mTypeface;
    }

    /**
     * 设置轴标签的字体
     *
     * @param tf
     */
    public void setTypeface(Typeface tf) {
        mTypeface = tf;
    }

    /**
     * 设置轴标签的文字大小(dp)。
     * min＝6f，max＝24f，默认10f
     *
     * @param size 文本大小，以DP为单位
     */
    public void setTextSize(float size) {

        if (size > 24f)
            size = 24f;
        if (size < 6f)
            size = 6f;

        mTextSize = Utils.convertDpToPixel(size);
    }

    /**
     * 返回当前为标签设置的文本大小，以像素为单位
     */
    public float getTextSize() {
        return mTextSize;
    }


    /**
     * 设置轴标签的文字颜色。在使用资源的颜色时，请确保使用 getResources().getColor(...)
     */
    public void setTextColor(@ColorInt int color) {
        mTextColor = color;
    }

    /**
     * 返回标签设置的文本颜色。
     */
    public int getTextColor() {
        return mTextColor;
    }

    /**
     * 如果设置true，这个组件被启用或者被绘制。如果禁用，将不绘制该组件。
     * 默认: true
     */
    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    /**
     * 返回true,启用此 comonent（应该被绘制），否则返回false。
     */
    public boolean isEnabled() {
        return mEnabled;
    }
}
