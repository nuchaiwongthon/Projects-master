package project.bc.nu.projects.vinit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import project.bc.nu.projects.MainActivity;
import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.myDBClass;
import project.bc.nu.projects.splash.SplashScreenCalendar;
import project.bc.nu.projects.splash.SplashScreenGenQR;
import project.bc.nu.projects.splash.SplashScreenManager;


public class VinitMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RadioGroup mRadioGroup;
    private boolean doubleBackToExitPressedOnce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vinit);
        mRadioGroup = (RadioGroup) findViewById(R.id.menu);


        final Intent intent = getIntent();
        final String strVegdisID = intent.getStringExtra("strVegdisID");

        ShowData(strVegdisID);
        //RadioButton 1
        final RadioButton btn1 = (RadioButton) findViewById(R.id.Rbtn1);
        // Perform action on click
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRadioGroup.clearCheck();
                Intent newActivity = new Intent(VinitMainActivity.this, SelectTypeVegdisease.class);
                newActivity.putExtra("strVegdisID", strVegdisID);
                startActivity(newActivity);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        //RadioButton 2
        final RadioButton btn2 = (RadioButton) findViewById(R.id.Rbtn2);
        // Perform action on click
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRadioGroup.clearCheck();
                Intent newActivity = new Intent(VinitMainActivity.this, SelectTypeVegdisease2.class);
                newActivity.putExtra("strVegdisID", strVegdisID);
                startActivity(newActivity);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        //RadioButton 3
        final RadioButton btn3 = (RadioButton) findViewById(R.id.Rbtn3);
        // Perform action on click
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRadioGroup.clearCheck();
                Intent newActivity = new Intent(VinitMainActivity.this, SelectTypeVegdisease3.class);
                newActivity.putExtra("strVegdisID", strVegdisID);
                startActivity(newActivity);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        //RadioButton 4
        final RadioButton btn4 = (RadioButton) findViewById(R.id.Rbtn4);
        // Perform action on click
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRadioGroup.clearCheck();
                Intent newActivity = new Intent(VinitMainActivity.this, SelectTypeVegdisease4.class);
                newActivity.putExtra("strVegdisID", strVegdisID);
                startActivity(newActivity);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        //RadioButton 5
        final RadioButton btn5 = (RadioButton) findViewById(R.id.Rbtn5);
        // Perform action on click
        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRadioGroup.clearCheck();
                Intent newActivity = new Intent(VinitMainActivity.this, SelectTypeVegdisease5.class);
                newActivity.putExtra("strVegdisID", strVegdisID);
                startActivity(newActivity);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        //RadioButton 6
        final RadioButton btn6 = (RadioButton) findViewById(R.id.Rbtn6);
        // Perform action on click
        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRadioGroup.clearCheck();
                Intent newActivity = new Intent(VinitMainActivity.this, SelectTypeVegdisease6.class);
                newActivity.putExtra("strVegdisID", strVegdisID);
                startActivity(newActivity);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);


        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void on() {
        super.onRestart();


    }
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
                super.onBackPressed();

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


        }


    private void ShowData(String strVegdisID) {
        final myDBClass myDb = new myDBClass(this);

        final RadioButton btn1 = (RadioButton) findViewById(R.id.Rbtn1);
        final RadioButton btn2 = (RadioButton) findViewById(R.id.Rbtn2);
        final RadioButton btn3 = (RadioButton) findViewById(R.id.Rbtn3);
        final RadioButton btn4 = (RadioButton) findViewById(R.id.Rbtn4);
        final RadioButton btn5 = (RadioButton) findViewById(R.id.Rbtn5);
        final RadioButton btn6 = (RadioButton) findViewById(R.id.Rbtn6);
        // Show Data

        btn1.setSelected(!btn1.isSelected());
        if (btn1.isSelected()) {
            String arrData[] = myDb.SelectData("3");
            if (arrData != null) {
                btn1.setText(arrData[3]);
            }
        }
        btn2.setSelected(!btn2.isSelected());
        if (btn2.isSelected()) {
            String arrData[] = myDb.SelectData("1");
            if (arrData != null) {
                btn2.setText(arrData[3]);
            }
        }
        btn3.setSelected(!btn3.isSelected());
        if (btn3.isSelected()) {
            String arrData[] = myDb.SelectData("2");
            if (arrData != null) {
                btn3.setText(arrData[3]);
            }
        }
        btn4.setSelected(!btn4.isSelected());
        if (btn4.isSelected()) {

            btn4.setText("กลุ่มกะหล่ำ/ผักกาด");
        }
        btn5.setSelected(!btn5.isSelected());
        if (btn5.isSelected()) {
            String arrData[] = myDb.SelectData("4");
            if (arrData != null) {
                btn5.setText(arrData[3]);
            }
        }
        btn6.setSelected(!btn6.isSelected());
        if (btn6.isSelected()) {
            String arrData[] = myDb.SelectData("5");
            if (arrData != null) {
                btn6.setText(arrData[3]);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {

            case R.id.nav_home:
                Intent h = new Intent(VinitMainActivity.this, MainActivity.class);
                h.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(h);

                break;
            case R.id.nav_gen:
                Intent i = new Intent(VinitMainActivity.this, SplashScreenGenQR.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.nav_vinit:
                break;
            case R.id.nav_calendar:
                Intent t = new Intent(VinitMainActivity.this, SplashScreenCalendar.class);
                t.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(t);
                break;
            case R.id.nav_manager:
                Intent m = new Intent(VinitMainActivity.this, SplashScreenManager.class);
                m.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

}