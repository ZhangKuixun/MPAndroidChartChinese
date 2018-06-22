
package com.xxmassdeveloper.mpchartexample.notimportant;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.github.mikephil.charting.utils.Utils;
import com.xxmassdeveloper.mpchartexample.AnotherBarActivity;
import com.xxmassdeveloper.mpchartexample.BarChartActivity;
import com.xxmassdeveloper.mpchartexample.BarChartActivityMultiDataset;
import com.xxmassdeveloper.mpchartexample.BarChartActivitySinus;
import com.xxmassdeveloper.mpchartexample.BarChartPositiveNegative;
import com.xxmassdeveloper.mpchartexample.BubbleChartActivity;
import com.xxmassdeveloper.mpchartexample.CandleStickChartActivity;
import com.xxmassdeveloper.mpchartexample.CombinedChartActivity;
import com.xxmassdeveloper.mpchartexample.CubicLineChartActivity;
import com.xxmassdeveloper.mpchartexample.DynamicalAddingActivity;
import com.xxmassdeveloper.mpchartexample.FilledLineActivity;
import com.xxmassdeveloper.mpchartexample.HalfPieChartActivity;
import com.xxmassdeveloper.mpchartexample.HorizontalBarChartActivity;
import com.xxmassdeveloper.mpchartexample.InvertedLineChartActivity;
import com.xxmassdeveloper.mpchartexample.LineChartActivity1;
import com.xxmassdeveloper.mpchartexample.LineChartActivity2;
import com.xxmassdeveloper.mpchartexample.LineChartActivityColored;
import com.xxmassdeveloper.mpchartexample.LineChartTime;
import com.xxmassdeveloper.mpchartexample.ListViewBarChartActivity;
import com.xxmassdeveloper.mpchartexample.ListViewMultiChartActivity;
import com.xxmassdeveloper.mpchartexample.MultiLineChartActivity;
import com.xxmassdeveloper.mpchartexample.PerformanceLineChart;
import com.xxmassdeveloper.mpchartexample.PieChartActivity;
import com.xxmassdeveloper.mpchartexample.PiePolylineChartActivity;
import com.xxmassdeveloper.mpchartexample.R;
import com.xxmassdeveloper.mpchartexample.RadarChartActivity;
import com.xxmassdeveloper.mpchartexample.RealtimeLineChartActivity;
import com.xxmassdeveloper.mpchartexample.ScatterChartActivity;
import com.xxmassdeveloper.mpchartexample.ScrollViewActivity;
import com.xxmassdeveloper.mpchartexample.StackedBarActivity;
import com.xxmassdeveloper.mpchartexample.StackedBarActivityNegative;
import com.xxmassdeveloper.mpchartexample.fragments.SimpleChartDemo;
import com.xxmassdeveloper.mpchartexample.realm.RealmMainActivity;

import java.util.ArrayList;

