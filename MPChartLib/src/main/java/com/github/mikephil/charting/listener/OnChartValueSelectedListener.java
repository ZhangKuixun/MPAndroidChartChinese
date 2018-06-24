package com.github.mikephil.charting.listener;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

/**
 * 通过触摸手势选择图表中的值时的回调侦听器。
 *
 * @author Philipp Jahoda
 */
public interface OnChartValueSelectedListener {

    /**
     * 当在图表中选择一个值时调用。
     *
     * @param e 选定的条目
     * @param h 包含突出显示位置的信息的相应突出显示对象，如dataSetIndex，…
     */
    void onValueSelected(Entry e, Highlight h);

    /**
     * 在没有选择或“未选中”时调用。
     */
    void onNothingSelected();
}
