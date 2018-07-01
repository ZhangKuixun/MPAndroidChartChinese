
package com.github.mikephil.charting.components;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;

import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Base-class of all axes (previously called labels).
 *
 * @author Philipp Jahoda
 */
public abstract class AxisBase extends ComponentBase {

    /**
     * custom formatter that is used instead of the auto-formatter if set
     */
    protected IAxisValueFormatter mAxisValueFormatter;

    private int mGridColor = Color.GRAY;

    private float mGridLineWidth = 1f;

    private int mAxisLineColor = Color.GRAY;

    private float mAxisLineWidth = 1f;

    /**
     * the actual array of entries
     */
    public float[] mEntries = new float[]{};

    /**
     * axis label entries only used for centered labels
     */
    public float[] mCenteredEntries = new float[]{};

    /**
     * the number of entries the legend contains
     */
    public int mEntryCount;

    /**
     * the number of decimal digits to use
     */
    public int mDecimals;

    /**
     * the number of label entries the axis should have, default 6
     */
    private int mLabelCount = 6;

    /**
     * the minimum interval between axis values
     */
    protected float mGranularity = 1.0f;

    /**
     * When true, axis labels are controlled by the `granularity` property.
     * When false, axis values could possibly be repeated.
     * This could happen if two adjacent axis values are rounded to same value.
     * If using granularity this could be avoided by having fewer axis values visible.
     */
    protected boolean mGranularityEnabled = false;

    /**
     * if true, the set number of y-labels will be forced
     */
    protected boolean mForceLabels = false;

    /**
     * flag indicating if the grid lines for this axis should be drawn
     */
    protected boolean mDrawGridLines = true;

    /**
     * flag that indicates if the line alongside the axis is drawn or not
     */
    protected boolean mDrawAxisLine = true;

    /**
     * flag that indicates of the labels of this axis should be drawn or not
     */
    protected boolean mDrawLabels = true;

    protected boolean mCenterAxisLabels = false;

    /**
     * the path effect of the axis line that makes dashed lines possible
     */
    private DashPathEffect mAxisLineDashPathEffect = null;

    /**
     * the path effect of the grid lines that makes dashed lines possible
     */
    private DashPathEffect mGridDashPathEffect = null;

    /**
     * array of limit lines that can be set for the axis
     */
    protected List<LimitLine> mLimitLines;

    /**
     * flag indicating the limit lines layer depth
     */
    protected boolean mDrawLimitLineBehindData = false;

    /**
     * flag indicating the grid lines layer depth
     */
    protected boolean mDrawGridLinesBehindData = true;

    /**
     * Extra spacing for `axisMinimum` to be added to automatically calculated `axisMinimum`
     */
    protected float mSpaceMin = 0.f;

    /**
     * Extra spacing for `axisMaximum` to be added to automatically calculated `axisMaximum`
     */
    protected float mSpaceMax = 0.f;

    /**
     * flag indicating that the axis-min value has been customized
     */
    protected boolean mCustomAxisMin = false;

    /**
     * flag indicating that the axis-max value has been customized
     */
    protected boolean mCustomAxisMax = false;

    /**
     * don't touch this direclty, use setter
     */
    public float mAxisMaximum = 0f;

    /**
     * don't touch this directly, use setter
     */
    public float mAxisMinimum = 0f;

    /**
     * the total range of values this axis covers
     */
    public float mAxisRange = 0f;

    /**
     * default constructor
     */
    public AxisBase() {
        this.mTextSize = Utils.convertDpToPixel(10f);
        this.mXOffset = Utils.convertDpToPixel(5f);
        this.mYOffset = Utils.convertDpToPixel(5f);
        this.mLimitLines = new ArrayList<LimitLine>();
    }

    /**
     * 将其设置为true可以绘制轴的网格线。
     *
     * @param enabled true，则绘制网格线
     */
    public void setDrawGridLines(boolean enabled) {
        mDrawGridLines = enabled;
    }

    /**
     * Returns true if drawing grid lines is enabled for this axis.
     *
     * @return
     */
    public boolean isDrawGridLinesEnabled() {
        return mDrawGridLines;
    }

