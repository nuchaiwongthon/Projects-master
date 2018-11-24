package project.bc.nu.projects.manager;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.myDBClass;

public class ListDeleteActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> VegdisList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_delete_veg_dis);

        // Call Show List All Data
        ShowListData();

        // btnCancel (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnD);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                ListDeleteActivity.this.finish();
            }
        });

    }

    // Show List data
    public void ShowListData()
    {
        myDBClass myDb = new myDBClass(this);
       VegdisList = myDb.SelectAllData();

        // listView3
        ListView lisView3 = (ListView)findViewById(R.id.listView3);

        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(ListDeleteActivity.this, VegdisList, R.layout.activity_column,
                new String[] {"vegdis_id", "vegdis_name"}, new int[] {R.id.colID, R.id.colName});
        lisView3.setAdapter(sAdap);
        registerForContextMenu(lisView3);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        //if (v.getId()==R.id.list) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle("" + VegdisList.get(info.position).get("vegdis_name").toString());
        String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
        for (int i = 0; i<menuItems.length; i++) {
            menu.add(Menu.NONE, i, i, menuItems[i]);
        }
        //}
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
        String CmdName = menuItems[menuItemIndex];
        String VDID = VegdisList.get(info.position).get("vegdis_id").toString();

        // Check Event Command
       if ("ลบ".equals(CmdName)) {

            myDBClass myDb = new myDBClass(this);

            long flg = myDb.DeleteData(VDID);
            if(flg > 0)
            {
                Toast.makeText(ListDeleteActivity.this,"ลบข้อมูลเรียบร้อย",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(ListDeleteActivity.this,"ลบข้อมูลไม่สำเร็จ",
                        Toast.LENGTH_SHORT).show();
            }

            // Call Show Data again
            ShowListData();
        }

        return true;
    }


}
