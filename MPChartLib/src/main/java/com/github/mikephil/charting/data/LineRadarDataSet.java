
package com.github.mikephil.charting.data;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.List;

/**
 * Base dataset for line and radar DataSets.
 *
 * @author Philipp Jahoda
 */
public abstract class LineRadarDataSet<T extends Entry> extends LineScatterCandleRadarDataSet<T> implements ILineRadarDataSet<T> {

    /**
     * the color that is used for filling the line surface
     */
    private int mFillColor = Color.rgb(140, 234, 255);

    /**
     * the drawable to be used for filling the line surface
     */
    protected Drawable mFillDrawable;

    /**
     * transparency used for filling line surface
     */
    private int mFillAlpha = 85;

    /**
     * the width of the drawn data lines
     */
    private float mLineWidth = 2.5f;

    /**
     * if true, the data will also be drawn filled
     */
    private boolean mDrawFilled = false;


    public LineRadarDataSet(List<T> yVals, String label) {
        super(yVals, label);
    }

    @Override
    public int getFillColor() {
        return mFillColor;
    }

    /**
     * 设置填充颜色。
     * <p>
     * 设置用于填充线下区域的颜色。最终设置“fillDrawable”。
     *
     * @param color
     */
    public void setFillColor(int color) {
        mFillColor = color;
        mFillDrawable = null;
    }

    @Override
    public Drawable getFillDrawable() {
        return mFillDrawable;
    }

    /**
     * Sets the drawable to be used to fill the area below the line.
     *
     * @param drawable
     */
    @TargetApi(18)
    public void setFillDrawable(Drawable drawable) {
        this.mFillDrawable = drawable;
    }

    @Override
    public int getFillAlpha() {
        return mFillAlpha;
    }

    /**
     * sets the alpha value (transparency) that is used for filling the line
     * surface (0-255), default: 85。
     * <p>
     * 设定填充线面的alpha值（即透明度 0-255）。默认：85。255 =完全不透明，0 =完全透明。
     *
     * @param alpha
     */
    public void setFillAlpha(int alpha) {
        mFillAlpha = alpha;
    }

    /**
     * 设置该 DataSet 的线宽（最小= 0.2F，最大= 10F）；默认1F；注意：线越细==性能越好，线越厚==性能越糟糕 。
     *
     * @param width
     */
    public void setLineWidth(float width) {

        if (width < 0.0f)
            width = 0.0f;
        if (width > 10.0f)
            width = 10.0f;
        mLineWidth = Utils.convertDpToPixel(width);
    }

    @Override
    public float getLineWidth() {
        return mLineWidth;
    }

    @Override
    public void setDrawFilled(boolean filled) {
        mDrawFilled = filled;
    }

    @Override
    public boolean isDrawFilledEnabled() {
        return mDrawFilled;
    }

    protected void copy(LineRadarDataSet lineRadarDataSet) {
        super.copy(lineRadarDataSet);
        lineRadarDataSet.mDrawFilled = mDrawFilled;
        lineRadarDataSet.mFillAlpha = mFillAlpha;
        lineRadarDataSet.mFillColor = mFillColor;
        lineRadarDataSet.mFillDrawable = mFillDrawable;
        lineRadarDataSet.mLineWidth = mLineWidth;
    }
}
