package project.bc.nu.projects.vinit;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.myDBClass;

public class SelectSyndrome13 extends AppCompatActivity {
    private RadioGroup mRadioGroup;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vinit_syndrome1);

        final Button AS = (Button) findViewById(R.id.btnAssert31);
        final Button showim = (Button) findViewById(R.id.show31);
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnV31);
        mRadioGroup = (RadioGroup) findViewById(R.id.menu);

        Intent intent = getIntent();
        String strVegdisID = intent.getStringExtra("strVegdisID");
        //Show Data
        ShowData(strVegdisID);
        showim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog imageDialog = new Dialog(context);
                imageDialog.setContentView(R.layout.custom_dialog_show_image);
                ImageView image = (ImageView) imageDialog.findViewById(R.id.showImage);
                TextView dialogButton = (TextView) imageDialog.findViewById(R.id.dialogButtonOK);

                image.setImageResource(R.drawable.d1);
                TextView ref = (TextView) imageDialog.findViewById(R.id.ref);
                ref.setText("https://www.apsnet.org/publications/imageresources/Pages/FI000275.aspx");

                dialogButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        imageDialog.dismiss();
                    }

                });


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageDialog.create();
                }
                imageDialog.show();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SelectSyndrome13.this.finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        final Button main = (Button) findViewById(R.id.btnMain);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectSyndrome13.this, VinitMainActivity.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });
        final CheckBox btn1 = (CheckBox) findViewById(R.id.Cbtn31);

        // turn off API = 19
       // btn1.setTypeface(ResourcesCompat.getFont(context, R.font.kanit_extralight));

        AS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (btn1.isChecked()) {

                    Intent intent = getIntent();
                    String strVegdisID = intent.getStringExtra("strVegdisID");
                    Intent newActivity = new Intent(SelectSyndrome13.this, SelectSyndrome7.class);
                    newActivity.putExtra("strVegdisID", strVegdisID);
                    startActivity(newActivity);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                }
                btn1.setChecked(false);

            }


        });
    }

    private void ShowData(String strVegdisID) {
        final myDBClass myDb = new myDBClass(this);
        final CheckBox btn1 = (CheckBox) findViewById(R.id.Cbtn31);


        // Show Data
        String arrData[] = myDb.SelectData(strVegdisID);
        if (arrData != null) {
            btn1.setText(arrData[5]);

        }
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
}
