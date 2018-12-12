package project.bc.nu.projects.managertimeline;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.MyDB;
import project.bc.nu.projects.SQLite.myDBClass;
import project.bc.nu.projects.managervegetable.AddVegActivityManager;

public class AddTimeline extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_timeline);
        final myDBClass db = new myDBClass(this);
/*
        db.InsertDataTimeline("1", "กะหล่ำดอก", "6", "ใส่ปุ๋ยครั่งที่ 1 สูตร 46-0-0 อัตรา 30 กก./ไร่");
        db.InsertDataTimeline("1", "กะหล่ำดอก", "19", "ใส่ปุ๋ยครั่งที่ 2 สูตร 15-15-15 อัตรา 50 กก./ไร่");
        db.InsertDataTimeline("1", "กะหล่ำดอก", "39", "ใส่ปุ๋ยครั่งที่ 3 สูตร 13-13-21 หรือ 14-14-21 อัตรา 50 กก./ไร่");
        db.InsertDataTimeline("1", "กะหล่ำดอก", "49", "เริ่มเก็บเกี่ยว กะหล่ำดอก มีหัวที่ห่อแน่นและมีขนาดพอเหมาะ");

        db.InsertDataTimeline("7", "บวบเหลี่ยม", "6", "ใส่ปุ๋ยครั่งที่ 1 " + "สูตร 46-0-0 อัตรา 30 กก./ไร่");
        db.InsertDataTimeline("7", "บวบเหลี่ยม", "19", "ใส่ปุ๋ยครั่งที่ 2 " + "สูตร 15-15-15 หรือ 16-16-16 อัตรา 40 กก./ไร่");
        db.InsertDataTimeline("7", "บวบเหลี่ยม", "29", "ใส่ปุ๋ยครั่งที่ 3 " + "สูตร 13-13-21 อัตรา 30-50 กก./ไร่");
        db.InsertDataTimeline("7", "บวบเหลี่ยม", "39", "เริ่มเก็บเกี่ยว บวบเหลี่ยม เมื่อขนาดพอเหมาะไม่อ่อนหรือแก่เกินไป");

        db.InsertDataTimeline("12", "มะเขือเทศ", "6", "ใส่ปุ๋ยครั่งที่ 1 " + "สูตร 13-13-21 อัตรา 100 กก./ไร่");
        db.InsertDataTimeline("12", "มะเขือเทศ", "14", "ใส่ปุ๋ยครั่งที่ 2 " + "สูตร 13-13-21");
        db.InsertDataTimeline("12", "มะเขือเทศ", "19", "ใส่ปุ๋ยครั่งที่ 3 " + "สูตร 13-13-21");
        db.InsertDataTimeline("12", "มะเขือเทศ", "69", "เริ่มเก็บเกี่ยว มะเขือเทศ ผลเริ่มมีผลสีแดงหรือสีส้มแก่");

        db.InsertDataTimeline("20", "คะน้าฮ้องกง", "6", "ใส่ปุ๋ยครั่งที่ 1 " + "สูตร 46–0–0 หรือ 21–0–0 อัตรา 120 กก./ไร่");
        db.InsertDataTimeline("20", "คะน้าฮ้องกง", "13", "ใส่ปุ๋ยครั่งที่ 2 " + "สูตร 21–0–0 ผสมปุ๋ยสูตร 15–15–15 อัตรา 2:1 120 กก./ไร่");
        db.InsertDataTimeline("20", "คะน้าฮ้องกง", "34", "เริ่มเก็บเกี่ยว คะน้าฮ้องกง เมื่อเริ่มมีดอก");

        db.InsertDataTimeline("38","กระเทียม", "9", "ใส่ปุ๋ยสูตร 46 – 0 – 0 หรือ 21 – 0 – 0 อัตรา 25 – 30 กิโลกรัมต่อไร่");
        db.InsertDataTimeline("38","กระเทียม", "89", "เริ่มเก็บเกี่ยว กระเทียม เมื่อใบเปลื่ยนเป็นสีเหลืองแห้งเหี่ยวลง");

*/
        // btnSave (Save)
        final ImageView save = (ImageView) findViewById(R.id.btnSave);


        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // If Save Complete
                if (SaveTimeline()) {
                    // Open Form Main
                    AddTimeline.this.finish();
                }
            }
        });


        // btnCancel (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnA);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                AddTimeline.this.finish();
            }
        });
    }

    public boolean SaveTimeline() {

        EditText veg_id = (EditText) findViewById(R.id.addVegID);
        EditText veg_name = (EditText) findViewById(R.id.addNameVeg);
        EditText timeline_date = (EditText) findViewById(R.id.addDate);
        EditText timeline_obj = (EditText) findViewById(R.id.addObj);
        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        AlertDialog ad = adb.create();
        // new Class DB
        final myDBClass myDb = new myDBClass(this);


        // Save Data
        long saveStatus = myDb.InsertDataTimeline(
                veg_id.getText().toString(),
                veg_name.getText().toString(),
                timeline_date.getText().toString(),
                timeline_obj.getText().toString());
        if (saveStatus <= 0) {
            ad.setMessage("ไม่สำเร็จ");
            ad.show();
            return false;
        }
        myDb.close();
        Toast.makeText(AddTimeline.this, "เพื่มข้อมูลเรียบร้อยแล้ว",
                Toast.LENGTH_SHORT).show();


        return true;

    }
}