    /**
     * 如果要画出与轴线并排的线，则将其设置为true。
     * 默认：true
     *
     * @param enabled true，则绘制该行旁边的轴线
     */
    public void setDrawAxisLine(boolean enabled) {
        mDrawAxisLine = enabled;
    }

    /**
     * Returns true if the line alongside the axis should be drawn.
     *
     * @return
     */
    public boolean isDrawAxisLineEnabled() {
        return mDrawAxisLine;
    }

    /**
     * Centers the axis labels instead of drawing them at their original position.
     * This is useful especially for grouped BarChart.
     *
     * @param enabled
     */
    public void setCenterAxisLabels(boolean enabled) {
        mCenterAxisLabels = enabled;
    }

    public boolean isCenterAxisLabelsEnabled() {
        return mCenterAxisLabels && mEntryCount > 0;
    }

    /**
     * 设置轴网格线的颜色（来自每个标签的水平线）。
     *
     * @param color
     */
    public void setGridColor(int color) {
        mGridColor = color;
    }

    /**
     * Returns the color of the grid lines for this axis (the horizontal lines
     * coming from each label).
     *
     * @return
     */
    public int getGridColor() {
        return mGridColor;
    }

    /**
     * 设置图表周围的边框宽度（dp）。
     *
     * @param width
     */
    public void setAxisLineWidth(float width) {
        mAxisLineWidth = Utils.convertDpToPixel(width);
    }

    /**
     * Returns the width of the axis line (line alongside the axis).
     *
     * @return
     */
    public float getAxisLineWidth() {
        return mAxisLineWidth;
    }

    /**
     * 设置从每个轴标签绘制的网格线的宽度。
     *
     * @param width
     */
    public void setGridLineWidth(float width) {
        mGridLineWidth = Utils.convertDpToPixel(width);
    }

    /**
     * 返回从每个轴标签绘制的网格线的宽度。
     *
     * @return
     */
    public float getGridLineWidth() {
        return mGridLineWidth;
    }

    /**
     * 设置图表周围边框的颜色。
     *
     * @param color
     */
    public void setAxisLineColor(int color) {
        mAxisLineColor = color;
    }

    /**
     * Returns the color of the axis line (line alongside the axis).
     *
     * @return
     */
    public int getAxisLineColor() {
        return mAxisLineColor;
    }

    /**
     * 将此设置为true，以便绘制该轴的标签（这不会影响绘制网格线或轴线）。
     *
     * @param enabled true，则绘制轴的标签
     */
    public void setDrawLabels(boolean enabled) {
        mDrawLabels = enabled;
    }

    /**
     * Returns true if drawing the labels is enabled for this axis.
     *
     * @return
     */
    public boolean isDrawLabelsEnabled() {
        return mDrawLabels;
    }

    /**
     * 设置当前轴(x轴/y轴)的标签数量。
     * <p>
     * 请注意:
     * 1.参数count范围：max＝25，min＝2，默认值：6
     * 2.这个数字是不固定 if(force == false)，只能是近似的。如果 if(force == true)，则确切绘制指定数量的标签，但这样可能导致轴线分布不均匀。
     *
     * @param count 应该显示的当前轴(x轴/y轴)标签的数量
     */
    public void setLabelCount(int count) {

        if (count > 25)
            count = 25;
        if (count < 2)
            count = 2;

        mLabelCount = count;
        mForceLabels = false;
    }

    /**
     * 设置当前轴(x轴/y轴)的标签数量。请注意，这个数字不是固定的（如果 force == false），并且只能近似。
     * <p>
     * 请注意:
     * 1.参数count范围：max＝25，min＝2，默认值：6
     * 2.这个数字是不固定 if(force == false)，只能是近似的。如果 if(force == true)，则确切绘制指定数量的标签，但这样可能导致轴线分布不均匀。
     *
     * @param count 当前轴(x轴/y轴)应该显示的标签数量
     * @param force 如果启用，将强制设置设置标签计数，标签的精确计数将被绘制并沿轴均匀分布 - 这可能导致轴上的数值不均匀。
     */
    public void setLabelCount(int count, boolean force) {

        setLabelCount(count);
        mForceLabels = force;
    }

