package com.github.mikephil.charting.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Tony Patino on 6/24/16.
 */
public class MPPointF extends ObjectPool.Poolable {

    private static ObjectPool<MPPointF> pool;

    public float x;
    public float y;

    static {
        pool = ObjectPool.create(32, new MPPointF(0, 0));
        pool.setReplenishPercentage(0.5f);
    }

    public MPPointF() {
    }

    public MPPointF(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static MPPointF getInstance(float x, float y) {
        MPPointF result = pool.get();
        result.x = x;
        result.y = y;
        return result;
    }

    public static MPPointF getInstance() {
        return pool.get();
    }

    public static MPPointF getInstance(MPPointF copy) {
        MPPointF result = pool.get();
        result.x = copy.x;
        result.y = copy.y;
        return result;
    }

    public static void recycleInstance(MPPointF instance) {
        pool.recycle(instance);
    }

    public static void recycleInstances(List<MPPointF> instances) {
        pool.recycle(instances);
    }

    public static final Parcelable.Creator<MPPointF> CREATOR = new Parcelable.Creator<MPPointF>() {
        /**
         * 从指定包裹中的数据返回一个新的点
         */
        public MPPointF createFromParcel(Parcel in) {
            MPPointF r = new MPPointF(0, 0);
            r.my_readFromParcel(in);
            return r;
        }

        /**
         * 返回指定大小的矩形数组
         */
        public MPPointF[] newArray(int size) {
            return new MPPointF[size];
        }
    };

    /**
     * 从存储在指定包裹中的数据设置点坐标。调用 writeToParcel() 写一个点到一个包裹。提供支持旧 Android 设备。
     *
     * @param in 包裹读取点坐标
     */
    public void my_readFromParcel(Parcel in) {
        x = in.readFloat();
        y = in.readFloat();
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    @Override
    protected ObjectPool.Poolable instantiate() {
        return new MPPointF(0, 0);
    }
}