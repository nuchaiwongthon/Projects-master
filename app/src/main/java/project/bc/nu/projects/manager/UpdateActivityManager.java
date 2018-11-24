package project.bc.nu.projects.manager;

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

public class UpdateActivityManager extends AppCompatActivity {
    private int REQUEST_CODE_GALLERY = 100;
    ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_update_veg_dis);

        // Read var from Intent
        Intent intent = getIntent();
        final String VegdisID = intent.getStringExtra("VDID");

        // Show Data
        ShowData(VegdisID);

        // btnSave (Save)
        final ImageView save = (ImageView) findViewById(R.id.SaveBtnEdit);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // If Save Complete
                if (UpdateData(VegdisID)) {
                    // Open Form ListUpdate
                    UpdateActivityManager.this.finish();
                }
            }
        });

        // btnCancel (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnE);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form ListUpdate
                UpdateActivityManager.this.finish();
            }
        });

    }

    public void ShowData(String VegdisID) {
        final TextView tVegdisID = (TextView) findViewById(R.id.editID);
        final EditText tVegdisName = (EditText) findViewById(R.id.editName);
        final EditText tVegdisArea = (EditText) findViewById(R.id.editArea);
        final EditText tVegdisType = (EditText) findViewById(R.id.editType);
        final EditText tVegdisCause = (EditText) findViewById(R.id.editCause);
        final EditText tSyndrome1 = (EditText) findViewById(R.id.editSyndrome1);
        final EditText tSyndrome2 = (EditText) findViewById(R.id.editSyndrome2);
        final EditText tProtect = (EditText) findViewById(R.id.editProtect);
        final EditText tRemedy = (EditText) findViewById(R.id.editRemedy);

        // new Class DB
        final myDBClass myDb = new myDBClass(this);

        // Show Data
        String arrData[] = myDb.SelectData(VegdisID);
        if (arrData != null) {
            tVegdisID.setText(arrData[0]);
            tVegdisName.setText(arrData[1]);
            tVegdisArea.setText(arrData[2]);
            tVegdisType.setText(arrData[3]);
            tVegdisCause.setText(arrData[4]);
            tSyndrome1.setText(arrData[5]);
            tSyndrome2.setText(arrData[6]);
            tProtect.setText(arrData[7]);
            tRemedy.setText(arrData[8]);
        }

    }

    public boolean UpdateData(String VegdisID) {

        final EditText tVegdisName = (EditText) findViewById(R.id.editName);
        final EditText tVegdisArea = (EditText) findViewById(R.id.editArea);
        final EditText tVegdisType = (EditText) findViewById(R.id.editType);
        final EditText tVegdisCause = (EditText) findViewById(R.id.editCause);
        final EditText tSyndrome1 = (EditText) findViewById(R.id.editSyndrome1);
        final EditText tSyndrome2 = (EditText) findViewById(R.id.editSyndrome2);
        final EditText tProtect = (EditText) findViewById(R.id.editProtect);
        final EditText tRemedy = (EditText) findViewById(R.id.editRemedy);


        // Dialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();


        // new Class DB
        final myDBClass myDb = new myDBClass(this);

        // Save Data
        long saveStatus = myDb.UpdateData(VegdisID,
                tVegdisName.getText().toString(),
                tVegdisArea.getText().toString(),
                tVegdisType.getText().toString(),
                tVegdisCause.getText().toString(),
                tSyndrome1.getText().toString(),
                tSyndrome2.getText().toString(),
                tProtect.getText().toString(),
                tRemedy.getText().toString());
        if (saveStatus <= 0) {
            ad.setMessage("บันทึกไม่สำเร็จ");
            ad.show();
            return false;
        }

        Toast.makeText(UpdateActivityManager.this, "แก้ไขเรียบร้อยแล้ว",
                Toast.LENGTH_SHORT).show();

        return true;

    }
}