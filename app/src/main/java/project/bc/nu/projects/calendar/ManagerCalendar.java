package project.bc.nu.projects.calendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import project.bc.nu.projects.MainActivity;
import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.myDBClass;
import project.bc.nu.projects.splash.SplashScreenGenQR;
import project.bc.nu.projects.splash.SplashScreenManager;
import project.bc.nu.projects.splash.SplashScreenVinit;

public class ManagerCalendar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView smsCountTxt;
    int pendingSMSCount = 99;
    Integer d,m,y;
    private static final String TAG = "CalendarMainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private boolean doubleBackToExitPressedOnce;
    TimelineRecyclerview timeline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calendar);
        final myDBClass myDb = new myDBClass(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newActivity = new Intent(ManagerCalendar.this, CalendarMainActivity.class);
                startActivity(newActivity);
                newActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RecyclerView timeline_recyclerview = findViewById(R.id.timeline);
        timeline = new TimelineRecyclerview();
        new TimelineRecyclerview().useRecyclerView(this,timeline_recyclerview);
        timeline.useRecyclerView(this, timeline_recyclerview);

    }
    @Override
    public void onStart() {
        timeline.reloadRecyclerview();
        super.onStart();

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        } if (doubleBackToExitPressedOnce) {
            super.onBackPressed();

            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(getApplicationContext(), "กดอีกครั้งเพื่อออก", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;

            }
        }, 2000);

    }



    @Override
    public boolean onNavigationItemSelected( MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {

            case R.id.nav_home:
                Intent h = new Intent(ManagerCalendar.this, MainActivity.class);
                h.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(h);
                break;
            case R.id.nav_gen:
                Intent i = new Intent(ManagerCalendar.this, SplashScreenGenQR.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.nav_vinit:
                Intent v = new Intent(ManagerCalendar.this, SplashScreenVinit.class);
                v.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(v);
                break;
            case R.id.nav_calendar:
                break;
            case R.id.nav_manager:
                Intent m = new Intent(ManagerCalendar.this, SplashScreenManager.class);
                m.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }





}
