
package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.List;

/**
 * Data object that represents all data for the BarChart.
 *
 * @author Philipp Jahoda
 */
public class BarData extends BarLineScatterCandleBubbleData<IBarDataSet> {

    /**
     * the width of the bars on the x-axis, in values (not pixels)
     */
    private float mBarWidth = 0.85f;

    public BarData() {
        super();
    }

    public BarData(IBarDataSet... dataSets) {
        super(dataSets);
    }

    public BarData(List<IBarDataSet> dataSets) {
        super(dataSets);
    }

    /**
     * Sets the width each bar should have on the x-axis (in values, not pixels).
     * Default 0.85f
     *
     * @param mBarWidth
     */
    public void setBarWidth(float mBarWidth) {
        this.mBarWidth = mBarWidth;
    }

    public float getBarWidth() {
        return mBarWidth;
    }

    /**
     * Groups all BarDataSet objects this data object holds together by modifying the x-value of their entries.
     * Previously set x-values of entries will be overwritten. Leaves space between bars and groups as specified
     * by the parameters.
     * Do not forget to call notifyDataSetChanged() on your BarChart object after calling this method.
     * <p>
     * 分组所有BarDataSet对象，该数据对象通过修改其条目的X值来保持在一起。以前设置的条目X值将被覆盖。在参数指定的条和组之间留出空间。
     * 在调用此方法之后，不要忘记在 BarChart 对象上调用 notifyDataSetChanged()。
     *
     * @param fromX      确定 XAxis 上分组条目开始的位置。
     * @param groupSpace 确定每组条目之间留下的空间。(不是px) 列如：0.8f，条目宽度是1f
     * @param barSpace   确定组中单个条目之间的空间。(不是px) 列如：0.1f，条目宽度是1f
     */
    public void groupBars(float fromX, float groupSpace, float barSpace) {

        int setCount = mDataSets.size();
        if (setCount <= 1) {
            throw new RuntimeException("BarData needs to hold at least 2 BarDataSets to allow grouping.");
        }

        IBarDataSet max = getMaxEntryCountSet();
        int maxEntryCount = max.getEntryCount();

        float groupSpaceWidthHalf = groupSpace / 2f;
        float barSpaceHalf = barSpace / 2f;
        float barWidthHalf = mBarWidth / 2f;

        float interval = getGroupWidth(groupSpace, barSpace);

        for (int i = 0; i < maxEntryCount; i++) {

            float start = fromX;
            fromX += groupSpaceWidthHalf;

            for (IBarDataSet set : mDataSets) {

                fromX += barSpaceHalf;
                fromX += barWidthHalf;

                if (i < set.getEntryCount()) {

                    BarEntry entry = set.getEntryForIndex(i);

                    if (entry != null) {
                        entry.setX(fromX);
                    }
                }

                fromX += barWidthHalf;
                fromX += barSpaceHalf;
            }

            fromX += groupSpaceWidthHalf;
            float end = fromX;
            float innerInterval = end - start;
            float diff = interval - innerInterval;

            // correct rounding errors
            if (diff > 0 || diff < 0) {
                fromX += diff;
            }
        }

        notifyDataChanged();
    }

    /**
     * In case of grouped bars, this method returns the space an individual group of bar needs on the x-axis.
     *
     * @param groupSpace
     * @param barSpace
     * @return
     */
    public float getGroupWidth(float groupSpace, float barSpace) {
        return mDataSets.size() * (mBarWidth + barSpace) + groupSpace;
    }
}