public class MainActivity extends Activity implements OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        setTitle("MPAndroidChart Example");

        // initialize the utilities
        Utils.init(this);

        ArrayList<ContentItem> objects = new ArrayList<>();

        objects.add(new ContentItem("Line Chart", "直线图的简单演示"));
        objects.add(new ContentItem("Line Chart (双重轴)", "双y轴直线图的演示"));
        objects.add(new ContentItem("Bar Chart", "简单的条形图演示"));
        objects.add(new ContentItem("Horizontal Bar Chart", "简单的水平条形图的演示"));
        objects.add(new ContentItem("Combined Chart", "演示如何创建组合图表（组合柱状和线）"));
        objects.add(new ContentItem("Pie Chart", "简单饼状图演示"));
        objects.add(new ContentItem("Pie Chart with value lines", "简单的饼图，有百分比注释"));
        objects.add(new ContentItem("Scatter Chart", "散点图的简单演示"));
        objects.add(new ContentItem("Bubble Chart", "气泡图的简单演示"));
        objects.add(new ContentItem("Stacked Bar Chart", "垂直堆叠柱状图的简单演示"));
        objects.add(new ContentItem("Stacked Bar Chart Negative", "横向有负和正值的堆叠柱状图的简单演示"));
        objects.add(new ContentItem("Another Bar Chart", "实现一个只显示底部值的条形图"));
        objects.add(new ContentItem("Multiple Lines Chart", "不同颜色数据集对象折线图，每个数据集一个颜色"));
        objects.add(new ContentItem("Multiple Bars Chart", " 具有多个数据集对象的条形图，每个数据集都有一个多重颜色"));
        objects.add(new ContentItem("Charts in ViewPager Fragments", "演示ViewPager里的图表。在这个例子中，重点是图表的设计和外观"));
        objects.add(new ContentItem("BarChart inside ListView", "在listView项中使用柱状图"));
        objects.add(new ContentItem("Multiple charts inside ListView", "在listView创建不同的图表"));
        objects.add(new ContentItem("Inverted Line Chart", "反转y轴，创建曲线图"));
        objects.add(new ContentItem("Candle Stick Chart", "演示烛台图的用法"));
        objects.add(new ContentItem("Cubic Line Chart", "演示三次曲线图"));
        objects.add(new ContentItem("Radar Chart", "演示使用蛛网状图"));
        objects.add(new ContentItem("Colored Line Chart", "显示不同背景和线条颜色的线条图"));
        objects.add(new ContentItem("Realtime Chart", " 这张图表是实时提供新数据。它还限制了X轴上的view"));
        objects.add(new ContentItem("Dynamical data adding", "演示了条目和数据集（实时图）的动态添加"));
        objects.add(new ContentItem("Performance Line Chart", "平滑地渲染30个对象"));
        objects.add(new ContentItem("Sinus Bar Chart", "用8个值绘制弯曲处的条形图"));
        objects.add(new ContentItem("Chart in ScrollView", "在ScrollView中使用图表"));
        objects.add(new ContentItem("BarChart positive / negative", "创建具有不同颜色的正负值的柱状图"));
        objects.add(new ContentItem("Realm.io Database", "使用了RealM.IO库"));
        objects.add(new ContentItem("Time Chart", "时间图表的简单演示。这张图表每小时从一毫秒产生一个线"));
        objects.add(new ContentItem("Filled LineChart", "填充两个LineDataSet之间的区域。 "));
        objects.add(new ContentItem("Half PieChart", "创建一个180度的饼图"));

        MyAdapter adapter = new MyAdapter(this, objects);

        ListView lv = findViewById(R.id.listView1);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int pos, long arg3) {

        Intent i;

        switch (pos) {
            case 0:
                i = new Intent(this, LineChartActivity1.class);
                startActivity(i);
                break;
            case 1:
                i = new Intent(this, LineChartActivity2.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(this, BarChartActivity.class);
                startActivity(i);
                break;
            case 3:
                i = new Intent(this, HorizontalBarChartActivity.class);
                startActivity(i);
                break;
            case 4:
                i = new Intent(this, CombinedChartActivity.class);
                startActivity(i);
                break;
            case 5:
                i = new Intent(this, PieChartActivity.class);
                startActivity(i);
                break;
            case 6:
                i = new Intent(this, PiePolylineChartActivity.class);
                startActivity(i);
                break;
            case 7:
                i = new Intent(this, ScatterChartActivity.class);
                startActivity(i);
                break;
            case 8:
                i = new Intent(this, BubbleChartActivity.class);
                startActivity(i);
                break;
            case 9:
                i = new Intent(this, StackedBarActivity.class);
                startActivity(i);
                break;
            case 10:
                i = new Intent(this, StackedBarActivityNegative.class);
                startActivity(i);
                break;
            case 11:
                i = new Intent(this, AnotherBarActivity.class);
                startActivity(i);
                break;
            case 12:
                i = new Intent(this, MultiLineChartActivity.class);
                startActivity(i);
                break;
            case 13:
                i = new Intent(this, BarChartActivityMultiDataset.class);
                startActivity(i);
                break;
            case 14:
                i = new Intent(this, SimpleChartDemo.class);
                startActivity(i);
                break;
            case 15:
                i = new Intent(this, ListViewBarChartActivity.class);
                startActivity(i);
                break;
            case 16:
                i = new Intent(this, ListViewMultiChartActivity.class);
                startActivity(i);
                break;
            case 17:
                i = new Intent(this, InvertedLineChartActivity.class);
                startActivity(i);
                break;
            case 18:
                i = new Intent(this, CandleStickChartActivity.class);
                startActivity(i);
                break;
            case 19:
                i = new Intent(this, CubicLineChartActivity.class);
                startActivity(i);
                break;
            case 20:
                i = new Intent(this, RadarChartActivity.class);
                startActivity(i);
                break;
            case 21:
                i = new Intent(this, LineChartActivityColored.class);
                startActivity(i);
                break;
            case 22:
                i = new Intent(this, RealtimeLineChartActivity.class);
                startActivity(i);
                break;
            case 23:
                i = new Intent(this, DynamicalAddingActivity.class);
                startActivity(i);
                break;
            case 24:
                i = new Intent(this, PerformanceLineChart.class);
                startActivity(i);
                break;
            case 25:
                i = new Intent(this, BarChartActivitySinus.class);
                startActivity(i);
                break;
            case 26:
                i = new Intent(this, ScrollViewActivity.class);
                startActivity(i);
                break;
            case 27:
                i = new Intent(this, BarChartPositiveNegative.class);
                startActivity(i);
                break;
            case 28:
                i = new Intent(this, RealmMainActivity.class);
                startActivity(i);
                break;
            case 29:
                i = new Intent(this, LineChartTime.class);
                startActivity(i);
                break;
            case 30:
                i = new Intent(this, FilledLineActivity.class);
                startActivity(i);
                break;
            case 31:
                i = new Intent(this, HalfPieChartActivity.class);
                startActivity(i);
                break;

        }

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i = null;

        switch (item.getItemId()) {
            case R.id.viewGithub:
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart"));
                startActivity(i);
                break;
            case R.id.report:
                i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "philjay.librarysup@gmail.com", null));
                i.putExtra(Intent.EXTRA_SUBJECT, "MPAndroidChart Issue");
                i.putExtra(Intent.EXTRA_TEXT, "Your error report here...");
                startActivity(Intent.createChooser(i, "Report Problem"));
                break;
            case R.id.blog:
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.xxmassdeveloper.com"));
                startActivity(i);
                break;
            case R.id.website:
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://at.linkedin.com/in/philippjahoda"));
                startActivity(i);
                break;
        }

        return true;
    }
}
