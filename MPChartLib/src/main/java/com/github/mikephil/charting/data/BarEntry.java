package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.highlight.Range;

/**
 * 堆积柱形图。
 * <p>
 * 堆积柱形图的使用方式和正常柱形图的使用方式基本相同。有点差异的是 BarEntry 的创建方式不太一样，
 * 在堆叠柱形图的使用中，需要使用到 BarEntry 的另一个构造方法：
 * public BarEntry(float x, float [] yValues) { ... }
 * 这个构造方法允许传入多个 yValues，代表每个堆积的柱形图的值。下面有个例子：
 * BarEntry stackedEntry = new BarEntry(0f, new float[] { 10, 20, 30 });
 * 这个BarEntry由3个堆积的值组成，高度分别是10,20,30
 *
 * @author Philipp Jahoda
 */
@SuppressLint("ParcelCreator")
public class BarEntry extends Entry {

    /**
     * the values the stacked barchart holds
     */
    private float[] mYVals;

    /**
     * the ranges for the individual stack values - automatically calculated
     */
    private Range[] mRanges;

    /**
     * the sum of all negative values this entry (if stacked) contains
     */
    private float mNegativeSum;

    /**
     * the sum of all positive values this entry (if stacked) contains
     */
    private float mPositiveSum;

    /**
     * 正常条的构造函数（不堆积）。
     *
     * @param x
     * @param y
     */
    public BarEntry(float x, float y) {
        super(x, y);
    }

    /**
     * Constructor for normal bars (not stacked).
     * 正常栏（不堆叠）的构造函数。
     *
     * @param x
     * @param y
     * @param data 该条目表示附加数据点
     */
    public BarEntry(float x, float y, Object data) {
        super(x, y, data);
    }

    /**
     * Constructor for normal bars (not stacked).
     *
     * @param x
     * @param y
     * @param icon - icon image
     */
    public BarEntry(float x, float y, Drawable icon) {
        super(x, y, icon);
    }

    /**
     * Constructor for normal bars (not stacked).
     *
     * @param x
     * @param y
     * @param icon - icon image
     * @param data - 该条目表示附加数据点
     */
    public BarEntry(float x, float y, Drawable icon, Object data) {
        super(x, y, icon, data);
    }

    /**
     * Constructor for stacked bar entries. One data object for whole stack
     *
     * @param x
     * @param vals - the stack values, use at least 2
     */
    public BarEntry(float x, float[] vals) {
        super(x, calcSum(vals));

        this.mYVals = vals;
        calcPosNegSum();
        calcRanges();
    }

    /**
     * Constructor for stacked bar entries. One data object for whole stack
     *
     * @param x
     * @param vals - the stack values, use at least 2
     * @param data - 该条目表示附加数据点
     */
    public BarEntry(float x, float[] vals, Object data) {
        super(x, calcSum(vals), data);

        this.mYVals = vals;
        calcPosNegSum();
        calcRanges();
    }

    /**
     * Constructor for stacked bar entries. One data object for whole stack
     *
     * @param x
     * @param vals - the stack values, use at least 2
     * @param icon - icon image
     */
    public BarEntry(float x, float[] vals, Drawable icon) {
        super(x, calcSum(vals), icon);

        this.mYVals = vals;
        calcPosNegSum();
        calcRanges();
    }

    /**
     * Constructor for stacked bar entries. One data object for whole stack
     *
     * @param x
     * @param vals - the stack values, use at least 2
     * @param icon - icon image
     * @param data - 该条目表示附加数据点
     */
    public BarEntry(float x, float[] vals, Drawable icon, Object data) {
        super(x, calcSum(vals), icon, data);

        this.mYVals = vals;
        calcPosNegSum();
        calcRanges();
    }

    /**
     * Returns an exact copy of the BarEntry.
     */
    public BarEntry copy() {

        BarEntry copied = new BarEntry(getX(), getY(), getData());
        copied.setVals(mYVals);
        return copied;
    }

    /**
     * Returns the stacked values this BarEntry represents, or null, if only a single value is represented (then, use
     * getY()).
     *
     * @return
     */
    public float[] getYVals() {
        return mYVals;
    }

    /**
     * Set the array of values this BarEntry should represent.
     *
     * @param vals
     */
    public void setVals(float[] vals) {
        setY(calcSum(vals));
        mYVals = vals;
        calcPosNegSum();
        calcRanges();
    }

    /**
     * Returns the value of this BarEntry. If the entry is stacked, it returns the positive sum of all values.
     *
     * @return
     */
    @Override
    public float getY() {
        return super.getY();
    }

    /**
     * Returns the ranges of the individual stack-entries. Will return null if this entry is not stacked.
     *
     * @return
     */
    public Range[] getRanges() {
        return mRanges;
    }

    /**
     * Returns true if this BarEntry is stacked (has a values array), false if not.
     *
     * @return
     */
    public boolean isStacked() {
        return mYVals != null;
    }

    /**
     * Use `getSumBelow(stackIndex)` instead.
     */
    @Deprecated
    public float getBelowSum(int stackIndex) {
        return getSumBelow(stackIndex);
    }

    public float getSumBelow(int stackIndex) {

        if (mYVals == null)
            return 0;

        float remainder = 0f;
        int index = mYVals.length - 1;

        while (index > stackIndex && index >= 0) {
            remainder += mYVals[index];
            index--;
        }

        return remainder;
    }

    /**
     * Reuturns the sum of all positive values this entry (if stacked) contains.
     *
     * @return
     */
    public float getPositiveSum() {
        return mPositiveSum;
    }

    /**
     * Returns the sum of all negative values this entry (if stacked) contains. (this is a positive number)
     *
     * @return
     */
    public float getNegativeSum() {
        return mNegativeSum;
    }

    private void calcPosNegSum() {

        if (mYVals == null) {
            mNegativeSum = 0;
            mPositiveSum = 0;
            return;
        }

        float sumNeg = 0f;
        float sumPos = 0f;

        for (float f : mYVals) {
            if (f <= 0f)
                sumNeg += Math.abs(f);
            else
                sumPos += f;
        }

        mNegativeSum = sumNeg;
        mPositiveSum = sumPos;
    }

    /**
     * Calculates the sum across all values of the given stack.
     *
     * @param vals
     * @return
     */
    private static float calcSum(float[] vals) {

        if (vals == null)
            return 0f;

        float sum = 0f;

        for (float f : vals)
            sum += f;

        return sum;
    }

    protected void calcRanges() {

        float[] values = getYVals();

        if (values == null || values.length == 0)
            return;

        mRanges = new Range[values.length];

        float negRemain = -getNegativeSum();
        float posRemain = 0f;

        for (int i = 0; i < mRanges.length; i++) {

            float value = values[i];

            if (value < 0) {
                mRanges[i] = new Range(negRemain, negRemain - value);
                negRemain -= value;
            } else {
                mRanges[i] = new Range(posRemain, posRemain + value);
                posRemain += value;
            }
        }
    }
}


