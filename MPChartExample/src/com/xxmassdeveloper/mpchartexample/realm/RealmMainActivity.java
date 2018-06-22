package com.xxmassdeveloper.mpchartexample.realm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xxmassdeveloper.mpchartexample.R;
import com.xxmassdeveloper.mpchartexample.notimportant.ContentItem;
import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;
import com.xxmassdeveloper.mpchartexample.notimportant.MyAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Philipp Jahoda on 07/12/15.
 */
public class RealmMainActivity extends DemoBase implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        setTitle("Realm.io Examples");

        ArrayList<ContentItem> objects = new ArrayList<>();

        objects.add(new ContentItem("Line Chart", "用Realm.io数据库创建曲线图"));
        objects.add(new ContentItem("Bar Chart", "用Realm.io数据库创建柱状图"));
        objects.add(new ContentItem("Horizontal Bar Chart", "用Realm.io数据库创建水平柱状图"));
        objects.add(new ContentItem("Scatter Chart", "用Realm.io数据库创建散点图"));
        objects.add(new ContentItem("Candle Stick Chart", "用Realm.io数据库创建烛台图"));
        objects.add(new ContentItem("Bubble Chart", "用Realm.io数据库创建气泡图"));
        objects.add(new ContentItem("Pie Chart", "用Realm.io数据库创建饼图"));
        objects.add(new ContentItem("Radar Chart", "用Realm.io数据库创建蜘蛛网图"));
        objects.add(new ContentItem("Realm Wiki", "这是关于GiTHub页面上关于RealM.IO的Wiki条目的代码"));

        MyAdapter adapter = new MyAdapter(this, objects);

        ListView lv = findViewById(R.id.listView1);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);

        Realm.init(this);

        // Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfig);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int pos, long arg3) {

        Intent i;

        switch (pos) {
            case 0:
                i = new Intent(this, RealmDatabaseActivityLine.class);
                startActivity(i);
                break;
            case 1:
                i = new Intent(this, RealmDatabaseActivityBar.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(this, RealmDatabaseActivityHorizontalBar.class);
                startActivity(i);
                break;
            case 3:
                i = new Intent(this, RealmDatabaseActivityScatter.class);
                startActivity(i);
                break;
            case 4:
                i = new Intent(this, RealmDatabaseActivityCandle.class);
                startActivity(i);
                break;
            case 5:
                i = new Intent(this, RealmDatabaseActivityBubble.class);
                startActivity(i);
                break;
            case 6:
                i = new Intent(this, RealmDatabaseActivityPie.class);
                startActivity(i);
                break;
            case 7:
                i = new Intent(this, RealmDatabaseActivityRadar.class);
                startActivity(i);
                break;
            case 8:
                i = new Intent(this, RealmWikiExample.class);
                startActivity(i);
                break;
        }

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.realm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://realm.io"));
        startActivity(i);

        return super.onOptionsItemSelected(item);
    }
}
