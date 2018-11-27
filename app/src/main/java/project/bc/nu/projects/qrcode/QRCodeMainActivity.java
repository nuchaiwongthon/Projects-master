package project.bc.nu.projects.qrcode;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.myDBClass;

public class QRCodeMainActivity extends AppCompatActivity {

    String arrData[][];
    final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gen_qr);

        // get Data from SQLite
        final myDBClass myDb = new myDBClass(this);

/*
        myDb.InsertData("1","Picture 1", "1.png");

*/
        arrData = myDb.SelectAllVeg();
        /*
         *  [x][0] = GalleryID
         *  [x][1] = Name
         *  [x][2] = Path
         */
        // backBtnS (Cancel)
    /*    final ImageView cancel = (ImageView) findViewById(R.id.backBtnG);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                QRCodeMainActivity.this.finish();
            }
        });*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(QRCodeMainActivity.this,ScanQRActivity.class);
                startActivity(newActivity);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
        final ListView LView1 = (ListView) findViewById(R.id.listViewGen);
        LView1.setAdapter(new QRCodeMainActivity.ImageAdapter(this, arrData));

        // final AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        // OnClick
        LView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // custom dialog
                final Dialog imageDialog = new Dialog(context);
                imageDialog.setContentView(R.layout.custom_dialog_veg_qr);

                // set the custom dialog components - text, image and button
                TextView textNameTH = (TextView) imageDialog.findViewById(R.id.DetailVegNameTH);
                TextView textNameCM = (TextView) imageDialog.findViewById(R.id.DetailNameCM);
                TextView textNameSC = (TextView) imageDialog.findViewById(R.id.DetailNameSC);
                TextView tVegStructure = (TextView) imageDialog.findViewById(R.id.DetailVegStructure);
                TextView tVegNutrient = (TextView) imageDialog.findViewById(R.id.DetailVegNutrient);
                TextView tVegPlant = (TextView) imageDialog.findViewById(R.id.DetailVegPlant);
                TextView tVegTreatment = (TextView) imageDialog.findViewById(R.id.DetailVegTreatment);


                textNameTH.setText(arrData[position][1].toString());
                textNameCM.setText(arrData[position][2].toString());
                textNameSC.setText(arrData[position][3].toString());
                tVegStructure.setText(arrData[position][4].toString());
                tVegNutrient.setText(arrData[position][5].toString());
                tVegPlant.setText(arrData[position][6].toString());
                tVegTreatment.setText(arrData[position][7].toString());
                ImageView image1 = (ImageView) imageDialog.findViewById(R.id.DetailVegImage);
                ImageView image2 = (ImageView) imageDialog.findViewById(R.id.DetailVegImageQR);
                TextView dialogButton = (TextView) imageDialog.findViewById(R.id.dialogButtonOK);


                String strPath1 = "/mnt/sdcard/project/veg/" + arrData[position][10].toString();
                String strPath2 = "/mnt/sdcard/project/qr_veg/" + arrData[position][10].toString();
                Bitmap bm1 = BitmapFactory.decodeFile(strPath1);
                Bitmap bm2 = BitmapFactory.decodeFile(strPath2);
                image1.setImageBitmap(bm1);
                image2.setImageBitmap(bm2);


/*

                View layout = inflater.inflate(R.layout.custom_fullimage_dialog,
                        (ViewGroup) findViewById(R.id.layout_root));
                ImageView image = (ImageView) layout.findViewById(R.id.fullimage);
                TextView textID = (TextView) layout.findViewById(R.id._fullimage_placename);
                String strPath = "/mnt/sdcard/DCIM/vegdis/"+arrData[position][9].toString();
                Bitmap bm = BitmapFactory.decodeFile(strPath);
                int width=400;
                int height=400;
                Bitmap resizedbitmap = Bitmap.createScaledBitmap(bm, width, height, true);
                image.setImageBitmap(resizedbitmap);

                imageDialog.setTitle("รหัส : " + arrData[position][0].toString());
                imageDialog.setMessage("ชื่อโรค : " + arrData[position][1].toString());
                imageDialog.setView(layout);
                */
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

    }
    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private String[][] list;

        public ImageAdapter(Context c, String[][] li) {
            // TODO Auto-generated method stub
            context = c;
            list = li;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return list.length;
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            if (convertView == null) {
                convertView = inflater.inflate(R.layout.activity_column_qr, null);
            }
            //
            TextView textName = (TextView) convertView.findViewById(R.id.colVegName);
            String strPath = "/mnt/sdcard/project/veg/" + list[position][10].toString();

            //textID.setText(list[position][0].toString());
            textName.setText(list[position][1].toString());
            // Image Resource
            ImageView imageView = (ImageView) convertView.findViewById(R.id.colVegPath);
            Bitmap bm = BitmapFactory.decodeFile(strPath);
            imageView.setImageBitmap(bm);

            return convertView;

        }
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }




}