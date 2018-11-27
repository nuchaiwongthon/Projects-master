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

public class SelectTypeVegdisease4 extends AppCompatActivity {

    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vinit_type3);
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnV3);
        mRadioGroup = (RadioGroup) findViewById(R.id.menu);

        Intent intent = getIntent();
        String strVegdisID = intent.getStringExtra("strVegdisID");
        //Show Data
        ShowData(strVegdisID);


        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SelectTypeVegdisease4.this.finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        final RadioButton btn1 = (RadioButton) findViewById(R.id.Rbtn31);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioGroup.clearCheck();
                // Perform action on click
                String strVegdisID = "2";
                Intent newActivity = new Intent(SelectTypeVegdisease4.this, SelectSyndrome4.class);
                newActivity.putExtra("strVegdisID", strVegdisID);
                startActivity(newActivity);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

    }

    private void ShowData(String strVegdisID) {
        final myDBClass myDb = new myDBClass(this);

        final RadioButton btn1 = (RadioButton) findViewById(R.id.Rbtn31);


        // Show Data
        btn1.setSelected(!btn1.isSelected());
        if (btn1.isSelected()) {
            String arrData[] = myDb.SelectData("2");
            if (arrData != null) {
                btn1.setText(arrData[2]);
            }
        }


    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
}
