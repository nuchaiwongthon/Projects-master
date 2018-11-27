package project.bc.nu.projects.vinit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.myDBClass;

public class SelectTypeVegdisease extends AppCompatActivity {

    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vinit_type1);
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnV1);
        mRadioGroup = (RadioGroup) findViewById(R.id.menu);

        Intent intent = getIntent();
        String strVegdisID = intent.getStringExtra("strVegdisID");
        //Show Data
        ShowData(strVegdisID);


        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SelectTypeVegdisease.this.finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        final RadioButton btn1 = (RadioButton) findViewById(R.id.Rbtn1);
        final RadioButton btn2 = (RadioButton) findViewById(R.id.Rbtn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioGroup.clearCheck();
                // Perform action on click
                String strVegdisID = "3";
                Intent newActivity = new Intent(SelectTypeVegdisease.this, SelectSyndrome.class);
                newActivity.putExtra("strVegdisID", strVegdisID);
                startActivity(newActivity);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioGroup.clearCheck();
                // Perform action on click
                String strVegdisID = "4";
                Intent newActivity = new Intent(SelectTypeVegdisease.this, SelectSyndrome2.class);
                newActivity.putExtra("strVegdisID", strVegdisID);
                startActivity(newActivity);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


            }
        });
    }

    private void ShowData(String strVegdisID) {
        final myDBClass myDb = new myDBClass(this);

        final RadioButton btn1 = (RadioButton) findViewById(R.id.Rbtn1);
        final RadioButton btn2 = (RadioButton) findViewById(R.id.Rbtn2);

        // Show Data
        btn1.setSelected(!btn1.isSelected());
        if (btn1.isSelected()) {
            String arrData[] = myDb.SelectData("3");
            if (arrData != null) {
                btn1.setText(arrData[2]);
            }
        }

        btn2.setSelected(!btn2.isSelected());
        if (btn2.isSelected()) {
            String arrData[] = myDb.SelectData("4");
            if (arrData != null) {
                btn2.setText(arrData[2]);
            }
        }
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
}
