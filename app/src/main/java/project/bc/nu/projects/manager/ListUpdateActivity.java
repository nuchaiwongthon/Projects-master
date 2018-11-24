package project.bc.nu.projects.manager;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.MyDB;
import project.bc.nu.projects.SQLite.myDBClass;

public class ListUpdateActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_update_veg_dis);

        final MyDB myDb = new MyDB(this);
        final ArrayList<HashMap<String, String>> VegdisList = myDb.SelectAllData();

        // listView1
        ListView lisView2 = (ListView)findViewById(R.id.listView2);

        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(ListUpdateActivity.this, VegdisList, R.layout.activity_column,
                new String[] {"vegdis_id", "vegdis_name"}, new int[] {R.id.colID, R.id.colName});
        lisView2.setAdapter(sAdap);

        lisView2.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {

                // Show on new activity
                Intent newActivity = new Intent(ListUpdateActivity.this,UpdateActivityManager.class);
                newActivity.putExtra("VDID", VegdisList.get(position).get("vegdis_id").toString());
                startActivity(newActivity);
            }
        });


        // btnCancel (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnE1);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                ListUpdateActivity.this.finish();
            }
        });

    }


}