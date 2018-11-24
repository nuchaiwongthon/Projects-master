package project.bc.nu.projects.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.myDBClass;

public  class ResultScanQRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_scan);


        // Read var from Intent
        Intent intent = getIntent();
        String VegID = intent.getStringExtra("VegID");

        // Show Data
        ShowData(VegID);

        final ImageView cancel = (ImageView) findViewById(R.id.backBtnResult);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               ResultScanQRActivity.this.finish();
            }
        });

    }
    public void ShowData(String VegID)
    {
        TextView textNameTH = (TextView) findViewById(R.id.ResultNameTH);
        TextView textNameCM = (TextView) findViewById(R.id.ResultNameCM);
        TextView textNameSC = (TextView) findViewById(R.id.ResultNameSC);
        TextView tVegStructure = (TextView) findViewById(R.id.ResultStructure);
        TextView tVegNutrient = (TextView) findViewById(R.id.ResultNutrient);
        TextView tVegPlant = (TextView) findViewById(R.id.ResultPlant);
        TextView tVegTreatment = (TextView) findViewById(R.id.ResultTreatment);
        ImageView tVegImage = (ImageView)findViewById(R.id.ResultImage);
        // new Class DB
        final myDBClass myDb = new myDBClass(this);

        // Show Data

        String arrData[] = myDb.SelectDataVeg(VegID);
        if(arrData != null)
        {


            textNameTH.setText(arrData[1].toString());
            textNameCM.setText(arrData[2].toString());
            textNameSC.setText(arrData[3].toString());
            tVegStructure.setText(arrData[4].toString());
            tVegNutrient.setText(arrData[5].toString());
            tVegPlant.setText(arrData[6].toString());
            tVegTreatment.setText(arrData[7].toString());
            String strPath = "/mnt/sdcard/project/veg/"+arrData[10].toString();
            Bitmap bm = BitmapFactory.decodeFile(strPath);
            tVegImage.setImageBitmap(bm);
        }

    }

}