    /**
     * Returns true if focing the y-label count is enabled. Default: false
     *
     * @return
     */
    public boolean isForceLabelsEnabled() {
        return mForceLabels;
    }

    /**
     * Returns the number of label entries the y-axis should have
     *
     * @return
     */
    public int getLabelCount() {
        return mLabelCount;
    }

    /**
     * @return true if granularity is enabled
     */
    public boolean isGranularityEnabled() {
        return mGranularityEnabled;
    }

    /**
     * 启用/禁用轴值间隔的粒度控制。如果启用，轴间隔不允许低于某个粒度。
     * 默认值：false
     *
     * @param enabled
     */
    public void setGranularityEnabled(boolean enabled) {
        mGranularityEnabled = enabled;
    }

    /**
     * @return the minimum interval between axis values
     */
    public float getGranularity() {
        return mGranularity;
    }

    /**
     * 在缩放时为轴设置最小间隔。轴不允许低于这个极限。这可以用来避免缩放时的标签值重复。
     *
     * @param granularity
     */
    public void setGranularity(float granularity) {
        mGranularity = granularity;
        // set this to true if it was disabled, as it makes no sense to call this method with granularity disabled
        mGranularityEnabled = true;
    }

    /*两个轴都支持所谓的 LimitLine，允许提供特殊信息，如边界或约束。 添加到 YAxis 的 LimitLines 在水平方向绘制，并且在添加到 XAxis 时在垂直方向绘制。*/

    /**
     * 给轴添加一条LimitLine
     *
     * @param l
     */
    public void addLimitLine(LimitLine l) {
        mLimitLines.add(l);

        if (mLimitLines.size() > 6) {
            Log.e("MPAndroiChart",
                    "Warning! You have more than 6 LimitLines on your axis, do you really want " +
                            "that?");
        }
    }

    /**
     * 移除轴上一条指定的 LimitLine
     *
     * @param l
     */
    public void removeLimitLine(LimitLine l) {
        mLimitLines.remove(l);
    }

    /**
     * 从轴移除所有的限制线
     */
    public void removeAllLimitLines() {
        mLimitLines.clear();
    }

    /**
     * 返回这个轴的限制线
     *
     * @return
     */
    public List<LimitLine> getLimitLines() {
        return mLimitLines;
    }

    /**
     * 允许控制 LimitLines 和实际数据之间的z顺序。 如果将此设置为 true，则 LimitLine 将在实际数据之后绘制，否则在顶部。
     * 默认值：false
     *
     * @param enabled
     */
    public void setDrawLimitLinesBehindData(boolean enabled) {
        mDrawLimitLineBehindData = enabled;
    }

    public boolean isDrawLimitLinesBehindDataEnabled() {
        return mDrawLimitLineBehindData;
    }

    /**
     * If this is set to false, the grid lines are draw on top of the actual data,
     * otherwise behind. Default: true
     *
     * @param enabled
     */
    public void setDrawGridLinesBehindData(boolean enabled) {
        mDrawGridLinesBehindData = enabled;
    }

    public boolean isDrawGridLinesBehindDataEnabled() {
        return mDrawGridLinesBehindData;
    }

    /**
     * Returns the longest formatted label (in terms of characters), this axis
     * contains.
     *
     * @return
     */
    public String getLongestLabel() {

        String longest = "";

        for (int i = 0; i < mEntries.length; i++) {
            String text = getFormattedLabel(i);

            if (text != null && longest.length() < text.length())
                longest = text;
        }

        return longest;
    }

    public String getFormattedLabel(int index) {

        if (index < 0 || index >= mEntries.length)
            return "";
        else
            return getValueFormatter().getFormattedValue(mEntries[index], this);
    }

    /**
     * 设置轴标签的自定义格式
     * 如果没有设置标签格式化，将自动为图表中绘制所有合理格式化的标签（有关小数）。
     * 使用 chart.getDefaultValueFormatter() 去使用图表计算格式化的标签。
     *
     * @param f
     */
    public void setValueFormatter(IAxisValueFormatter f) {

        if (f == null)
            mAxisValueFormatter = new DefaultAxisValueFormatter(mDecimals);
        else
            mAxisValueFormatter = f;
    }

