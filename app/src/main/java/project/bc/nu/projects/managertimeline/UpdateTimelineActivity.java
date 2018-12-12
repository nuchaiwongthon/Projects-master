package project.bc.nu.projects.managertimeline;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.myDBClass;
import project.bc.nu.projects.managervegetable.UpdateVegActivityManager;

public class UpdateTimelineActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_update_timeline);
        // Read var from Intent
        Intent intent = getIntent();
        final String timelineID = intent.getStringExtra("TID");

        // Show Data
        ShowData(timelineID);

        // btnSave (Save)
        final ImageView save = (ImageView) findViewById(R.id.SaveBtnEdit);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // If Save Complete
                if (UpdateTimeline(timelineID)) {
                    // Open Form ListUpdate
                    UpdateTimelineActivity.this.finish();
                }
            }
        });

        // btnCancel (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnE);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form ListUpdate
                UpdateTimelineActivity.this.finish();
            }
        });
    }

    public void ShowData(String timelineID) {
        final TextView time_id = (TextView) findViewById(R.id.editTimelineID);
        final EditText veg_name = (EditText) findViewById(R.id.editNameVeg);
        final EditText time_date = (EditText) findViewById(R.id.editDate);
        final EditText time_obj = (EditText) findViewById(R.id.editObj);

        // new Class DB
        final myDBClass myDb = new myDBClass(this);

        // Show Data
        String arrData[] = myDb.SelectTimeline(timelineID);
        if (arrData != null) {
            time_id.setText(arrData[0]);
            veg_name.setText(arrData[2]);
            time_date.setText(arrData[3]);
            time_obj.setText(arrData[4]);
        }

    }

    public boolean UpdateTimeline(String timeline_id) {

        final TextView time_id = (TextView) findViewById(R.id.editTimelineID);
        final EditText veg_name = (EditText) findViewById(R.id.editNameVeg);
        final EditText time_date = (EditText) findViewById(R.id.editDate);
        final EditText time_obj = (EditText) findViewById(R.id.editObj);


        // Dialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();


        // new Class DB
        final myDBClass myDb = new myDBClass(this);

        // Save Data
        long saveStatus = myDb.UpdateTimeline(timeline_id,
                veg_name.getText().toString(),
                time_date.getText().toString(),
                time_obj.getText().toString());
        if (saveStatus <= 0) {
            ad.setMessage("บันทึกไม่สำเร็จ");
            ad.show();
            return false;
        }

        Toast.makeText(UpdateTimelineActivity.this, "แก้ไขเรียบร้อยแล้ว",
                Toast.LENGTH_SHORT).show();

        return true;


    }
}
