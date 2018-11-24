package project.bc.nu.projects.manager;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.MyDB;
import project.bc.nu.projects.SQLite.myDBClass;

public class ListShowActivityManager extends AppCompatActivity {
    String arrData[][];
    final Context context = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_show_veg_dis);

        // get Data from SQLite
        final myDBClass myDb = new myDBClass(this);

/*
        myDb.InsertData("1","Picture 1", "1.png");

*/
        arrData = myDb.SelectAllVegDis();
        /*
         *  [x][0] = GalleryID
         *  [x][1] = Name
         *  [x][2] = Path
         */
        // backBtnS (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnS);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                ListShowActivityManager.this.finish();
            }
        });


        final ListView LView1 = (ListView) findViewById(R.id.listView1);

        LView1.setAdapter(new ImageAdapter(this,arrData));

       // final AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        // OnClick
        LView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                // custom dialog
                final Dialog imageDialog = new Dialog(context);
                imageDialog.setContentView(R.layout.custom_dialog_veg_dis);

                // set the custom dialog components - text, image and button
                TextView textID = (TextView) imageDialog.findViewById(R.id.DetailVegDisID);
                TextView textName = (TextView) imageDialog.findViewById(R.id.DetailVegName);
                TextView textArea = (TextView) imageDialog.findViewById(R.id.DetailVegArea);
                TextView textCause = (TextView) imageDialog.findViewById(R.id.DetailVegCause);
                TextView textSyndrome1 = (TextView) imageDialog.findViewById(R.id.DetailVegSyndrome1);
                TextView textSyndrome2 = (TextView) imageDialog.findViewById(R.id.DetailVegSyndrome2);
                TextView textProtect = (TextView) imageDialog.findViewById(R.id.DetailVegProtect);
                TextView textRemedy = (TextView) imageDialog.findViewById(R.id.DetailVegRemedy);


                textID.setText(arrData[position][0].toString());
                textName.setText(arrData[position][1].toString());
                textArea.setText(arrData[position][2].toString());
                textCause.setText(arrData[position][4].toString());
                textSyndrome1.setText(arrData[position][5].toString());
                textSyndrome2.setText(arrData[position][6].toString());
                textProtect.setText(arrData[position][7].toString());
                textRemedy.setText(arrData[position][8].toString());
                ImageView image = (ImageView) imageDialog.findViewById(R.id.DetailImage);
                String strPath = "/mnt/sdcard/project/vegdis/"+arrData[position][10].toString();
                Bitmap bm = BitmapFactory.decodeFile(strPath);
                image.setImageBitmap(bm);
               TextView dialogButton = (TextView) imageDialog.findViewById(R.id.dialogButtonOK);


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


                imageDialog.show();

            }
        });

    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private String[][] list;

        public ImageAdapter(Context c, String[][] li)
        {
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
                convertView = inflater.inflate(R.layout.activity_column, null);
            }
            TextView textID = (TextView) convertView.findViewById(R.id.colID);
            TextView textName = (TextView) convertView.findViewById(R.id.colName);
            String strPath = "/mnt/sdcard/project/vegdis/"+list[position][10].toString();
            textID.setText(list[position][0].toString());
            textName.setText(list[position][1].toString());
            // Image Resource
            ImageView imageView = (ImageView) convertView.findViewById(R.id.colPath);
            Bitmap bm = BitmapFactory.decodeFile(strPath);
            imageView.setImageBitmap(bm);

            return convertView;

        }
    }

}