    /**
     * Returns the formatter used for formatting the axis labels.
     *
     * @return
     */
    public IAxisValueFormatter getValueFormatter() {

        if (mAxisValueFormatter == null ||
                (mAxisValueFormatter instanceof DefaultAxisValueFormatter &&
                        ((DefaultAxisValueFormatter) mAxisValueFormatter).getDecimalDigits() != mDecimals))
            mAxisValueFormatter = new DefaultAxisValueFormatter(mDecimals);

        return mAxisValueFormatter;
    }

    /**
     * 使网格线以虚线模式绘制，例如”- - - - - -”。只有当硬件加速关闭时，这才有效。请记住硬件加速提高性能。
     *
     * @param lineLength  线段的长度
     * @param spaceLength 线间的空间
     * @param phase       起点 (通常使用0)
     */
    public void enableGridDashedLine(float lineLength, float spaceLength, float phase) {
        mGridDashPathEffect = new DashPathEffect(new float[]{
                lineLength, spaceLength
        }, phase);
    }

    /**
     * Enables the grid line to be drawn in dashed mode, e.g. like this
     * "- - - - - -". THIS ONLY WORKS IF HARDWARE-ACCELERATION IS TURNED OFF.
     * Keep in mind that hardware acceleration boosts performance.
     *
     * @param effect the DashPathEffect
     */
    public void setGridDashedLine(DashPathEffect effect) {
        mGridDashPathEffect = effect;
    }

    /**
     * Disables the grid line to be drawn in dashed mode.
     */
    public void disableGridDashedLine() {
        mGridDashPathEffect = null;
    }

    /**
     * Returns true if the grid dashed-line effect is enabled, false if not.
     *
     * @return
     */
    public boolean isGridDashedLineEnabled() {
        return mGridDashPathEffect == null ? false : true;
    }

    /**
     * returns the DashPathEffect that is set for grid line
     *
     * @return
     */
    public DashPathEffect getGridDashPathEffect() {
        return mGridDashPathEffect;
    }


    /**
     * Enables the axis line to be drawn in dashed mode, e.g. like this
     * "- - - - - -". THIS ONLY WORKS IF HARDWARE-ACCELERATION IS TURNED OFF.
     * Keep in mind that hardware acceleration boosts performance.
     *
     * @param lineLength  the length of the line pieces
     * @param spaceLength the length of space in between the pieces
     * @param phase       offset, in degrees (normally, use 0)
     */
    public void enableAxisLineDashedLine(float lineLength, float spaceLength, float phase) {
        mAxisLineDashPathEffect = new DashPathEffect(new float[]{
                lineLength, spaceLength
        }, phase);
    }

    /**
     * Enables the axis line to be drawn in dashed mode, e.g. like this
     * "- - - - - -". THIS ONLY WORKS IF HARDWARE-ACCELERATION IS TURNED OFF.
     * Keep in mind that hardware acceleration boosts performance.
     *
     * @param effect the DashPathEffect
     */
    public void setAxisLineDashedLine(DashPathEffect effect) {
        mAxisLineDashPathEffect = effect;
    }

    /**
     * Disables the axis line to be drawn in dashed mode.
     */
    public void disableAxisLineDashedLine() {
        mAxisLineDashPathEffect = null;
    }

    /**
     * Returns true if the axis dashed-line effect is enabled, false if not.
     *
     * @return
     */
    public boolean isAxisLineDashedLineEnabled() {
        return mAxisLineDashPathEffect == null ? false : true;
    }

    /**
     * returns the DashPathEffect that is set for axis line
     *
     * @return
     */
    public DashPathEffect getAxisLineDashPathEffect() {
        return mAxisLineDashPathEffect;
    }

    /**
     * ###### BELOW CODE RELATED TO CUSTOM AXIS VALUES ######
     */

    public float getAxisMaximum() {
        return mAxisMaximum;
    }

