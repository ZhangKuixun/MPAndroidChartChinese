
package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis;

/**
 * 这个类可以用来得到当前高亮条目的信息，或者用来告诉Chart，需要高亮哪些条目。
 *
 * @author Philipp Jahoda
 */
public class Highlight {

    /**
     * 高亮值的X值
     */
    private float mX = Float.NaN;

    /**
     * 高亮值的y值
     */
    private float mY = Float.NaN;

    /**
     * 高亮值的x像素点
     */
    private float mXPx;

    /**
     * 高亮值的y像素点
     */
    private float mYPx;

    /**
     * the index of the data object - in case it refers to more than one
     */
    private int mDataIndex = -1;

    /**
     * dataSet 高亮值的下标
     */
    private int mDataSetIndex;

    /**
     * 突出显示的柱状条目索引，默认值为-1。
     */
    private int mStackIndex = -1;

    /**
     * the axis the highlighted value belongs to
     */
    private YAxis.AxisDependency axis;

    /**
     * the x-position (pixels) on which this highlight object was last drawn
     * 最后突出显示该突出对象的x位置（像素）。
     */
    private float mDrawX;

    /**
     * the y-position (pixels) on which this highlight object was last drawn
     */
    private float mDrawY;

    public Highlight(float x, float y, int dataSetIndex) {
        this.mX = x;
        this.mY = y;
        this.mDataSetIndex = dataSetIndex;
    }

    public Highlight(float x, int dataSetIndex, int stackIndex) {
        this(x, Float.NaN, dataSetIndex);
        this.mStackIndex = stackIndex;
    }

    /**
     * constructor
     *
     * @param x            the x-value of the highlighted value
     * @param y            the y-value of the highlighted value
     * @param dataSetIndex the index of the DataSet the highlighted value belongs to
     */
    public Highlight(float x, float y, float xPx, float yPx, int dataSetIndex, YAxis.AxisDependency axis) {
        this.mX = x;
        this.mY = y;
        this.mXPx = xPx;
        this.mYPx = yPx;
        this.mDataSetIndex = dataSetIndex;
        this.axis = axis;
    }

    /**
     * 构造函数，柱状图
     *
     * @param x            X轴上高亮值的索引
     * @param y            高亮的Y值
     * @param dataSetIndex 突出显示值的 DataSet 的索引
     * @param stackIndex   已选择柱状图条目的值的引用
     */
    public Highlight(float x, float y, float xPx, float yPx, int dataSetIndex, int stackIndex, YAxis.AxisDependency axis) {
        this(x, y, xPx, yPx, dataSetIndex, axis);
        this.mStackIndex = stackIndex;
    }

    /**
     * 返回x值的高亮值
     */
    public float getX() {
        return mX;
    }

    /**
     * 返回Y值中的高亮值
     */
    public float getY() {
        return mY;
    }

    /**
     * 返回X轴位置的高亮像素（单位：px）
     */
    public float getXPx() {
        return mXPx;
    }

    /**
     * 返回Y轴位置的高亮值（单位：px）
     */
    public float getYPx() {
        return mYPx;
    }

    /**
     * 数据对象的索引-如果它引用多于一个
     */
    public int getDataIndex() {
        return mDataIndex;
    }

    public void setDataIndex(int mDataIndex) {
        this.mDataIndex = mDataIndex;
    }

    /**
     * 返回 DataSet 中突出显示的值的索引
     */
    public int getDataSetIndex() {
        return mDataSetIndex;
    }

    /**
     * Only needed if a stacked-barchart entry was highlighted. References the
     * selected value within the stacked-entry.
     *
     * @return
     */
    public int getStackIndex() {
        return mStackIndex;
    }

    public boolean isStacked() {
        return mStackIndex >= 0;
    }

    /**
     * Returns the axis the highlighted value belongs to.
     *
     * @return
     */
    public YAxis.AxisDependency getAxis() {
        return axis;
    }

    /**
     * Sets the x- and y-position (pixels) where this highlight was last drawn.
     *
     * @param x
     * @param y
     */
    public void setDraw(float x, float y) {
        this.mDrawX = x;
        this.mDrawY = y;
    }

    /**
     * Returns the x-position in pixels where this highlight object was last drawn.
     * 返回的X轴位置,是最后绘制此高亮对象的像素。
     *
     * @return
     */
    public float getDrawX() {
        return mDrawX;
    }

    /**
     * Returns the y-position in pixels where this highlight object was last drawn.
     *
     * @return
     */
    public float getDrawY() {
        return mDrawY;
    }

    /**
     * 如果此高亮对象等于另一个对象，则返回true（比较xIndex和dataSetIndex）
     */
    public boolean equalTo(Highlight h) {

        if (h == null)
            return false;
        else {
            if (this.mDataSetIndex == h.mDataSetIndex && this.mX == h.mX
                    && this.mStackIndex == h.mStackIndex && this.mDataIndex == h.mDataIndex)
                return true;
            else
                return false;
        }
    }

    @Override
    public String toString() {
        return "Highlight, x: " + mX + ", y: " + mY + ", dataSetIndex: " + mDataSetIndex
                + ", stackIndex (only stacked barentry): " + mStackIndex;
    }
}
