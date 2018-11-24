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
import android.widget.Toast;

import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.myDBClass;

public class SelectSyndrome5 extends AppCompatActivity {
    private RadioGroup mRadioGroup;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vinit_syndrome);

        final Button AS = (Button) findViewById(R.id.btnAssert21);
        final Button showim1 = (Button) findViewById(R.id.show21);
        final Button showim2 = (Button) findViewById(R.id.show22);
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnV21);
        mRadioGroup = (RadioGroup) findViewById(R.id.menu);

        Intent intent = getIntent();
        String strVegdisID = intent.getStringExtra("strVegdisID");
        //Show Data
        ShowData(strVegdisID);


        showim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog imageDialog = new Dialog(context);
                imageDialog.setContentView(R.layout.custom_dialog_show_image);
                ImageView image = (ImageView) imageDialog.findViewById(R.id.showImage);
                TextView dialogButton = (TextView) imageDialog.findViewById(R.id.dialogButtonOK);
                image.setImageResource(R.drawable.b2);
                TextView ref = (TextView) imageDialog.findViewById(R.id.ref);
                ref.setText("https://www.apsnet.org/edcenter/intropp/lessons/fungi/Oomycetes/Pages/Cucurbits.aspx");

                dialogButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        imageDialog.dismiss();
                    }

                });


                imageDialog.create();
                imageDialog.show();

            }
        });

        showim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog imageDialog = new Dialog(context);
                imageDialog.setContentView(R.layout.custom_dialog_show_image);
                ImageView image = (ImageView) imageDialog.findViewById(R.id.showImage);
                TextView dialogButton = (TextView) imageDialog.findViewById(R.id.dialogButtonOK);
                image.setImageResource(R.drawable.c2);
                TextView ref = (TextView) imageDialog.findViewById(R.id.ref);
                ref.setText("http://www.apsnet.org/publications/apsnetfeatures/Pages/Ralstonia.aspx");

                dialogButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        imageDialog.dismiss();
                    }

                });


                imageDialog.create();
                imageDialog.show();

            }
        });

        final Button main = (Button) findViewById(R.id.btnMain1);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SelectSyndrome5.this, VinitMainActivity.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SelectSyndrome5.this.finish();

            }
        });
        final CheckBox btn1 = (CheckBox) findViewById(R.id.Cbtn21);
        final CheckBox btn2 = (CheckBox) findViewById(R.id.Cbtn22);
        btn1.setTypeface(ResourcesCompat.getFont(context, R.font.kanit_extralight));
        btn2.setTypeface(ResourcesCompat.getFont(context, R.font.kanit_extralight));
        AS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (btn1.isChecked() && btn2.isChecked()) {
                    Toast.makeText(getApplicationContext(), "เลือกอันใดอันหนึ่ง",
                            Toast.LENGTH_SHORT).show();
                    btn1.setChecked(false);
                    btn2.setChecked(false);
                } else if (btn1.isChecked()) {
                    String strVegdisID = "2";
                    Intent newActivity = new Intent(SelectSyndrome5.this, ResultVinitActivity.class);
                    newActivity.putExtra("strVegdisID", strVegdisID);
                    startActivity(newActivity);
                    btn1.setChecked(false);
                } else if (btn2.isChecked()) {
                    String strVegdisID = "3";
                    Intent newActivity = new Intent(SelectSyndrome5.this, ResultVinitActivity.class);
                    newActivity.putExtra("strVegdisID", strVegdisID);
                    startActivity(newActivity);
                    btn2.setChecked(false);
                }

                AS.setSelected(!AS.isSelected());
                if (AS.isSelected()) {

                }

            }


        });
    }

    private void ShowData(String strVegdisID) {
        final myDBClass myDb = new myDBClass(this);
        final CheckBox btn1 = (CheckBox) findViewById(R.id.Cbtn21);
        final CheckBox btn2 = (CheckBox) findViewById(R.id.Cbtn22);
        btn1.setSelected(!btn1.isSelected());
        if (btn1.isSelected()) {
            String arrData[] = myDb.SelectData("2");
            if (arrData != null) {
                btn1.setText(arrData[6]);

            }
        }
        btn2.setSelected(!btn2.isSelected());
        if (btn2.isSelected()) {
            String arrData[] = myDb.SelectData("3");
            if (arrData != null) {
                btn2.setText(arrData[6]);

            }

        }
    }
}