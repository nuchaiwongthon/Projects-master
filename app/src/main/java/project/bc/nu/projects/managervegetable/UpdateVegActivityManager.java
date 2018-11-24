package project.bc.nu.projects.managervegetable;

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

public class UpdateVegActivityManager extends AppCompatActivity {

    private int REQUEST_CODE_GALLERY = 100;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_update_veg);

        // Read var from Intent
        Intent intent = getIntent();
        final String VegID = intent.getStringExtra("VID");

        // Show Data
        ShowData(VegID);

        // btnSave (Save)
        final ImageView save = (ImageView) findViewById(R.id.SaveBtnEdit);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // If Save Complete
                if (UpdateDataVeg(VegID)) {
                    // Open Form ListUpdate
                    UpdateVegActivityManager.this.finish();
                }
            }
        });

        // btnCancel (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnE);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form ListUpdate
                UpdateVegActivityManager.this.finish();
            }
        });
    }

    public void ShowData(String VegID) {
        final TextView tVegID = (TextView) findViewById(R.id.editVegID);
        final EditText tVegNameTH = (EditText) findViewById(R.id.editNameTH);
        final EditText tVegNameCM = (EditText) findViewById(R.id.editNameCM);
        final EditText tVegNameSC = (EditText) findViewById(R.id.editNameSC);
        final EditText tVegStructure = (EditText) findViewById(R.id.editStructure);
        final EditText tVegNutrient = (EditText) findViewById(R.id.editNutrient);
        final EditText tVegPlant = (EditText) findViewById(R.id.editPlant);
        final EditText tVegTreatment = (EditText) findViewById(R.id.editTreatment);
        final EditText tVegType = (EditText) findViewById(R.id.editType);
        final EditText tVegNameImage = (EditText) findViewById(R.id.editNameImage);
        final EditText tVegPath = (EditText) findViewById(R.id.editPath);
        // new Class DB
        final myDBClass myDb = new myDBClass(this);

        // Show Data
        String arrData[] = myDb.SelectDataVeg(VegID);
        if (arrData != null) {
            tVegID.setText(arrData[0]);
            tVegNameTH.setText(arrData[1]);
            tVegNameCM.setText(arrData[2]);
            tVegNameSC.setText(arrData[3]);
            tVegStructure.setText(arrData[4]);
            tVegNutrient.setText(arrData[5]);
            tVegPlant.setText(arrData[6]);
            tVegTreatment.setText(arrData[7]);
            tVegType.setText(arrData[8]);
            tVegNameImage.setText(arrData[9]);
            tVegPath.setText(arrData[10]);
        }

    }

    public boolean UpdateDataVeg(String VegID) {

        final EditText tVegNameTH = (EditText) findViewById(R.id.editNameTH);
        final EditText tVegNameCM = (EditText) findViewById(R.id.editNameCM);
        final EditText tVegNameSC = (EditText) findViewById(R.id.editNameSC);
        final EditText tVegStructure = (EditText) findViewById(R.id.editStructure);
        final EditText tVegNutrient = (EditText) findViewById(R.id.editNutrient);
        final EditText tVegPlant = (EditText) findViewById(R.id.editPlant);
        final EditText tVegTreatment = (EditText) findViewById(R.id.editTreatment);
        final EditText tVegType = (EditText) findViewById(R.id.editType);
        final EditText tVegNameImage = (EditText) findViewById(R.id.editNameImage);
        final EditText tVegPath = (EditText) findViewById(R.id.editPath);


        // Dialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();


        // new Class DB
        final myDBClass myDb = new myDBClass(this);

        // Save Data
        long saveStatus = myDb.UpdateDataVeg(VegID,
                tVegNameTH.getText().toString(),
                tVegNameCM.getText().toString(),
                tVegNameSC.getText().toString(),
                tVegStructure.getText().toString(),
                tVegNutrient.getText().toString(),
                tVegPlant.getText().toString(),
                tVegTreatment.getText().toString(),
                tVegType.getText().toString(),
                tVegNameImage.getText().toString(),
                tVegPath.getText().toString());
        if (saveStatus <= 0) {
            ad.setMessage("บันทึกไม่สำเร็จ");
            ad.show();
            return false;
        }

        Toast.makeText(UpdateVegActivityManager.this, "แก้ไขเรียบร้อยแล้ว",
                Toast.LENGTH_SHORT).show();

        return true;

    }
}
