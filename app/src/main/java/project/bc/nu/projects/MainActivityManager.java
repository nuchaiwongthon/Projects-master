package project.bc.nu.projects;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import project.bc.nu.projects.manager.VegDiseaseActivity;
import project.bc.nu.projects.managertimeline.TimelineActivity;
import project.bc.nu.projects.managervegetable.VegetableActivity;

public class MainActivityManager extends AppCompatActivity {

    List<String> arrList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);


        // btnCancel (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnM);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                MainActivityManager.this.finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        final List<String> arrList = new ArrayList<String>();



            final Spinner spin = (Spinner) findViewById(R.id.sp_db);

        String[] plants = new String[]{
                "กรุณาเลือก" ,
                "ข้อมูลผักสวนครัว",
                "ข้อมูลโรคผักสวนครัว",
                "ข้อมูลการดูแลรักษา"
        };


        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plantsList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spin.setAdapter(spinnerArrayAdapter);
        Button  btn1 = (Button) findViewById(R.id.btn_ok) ;
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newActivity = new Intent();
                Toast.makeText(MainActivityManager.this,
                        "" + String.valueOf(spin.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                if (spin.getSelectedItem().toString().equals("ข้อมูลผักสวนครัว")) {
                    startActivity(new Intent(MainActivityManager.this, VegetableActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else if (spin.getSelectedItem().toString().equals("ข้อมูลโรคผักสวนครัว")) {
                    startActivity(new Intent(MainActivityManager.this, VegDiseaseActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else if (spin.getSelectedItem().toString().equals("ข้อมูลการดูแลรักษา")) {
                    startActivity(new Intent(MainActivityManager.this, TimelineActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        });
    }



}