    public float getAxisMinimum() {
        return mAxisMinimum;
    }

    /**
     * 调用此方法来撤销先前设置的最大值。 通过这样做，你将再次允许轴自动计算它的最大值。
     */
    public void resetAxisMaximum() {
        mCustomAxisMax = false;
    }

    /**
     * Returns true if the axis max value has been customized (and is not calculated automatically)
     *
     * @return
     */
    public boolean isAxisMaxCustom() {
        return mCustomAxisMax;
    }

    /**
     * By calling this method, any custom minimum value that has been previously set is reseted,
     * and the calculation is
     * done automatically.
     * 调用此方法来撤销先前设置的最小值。 通过这样做，你将再次允许轴自动计算它的最小值。
     * 通过调用此方法，预先设置的任何自定义最小值被重设，并且计算自动完成。
     */
    public void resetAxisMinimum() {
        mCustomAxisMin = false;
    }

    /**
     * Returns true if the axis min value has been customized (and is not calculated automatically)
     *
     * @return
     */
    public boolean isAxisMinCustom() {
        return mCustomAxisMin;
    }

    /**
     * 为该轴设置自定义最小值。如果设置，该值将不会根据所提供的数据自动计算。使用 resetAxisMinValue()来撤消此操作。
     * 如果使用此方法，请不要忘记调用 setStartAtZero(false)。否则，轴的最小值仍将被强制为0。
     *
     * @param min
     */
    public void setAxisMinimum(float min) {
        mCustomAxisMin = true;
        mAxisMinimum = min;
        this.mAxisRange = Math.abs(mAxisMaximum - min);
    }

    /**
     * Use setAxisMinimum(...) instead.
     *
     * @param min
     */
    @Deprecated
    public void setAxisMinValue(float min) {
        setAxisMinimum(min);
    }

    /**
     * 为该轴设置自定义最大值。 如果设置，此值将不会根据提供的数据自动计算.
     * 使用 resetAxisMaxValue() 来撤消此操作。
     *
     * @param max
     */
    public void setAxisMaximum(float max) {
        mCustomAxisMax = true;
        mAxisMaximum = max;
        this.mAxisRange = Math.abs(max - mAxisMinimum);
    }

    /**
     * Use setAxisMaximum(...) instead.
     *
     * @param max
     */
    @Deprecated
    public void setAxisMaxValue(float max) {
        setAxisMaximum(max);
    }

    /**
     * Calculates the minimum / maximum  and range values of the axis with the given
     * minimum and maximum values from the chart data.
     *
     * @param dataMin the min value according to chart data
     * @param dataMax the max value according to chart data
     */
    public void calculate(float dataMin, float dataMax) {

        // if custom, use value as is, else use data value
        float min = mCustomAxisMin ? mAxisMinimum : (dataMin - mSpaceMin);
        float max = mCustomAxisMax ? mAxisMaximum : (dataMax + mSpaceMax);

        // temporary range (before calculations)
        float range = Math.abs(max - min);

        // in case all values are equal
        if (range == 0f) {
            max = max + 1f;
            min = min - 1f;
        }

        this.mAxisMinimum = min;
        this.mAxisMaximum = max;

        // actual range
        this.mAxisRange = Math.abs(max - min);
    }

    /**
     * Gets extra spacing for `axisMinimum` to be added to automatically calculated `axisMinimum`
     */
    public float getSpaceMin() {
        return mSpaceMin;
    }

    /**
     * Sets extra spacing for `axisMinimum` to be added to automatically calculated `axisMinimum`
     */
    public void setSpaceMin(float mSpaceMin) {
        this.mSpaceMin = mSpaceMin;
    }

    /**
     * Gets extra spacing for `axisMaximum` to be added to automatically calculated `axisMaximum`
     */
    public float getSpaceMax() {
        return mSpaceMax;
    }

    /**
     * Sets extra spacing for `axisMaximum` to be added to automatically calculated `axisMaximum`
     */
    public void setSpaceMax(float mSpaceMax) {
        this.mSpaceMax = mSpaceMax;
    }
}
