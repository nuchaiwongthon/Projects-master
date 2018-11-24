package project.bc.nu.projects.vinit;

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

public class ResultVinitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_vinit);

        // Read var from Intent
        Intent intent = getIntent();
        String strVegdisID = intent.getStringExtra("strVegdisID");

        // Show Data
        ShowData(strVegdisID);



        final ImageView cancel = (ImageView) findViewById(R.id.backBtnResult);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ResultVinitActivity.this.finish();
            }
        });
    }
        public void ShowData(String strVegdisID)
        {

            final TextView tVegName = (TextView) findViewById(R.id.ResultName);
            final TextView tVegArea = (TextView) findViewById(R.id.ResultArea);
            final TextView tVegCause = (TextView) findViewById(R.id.ResultCause);
            final TextView tProtect = (TextView) findViewById(R.id.ResultProtect);
            final TextView tRemedy = (TextView) findViewById(R.id.ResultRemedy);

            final ImageView tVegImage = (ImageView) findViewById(R.id.ResultVegImage);
            // new Class DB
            final myDBClass myDb = new myDBClass(this);

            // Show Data

            String arrData[] = myDb.SelectData(strVegdisID);
            if(arrData != null)
            {
                tVegName.setText(arrData[1].toString());
                tVegArea.setText(arrData[2].toString());
                tVegCause.setText(arrData[4].toString());
                tProtect.setText(arrData[7].toString());
                tRemedy.setText(arrData[8].toString());
                String strPath = "/mnt/sdcard/project/vegdis/"+arrData[10].toString();
                Bitmap bm = BitmapFactory.decodeFile(strPath);
                tVegImage.setImageBitmap(bm);
            }

        }

    }






