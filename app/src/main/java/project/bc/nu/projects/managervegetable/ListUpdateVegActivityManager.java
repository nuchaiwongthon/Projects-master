package project.bc.nu.projects.managervegetable;

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

public class ListUpdateVegActivityManager extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_update_veg);

        final myDBClass myDb = new myDBClass(this);
        final ArrayList<HashMap<String, String>> VegdisList = myDb.SelectAllDataVeg();

        // listView1
        ListView lisView2 = (ListView)findViewById(R.id.listView2);

        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(ListUpdateVegActivityManager.this, VegdisList, R.layout.activity_column_veg,
                new String[] {"veg_id", "veg_name_th"}, new int[] {R.id.colVegID, R.id.colVegName});
        lisView2.setAdapter(sAdap);

        lisView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {

                // Show on new activity
                Intent newActivity = new Intent(ListUpdateVegActivityManager.this,UpdateVegActivityManager.class);
                newActivity.putExtra("VID", VegdisList.get(position).get("veg_id").toString());
                startActivity(newActivity);
            }
        });


        // btnCancel (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnE1);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                ListUpdateVegActivityManager.this.finish();
            }
        });

    }
}
