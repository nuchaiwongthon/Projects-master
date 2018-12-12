package project.bc.nu.projects.managertimeline;

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
import project.bc.nu.projects.SQLite.myDBClass;
import project.bc.nu.projects.manager.ListShowActivityManager;

public class ListShowActivityTimeline extends AppCompatActivity{
    String arrData[][];
    final Context context = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_show_timeline);

        // get Data from SQLite
        final myDBClass myDb = new myDBClass(this);

        arrData = myDb.SelectAllTimeline();
        // backBtnS (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnS);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                ListShowActivityTimeline.this.finish();
            }
        });


        final ListView LView1 = (ListView) findViewById(R.id.listView1);

        LView1.setAdapter(new ListShowActivityTimeline.ImageAdapter(this,arrData));

        // final AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        // OnClick
        LView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                // custom dialog
                final Dialog imageDialog = new Dialog(context);
                imageDialog.setContentView(R.layout.custom_dialog_timeline);

                // set the custom dialog components - text, image and button
                TextView textID = (TextView) imageDialog.findViewById(R.id.timelineID);
                TextView textName = (TextView) imageDialog.findViewById(R.id.timeName);
                TextView textDate = (TextView) imageDialog.findViewById(R.id.timeDate);
                TextView textObj = (TextView) imageDialog.findViewById(R.id.timeObj);


                textID.setText(arrData[position][0]);
                textName.setText(arrData[position][2]);
                textDate.setText(arrData[position][3]);
                textObj.setText(arrData[position][4]);
                TextView dialogButton = (TextView) imageDialog.findViewById(R.id.dialogButtonOK);


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
                convertView = inflater.inflate(R.layout.activity_column_timeline, null);
            }
            TextView textName = (TextView) convertView.findViewById(R.id.colName);
            TextView textObj = (TextView) convertView.findViewById(R.id.colObj);
            textName.setText(list[position][2]);
            textObj.setText(list[position][4]);

            return convertView;

        }
    }
}
