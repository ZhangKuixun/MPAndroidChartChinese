
package com.github.mikephil.charting.components;

import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.utils.Utils;

/**
 * This class encapsulates everything both Axis, Legend and LimitLines have in common.
 * 这个类封装了轴、图例和界线有共同点的东西。
 *
 * @author Philipp Jahoda
 */
public abstract class ComponentBase {

    /**
     * flag that indicates if this axis / legend is enabled or not
     */
    protected boolean mEnabled = true;

    /**
     * the offset in pixels this component has on the x-axis
     */
    protected float mXOffset = 5f;

    /**
     * the offset in pixels this component has on the Y-axis
     */
    protected float mYOffset = 5f;

    /**
     * the typeface used for the labels
     */
    protected Typeface mTypeface = null;

    /**
     * the text size of the labels
     */
    protected float mTextSize = Utils.convertDpToPixel(10f);

    /**
     * the text color to use for the labels
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
     *
     * @param yOffset
     */
    public void setYOffset(float yOffset) {
        mYOffset = Utils.convertDpToPixel(yOffset);
    }

    /**
     * returns the Typeface used for the labels, returns null if none is set
     *
     * @return
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
     * returns the text size that is currently set for the labels, in pixels
     *
     * @return
     */
    public float getTextSize() {
        return mTextSize;
    }


    /**
     * 设置轴标签的文字颜色。在使用资源的颜色时，请确保使用 getResources().getColor(...)
     *
     * @param color
     */
    public void setTextColor(int color) {
        mTextColor = color;
    }

    /**
     * Returns the text color that is set for the labels.
     *
     * @return
     */
    public int getTextColor() {
        return mTextColor;
    }

    /**
     * 如果设置true，这个组件被启用或者被绘制。如果禁用，将不绘制该组件。
     * 默认: true
     *
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    /**
     * Returns true if this comonent is enabled (should be drawn), false if not.
     *
     * @return
     */
    public boolean isEnabled() {
        return mEnabled;
    }
}
