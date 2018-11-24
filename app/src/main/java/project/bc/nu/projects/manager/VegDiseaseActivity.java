package project.bc.nu.projects.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;

import project.bc.nu.projects.R;

public class VegDiseaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_veg_dis);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // btnCancel (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnM);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
               VegDiseaseActivity.this.finish();
            }
        });

        // Button1 (Add)
        final Button btn1 = (Button) findViewById(R.id.btnAdd);
        // Perform action on click
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Form Add
                Intent newActivity = new Intent(VegDiseaseActivity.this, AddActivityManager.class);
                startActivity(newActivity);

            }
        });

        // Button2 (Show)
        final Button btn2 = (Button) findViewById(R.id.btnShow);
        // Perform action on click
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Form Show
                Intent newActivity = new Intent(VegDiseaseActivity.this, ListShowActivityManager.class);
                startActivity(newActivity);

            }
        });

        // Button3 (Update)
        final Button btn3 = (Button) findViewById(R.id.btnEdit);
        // Perform action on click
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Form ListUpdate
                Intent newActivity = new Intent(VegDiseaseActivity.this,ListUpdateActivity.class);
                startActivity(newActivity);

            }
        });
        // Button4 (Delete)
        final Button btn4 = (Button) findViewById(R.id.btnDelete);
        // Perform action on click
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Form ListDelete
                Intent newActivity = new Intent(VegDiseaseActivity.this,ListDeleteActivity.class);
                startActivity(newActivity);

            }
        });
    }
}
