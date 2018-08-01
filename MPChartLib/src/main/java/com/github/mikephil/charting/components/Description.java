package com.github.mikephil.charting.components;

import android.graphics.Paint;

import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

/**
 * Created by Philipp Jahoda on 17/09/16.
 * 描述
 */
public class Description extends ComponentBase {

    /**
     * 描述使用的文本
     */
    private String text = "Description Label";

    /**
     * 描述文本的自定义位置
     */
    private MPPointF mPosition;

    /**
     * 描述文本的对齐方式
     */
    private Paint.Align mTextAlign = Paint.Align.RIGHT;

    public Description() {
        super();

        // default size
        mTextSize = Utils.convertDpToPixel(8f);
    }

    /**
     * 设置此文本为描述。千万不要将它设置为 null，因为这将在使用 Android 画布绘制时导致 NullPosiple 异常。
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 返回描述文本
     */
    public String getText() {
        return text;
    }

    /**
     * 自定义设置在屏幕上以像素为单位的描述文本位置。
     *
     * @param x - X坐标
     * @param y - Y坐标
     */
    public void setPosition(float x, float y) {
        if (mPosition == null) {
            mPosition = MPPointF.getInstance(x, y);
        } else {
            mPosition.x = x;
            mPosition.y = y;
        }
    }

    /**
     * 返回自定义的描述文本位置，如果没有设置则返回NULL。
     */
    public MPPointF getPosition() {
        return mPosition;
    }

    /**
     * 设置描述文本的文本对齐方式 {@link Paint.Align}。默认 RIGHT
     */
    public void setTextAlign(Paint.Align align) {
        this.mTextAlign = align;
    }

    /**
     * 返回描述的文本对齐方式 {@link Paint.Align}
     */
    public Paint.Align getTextAlign() {
        return mTextAlign;
    }
}
