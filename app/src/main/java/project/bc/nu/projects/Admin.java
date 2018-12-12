package project.bc.nu.projects;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import project.bc.nu.projects.calendar.CalendarMainActivity;
import project.bc.nu.projects.calendar.ManagerCalendar;
import project.bc.nu.projects.vinit.SelectSyndrome2;
import project.bc.nu.projects.vinit.SelectSyndrome3;

public class Admin extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        EditText user = (EditText) findViewById(R.id.UAdmin);
        EditText pass = (EditText) findViewById(R.id.PAdmin);
        Button login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getText().toString().equals("admin")&& pass.getText().toString().equals("1234")){

                    Intent newActivity = new Intent(Admin.this, MainActivityManager.class);
                    startActivity(newActivity);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    Toast.makeText(getApplicationContext(),
                            "เข้าสู่ระบบเรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();

                }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Admin.this);
                        builder.setMessage("กรอกชื่อผู้ใช้หรือรหัสผ่านผิดพลาด โปรดลองอีกครั้ง");
                    builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                }

            }
        });
    }
}
