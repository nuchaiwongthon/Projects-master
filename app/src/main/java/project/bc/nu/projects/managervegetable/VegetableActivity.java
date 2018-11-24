package project.bc.nu.projects.managervegetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.myDBClass;

public class VegetableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_veg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final myDBClass myDb = new myDBClass(this);
        // btnCancel (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnM);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                VegetableActivity.this.finish();
            }
        });
        // Button1 (Add)
        final Button btn1 = (Button) findViewById(R.id.btnAdd);
        // Perform action on click
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Form Add
                Intent newActivity = new Intent(VegetableActivity.this, AddVegActivityManager.class);
                startActivity(newActivity);

            }
        });

        // Button2 (Show)
        final Button btn2 = (Button) findViewById(R.id.btnShow);
        // Perform action on click
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Form Show
                Intent newActivity = new Intent(VegetableActivity.this, ListShowVegActivityManager.class);
                startActivity(newActivity);

            }
        });

        // Button3 (Update)
        final Button btn3 = (Button) findViewById(R.id.btnEdit);
        // Perform action on click
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Form ListUpdate
                Intent newActivity = new Intent(VegetableActivity.this,ListUpdateVegActivityManager.class);
                startActivity(newActivity);

            }
        });
        // Button4 (Delete)
        final Button btn4 = (Button) findViewById(R.id.btnDelete);
        // Perform action on click
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Form ListDelete
                Intent newActivity = new Intent(VegetableActivity.this,ListDeleteVegActivityManager.class);
                startActivity(newActivity);

            }
        });
    }
}
