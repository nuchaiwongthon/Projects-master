package project.bc.nu.projects.manager;

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

public class AddActivityManager extends AppCompatActivity {
    private int REQUEST_CODE_GALLERY = 100;
    public static myDBClass db;
    String arrData[][];


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_veg_dis);
        final myDBClass db = new myDBClass(this);
/*
        db.InsertData("โรคแอนแทรกโนส (กุ้งแห้ง)","ผล", "กลุ่มพริก",
                "เกิดจาก เชื้อรา Colletotrichum spp.","มีจุดฉ่ำน้ำ เล็กๆมีลักษณะแผลบุ๋มลึกลงไปเล็กน้อย","มีะแผลขยายออกไปในลักษณะวงรีหรือวงกลมซ้อนกันเป็นชั้น ๆ","1. ไม่ใช้เมล็ดจากผลพริกที่เป็นโรคทำพันธุ์ เนื่องจากเชื้อโรคสามารถติดไปกับเมล็ด\n" +
                        "2. แช่เมล็ดพันธุ์ ในนน้ำอุ่นประมาณ 20 นาที หรือแช่ด้วยสารป้องกันและกำจัดโรคพืชหรือคลุกด้วยเชื้อราไตรโคเดอร์มาโดยใช้เชื้อ 10 กรัม ต่อน้ำ 10 มิลลิลิตรต่อเมล็ด 1กิโลกรัม\n" +
                        "3. ไม่ปลูกพริกแน่นจนเกินไป\n","1. เก็บผลพริกที่เป็นโรคออกจากแปลง\n" +
                        "2. พ่นเชื้อราไตรโคเดดอร์มา ใช้เชื้อ 100 กรัมผสมน้ำ 20 ลิตร กรองเอาเฉพาะน้ำเชื้อนำไปฉีดพ่นในระยะที่เพิ่งเริ่มเป็นโรคทุกสัปดาห์\n" +
                        "3. พ่นสารป้องกันและกำจัดโรคพืช เช่น แมนโคเซบคาร์เนบดาซิม เบนเลท อัตราตามคำแนะนำในฉลาก\n","โรคแอนแทรกโนส (กุ้งแห้ง)","1.png");

        db.InsertData("โรคราน้ำค้าง","ใบ","กลุ่มแตง",
                "เกิดจาก เชื้อรา Pseudoperonospora cubensis","พบปื้นสีเหลืองด้านหลังใบพบกลุ่มเส้นใยของเชื้อรา","ปื้นสีเหลืองเปลี่ยนเป็น สีน้ำตาล","1. แช่เมล็ดพันธุ์ ในนํ้าอุ่นประมาณ 20 นาที หรือ แช่ด้วยสารป้องกันและกำจัดโรคพืช หรือใช้เชื้อราไตรโคเดอร์มาคลุกเมล็ด โดยใช้เชื้อ 10 กรัม ต่อนํ้า 10 มิลลิลิตร ต่อเมล็ด 1 กิโลกรัม\n" +
                        "2. ปลูกพืชอย่าให้แน่นเกินไป\n","1. พ่นสารป้องกันและกำจัดโรคพืช เช่น แมนโคเซบ เมตาแลกซิล","โรคราน้ำค้าง","2.png");

        db.InsertData("โรคเหี่ยวเขียว","ใบ","กลุ่มมะเขือ",
                "เกิดจาก เชื้อรา Ralstonia solanacearum ชื่อเดิม Pseudomonas solanacearum","ใบเหี่ยวเฉาห้อยตกลงทั้งๆที่ใบเขียว","ถ้าเฉือนต้นตามยาว จะพบท่อน้ำอาหารเป็นสีน้ำตาลฉ่ำน้ำเมื่อตัดรากหรือโคนต้นแช่น้ำจะพบกลุ่มแบคทีเรียสีขาวขุ่นไหลออกมา","1. ปรับสภาพดินให้เป็นด่างเล็กน้อย โดยใส่ปูนขาวและเพิ่ม อินทรีย์วัตถุ\n" +
                        "2. ปรับวิธีการให้น้ำ อย่าให้น้ำไหลจากบริเวณต้นที่เป็นโรคไปยังต้นอื่น\n" +
                        "3. ปลูกพืชหมุนเวียนอย่างน้อย 3 ปี โดยปลูกพืชธัญพืช หรือพืชตระกูลกะหล่ำ\n","1. ขุดทำลายต้นที่เป็นโรคเผาไฟ ห้ามทิ้งลงน้ำ","โรคเหี่ยวเขียว","3.png");

        db.InsertData("โรคโคนเน่า","ลำต้น/ราก","กลุ่มถั่ว",
                "เกิดจาก เชื้อรา Sclerotium rolfsii ; Fusarium sp.","ต้นแสดงอาการเหี่ยวเฉาคล้ายรากขาดน้ำ บริเวณโคนมีลักษณะเน่าเปื่อยเป็นสีน้ำตาลดำ","บริเวณโคนมีเส้นใยเชื้อราสีขาวปกคลุม","1. แช่เมล็ดพันธุ์ในน้ำอุ่นประมาณ 20 นาที หรือแช่ดด้วยสารป้องกันและกำจัดโรคพืชหรือใช้เชื้อราไตรโคเดอร์มาคลุกเมล็ด โดยใช้เชื้อ 10 กรัม ต่อน้ำ 20 มิลลิลิตร ต่อเมล็ด 1 กิโลกรัม\n" +
                        "2. ปรับปรุงดิน ดินที่สภาพเป็นกรดปรับปรุงกินโดยใช้ปูนขาว หรือโดโลไมท์\n","1. ใช้เชื้อราไตรโคเดอร์มา เชื้อ 1 กิโลกรัม รำ 4 กิโลกรัม ปุ๋ยอินทรีย์ 100 กิโลกรัม ใช้ส่วนผสมเชื้อรองก้นหลุม หรือ หว่านในแปลงปลูก หรือหว่านรอบโคนต้น\n" +
                        "2. ถอนต้นที่เป็นโรคออกจากแปลง และหลังเก็บเกี่ยวทำงายเศษซากพืชให้หมดเพื่อลดการสะสมของโรคในแปลง\n","โรคโคนเน่า","4.png");

        db.InsertData("โรครากปม","ลำต้น/ราก","กลุ่มอื่นๆ เช่น ต้นหอม ผักชี ขิง",
                "เกิดจาก ไส้เดือนฝอย : Moloidogyne incognita","ลำต้นแคระแกร็นไม่ค่อยเจริญเติบโต ใบมีลักษณะเปลี่ยนเป็นสีเหลือง","รากมีลักษณะปุ่มปม","1. ไถพรวนดิน พลิกหน้าดินขึ้นตากก่อนทำการปลูกพืช\n" +
                        "2. ควรปลูกพืชบำรุงดิน และพืชที่ไม่เป็นโรคนี้สลับด้วย เช่น ข้าว ข้าวโพด และถั่วต่างๆ \n","1. พ่นสารกำจัดแมลง คาร์โบฟูราน หรือ เมทโธมิล อัตราตามคำแนะนำในฉลาก","โรครากปม","5.png");

*/


        // btnSave (Save)
        final ImageView save = (ImageView) findViewById(R.id.btnSave);



        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // If Save Complete
                if (SaveData()) {
                    // Open Form Main
                    AddActivityManager.this.finish();
                }
            }
        });


        // btnCancel (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnA);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                AddActivityManager.this.finish();
            }
        });


