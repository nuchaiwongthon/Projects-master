package project.bc.nu.projects.vinit;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

public class SelectSyndrome9 extends AppCompatActivity {

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

                image.setImageResource(R.drawable.e2);
                TextView ref = (TextView) imageDialog.findViewById(R.id.ref);
                ref.setText("https://www.apsnet.org/publications/apsnetfeatures/Pages/FalseRootKnot.aspx");

                dialogButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        imageDialog.dismiss();
                    }

                });


                imageDialog.create();
                imageDialog.show();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SelectSyndrome9.this.finish();

            }
        });
        final Button main = (Button) findViewById(R.id.btnMain);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SelectSyndrome9.this, VinitMainActivity.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        final CheckBox btn1 = (CheckBox) findViewById(R.id.Cbtn31);
        btn1.setTypeface(ResourcesCompat.getFont(context, R.font.kanit_extralight));

        AS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (btn1.isChecked()) {

                    Intent intent = getIntent();
                    String strVegdisID = intent.getStringExtra("strVegdisID");
                    Intent newActivity = new Intent(SelectSyndrome9.this, ResultVinitActivity.class);
                    newActivity.putExtra("strVegdisID", strVegdisID);
                    startActivity(newActivity);
                    btn1.setChecked(false);

                }


            }


        });
    }

    private void ShowData(String strVegdisID) {
        final myDBClass myDb = new myDBClass(this);
        final CheckBox btn1 = (CheckBox) findViewById(R.id.Cbtn31);


        // Show Data
        String arrData[] = myDb.SelectData(strVegdisID);
        if (arrData != null) {
            btn1.setText(arrData[6]);

        }
    }
}
