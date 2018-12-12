package project.bc.nu.projects.managertimeline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.myDBClass;
public class ListUpdateTimelineActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_update_timeline);

        final myDBClass myDb = new myDBClass(this);
        final ArrayList<HashMap<String, String>> timeline = myDb.SelectAllDataTimeline();

        // listView1
        ListView lisView2 = (ListView) findViewById(R.id.listView2);

        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(ListUpdateTimelineActivity.this, timeline, R.layout.activity_column_timeline,
                new String[]{"veg_name", "timeline_obj"}, new int[]{R.id.colName, R.id.colObj});
        lisView2.setAdapter(sAdap);

        lisView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {

                // Show on new activity
                Intent newActivity = new Intent(ListUpdateTimelineActivity.this, UpdateTimelineActivity.class);
                newActivity.putExtra("TID", timeline.get(position).get("timeline_id").toString());
                startActivity(newActivity);
            }
        });


        // btnCancel (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnE1);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                ListUpdateTimelineActivity.this.finish();
            }
        });

    }
}