/*
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tVegdisName.length() != 0) {
                    myDb.InsertData(tVegdisName.getText().toString(),
                            tVegdisArea.getText().toString(),
                            tVegdisType.getText().toString(),
                            tVegdisCause.getText().toString(),
                            tSyndrome1.getText().toString(),
                            tSyndrome2.getText().toString(),
                            tRemedy.getText().toString(),
                            tNameImage.getText().toString(),
                            imageViewToByte(imageView));
                    Toast.makeText(AddActivityManager.this, "เพื่มข้อมูลเรียบร้อยแล้ว",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });*/


    }



/*
    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytesArray = stream.toByteArray();
        return bytesArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE_GALLERY);

        } else {

            Toast.makeText(getApplicationContext(), "You don't have permissions to access file", Toast.LENGTH_SHORT).show();

        }
        return;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageView = (ImageView) findViewById(R.id.ShowImage);
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {

            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
*/
    private void init() {
        EditText tVegdisName = (EditText) findViewById(R.id.addName);
        EditText tVegdisArea = (EditText) findViewById(R.id.addArea);
        EditText tVegdisType = (EditText) findViewById(R.id.addType);
        EditText tVegdisCause = (EditText) findViewById(R.id.addCause);
        EditText tSyndrome1 = (EditText) findViewById(R.id.addSyndrome1);
        EditText tSyndrome2 = (EditText) findViewById(R.id.addSyndrome2);
        EditText tRemedy = (EditText) findViewById(R.id.addRemedy);
        EditText tNameImage = (EditText) findViewById(R.id.addNameImage);
    }


    public boolean SaveData() {
        //addName,addSyndrome,addRemedy

        final EditText tVegdisName = (EditText) findViewById(R.id.addName);
        final EditText tVegdisArea = (EditText) findViewById(R.id.addArea);
        final EditText tVegdisType = (EditText) findViewById(R.id.addType);
        final EditText tVegdisCause = (EditText) findViewById(R.id.addCause);
        final EditText tSyndrome1 = (EditText) findViewById(R.id.addSyndrome1);
        final EditText tSyndrome2 = (EditText) findViewById(R.id.addSyndrome2);
        final EditText tProtect = (EditText) findViewById(R.id.addProtect);
        final EditText tRemedy = (EditText) findViewById(R.id.addRemedy);
        final EditText tNameImage = (EditText) findViewById(R.id.addNameImage);
        final EditText tPath = (EditText) findViewById(R.id.addPath);

        // Dialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();


        // new Class DB
        final myDBClass db = new myDBClass(this);


        // Save Data
        long saveStatus;
        saveStatus = db.InsertData(
                tVegdisName.getText().toString(),
                tVegdisArea.getText().toString(),
                tVegdisType.getText().toString(),
                tVegdisCause.getText().toString(),
                tSyndrome1.getText().toString(),
                tSyndrome2.getText().toString(),
                tProtect.getText().toString(),
                tRemedy.getText().toString(),
                tNameImage.getText().toString(),
                tPath.getText().toString());
        if (saveStatus <= 0) {
            ad.setMessage("ไม่สำเร็จ");
            ad.show();
            return false;
        }
        db.close();
        Toast.makeText(AddActivityManager.this, "เพื่มข้อมูลเรียบร้อยแล้ว",
                Toast.LENGTH_SHORT).show();


        return true;

    }


}