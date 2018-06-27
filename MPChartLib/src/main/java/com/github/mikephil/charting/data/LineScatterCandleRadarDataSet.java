package com.github.mikephil.charting.data;

import android.graphics.DashPathEffect;

import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.List;

/**
 * Created by Philipp Jahoda on 11/07/15.
 */
public abstract class LineScatterCandleRadarDataSet<T extends Entry> extends BarLineScatterCandleBubbleDataSet<T> implements ILineScatterCandleRadarDataSet<T> {

    protected boolean mDrawVerticalHighlightIndicator = true;
    protected boolean mDrawHorizontalHighlightIndicator = true;

    /**
     * 高亮指示线的宽度
     */
    protected float mHighlightLineWidth = 0.5f;

    /**
     * 虚线高亮线的路径效果
     */
    protected DashPathEffect mHighlightDashPathEffect = null;


    public LineScatterCandleRadarDataSet(List<T> yVals, String label) {
        super(yVals, label);
        mHighlightLineWidth = Utils.convertDpToPixel(0.5f);
    }

    /**
     * 启用/禁用水平高亮指示器。如果禁用，则不绘制指示器。
     * 默认：true
     *
     * @param enabled
     */
    public void setDrawHorizontalHighlightIndicator(boolean enabled) {
        this.mDrawHorizontalHighlightIndicator = enabled;
    }

    /**
     * 启用/禁用垂直高亮指示器。如果禁用，则不绘制指示器。
     * 默认：true
     *
     * @param enabled
     */
    public void setDrawVerticalHighlightIndicator(boolean enabled) {
        this.mDrawVerticalHighlightIndicator = enabled;
    }

    /**
     * 启用/禁用垂直和水平高亮指示器。
     *
     * @param enabled
     */
    public void setDrawHighlightIndicators(boolean enabled) {
        setDrawVerticalHighlightIndicator(enabled);
        setDrawHorizontalHighlightIndicator(enabled);
    }

    @Override
    public boolean isVerticalHighlightIndicatorEnabled() {
        return mDrawVerticalHighlightIndicator;
    }

    @Override
    public boolean isHorizontalHighlightIndicatorEnabled() {
        return mDrawHorizontalHighlightIndicator;
    }

    /**
     * 设置高亮线的宽度，单位dp
     *
     * @param width
     */
    public void setHighlightLineWidth(float width) {
        mHighlightLineWidth = Utils.convertDpToPixel(width);
    }

    @Override
    public float getHighlightLineWidth() {
        return mHighlightLineWidth;
    }

    /**
     * 允许以虚线模式绘制高亮线，e.g. like this "- - - - - -"
     *
     * @param lineLength  线段长度
     * @param spaceLength 线段之间的空隙长度
     * @param phase       偏移量（通常使用0）
     */
    public void enableDashedHighlightLine(float lineLength, float spaceLength, float phase) {
        mHighlightDashPathEffect = new DashPathEffect(new float[]{
                lineLength, spaceLength
        }, phase);
    }

    /**
     * 禁用以虚线模式绘制的高亮线。
     */
    public void disableDashedHighlightLine() {
        mHighlightDashPathEffect = null;
    }

    /**
     * 如果对高亮行启用虚线效果，则返回true，如果否则为false：默认：disabled
     *
     * @return
     */
    public boolean isDashedHighlightLineEnabled() {
        return mHighlightDashPathEffect != null;
    }

    @Override
    public DashPathEffect getDashPathEffectHighlight() {
        return mHighlightDashPathEffect;
    }

    protected void copy(LineScatterCandleRadarDataSet lineScatterCandleRadarDataSet) {
        super.copy(lineScatterCandleRadarDataSet);
        lineScatterCandleRadarDataSet.mDrawHorizontalHighlightIndicator = mDrawHorizontalHighlightIndicator;
        lineScatterCandleRadarDataSet.mDrawVerticalHighlightIndicator = mDrawVerticalHighlightIndicator;
        lineScatterCandleRadarDataSet.mHighlightLineWidth = mHighlightLineWidth;
        lineScatterCandleRadarDataSet.mHighlightDashPathEffect = mHighlightDashPathEffect;
    }
}
