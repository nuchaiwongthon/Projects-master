package project.bc.nu.projects;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import project.bc.nu.projects.splash.SplashScreenCalendar;
import project.bc.nu.projects.splash.SplashScreenGenQR;
import project.bc.nu.projects.splash.SplashScreenManager;
import project.bc.nu.projects.splash.SplashScreenVinit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView smsCountTxt;
  //  int pendingSMSCount = 99;
    private int MY_REQUEST_CODE = 99;
    private boolean doubleBackToExitPressedOnce;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
*/        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_REQUEST_CODE);
        }
        final ImageView btn_gen = (ImageView) findViewById(R.id.btn_gen);
        // Perform action on click
        btn_gen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Generate QR
                Intent newActivity = new Intent(MainActivity.this, SplashScreenGenQR.class);
                startActivity(newActivity);

            }
        });

        final ImageView btn_vinit = (ImageView) findViewById(R.id.btn_vinit);
        // Perform action on click
        btn_vinit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Vinit
                Intent newActivity = new Intent(MainActivity.this, SplashScreenVinit.class);
                startActivity(newActivity);

            }
        });
        final ImageView btn_calendar = (ImageView) findViewById(R.id.btn_calendar);
        // Perform action on click
        btn_calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Calendar
                Intent newActivity = new Intent(MainActivity.this, SplashScreenCalendar.class);
                startActivity(newActivity);

            }
        });
        final ImageView btn_manager = (ImageView) findViewById(R.id.btn_manager);
        // Perform action on click
        btn_manager.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Manager
                Intent newActivity = new Intent(MainActivity.this, SplashScreenManager.class);
                startActivity(newActivity);

            }
        });
    }


    @Override
    public void onBackPressed() {
      if (doubleBackToExitPressedOnce) {
            super.onBackPressed();

            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(getApplicationContext(),"กดอีกครั้งเพื่อออกโปรแกรม",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;

            }
        }, 3000);

    /*    AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.exit_message);
        builder.setPositiveButton("ออก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.show();

*/
    }

    /*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_notifications);

        View actionView = MenuItemCompat.getActionView(menuItem);
        smsCountTxt = (TextView) actionView.findViewById(R.id.notification_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;


    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_notifications: {
                Toast.makeText(getApplicationContext(),"อยู่ระหว่างปรับปรุง !!",Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {

      /*  if (smsCountTxt != null) {
            if (pendingSMSCount == 0) {
                if (smsCountTxt.getVisibility() != View.GONE) {
                    smsCountTxt.setVisibility(View.GONE);
                }
            } else {
                smsCountTxt.setText(String.valueOf(Math.min(pendingSMSCount, 99)));
                if (smsCountTxt.getVisibility() != View.VISIBLE) {
                    smsCountTxt.setVisibility(View.VISIBLE);
                }
            }
        }*/
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem Item) {
        // Handle navigation view item clicks here.
        int id=Item.getItemId();
        switch (id){
            case R.id.nav_home:
                break;
            case R.id.nav_gen:
                Intent i= new Intent(MainActivity.this,SplashScreenGenQR.class);
                startActivity(i);
                break;
            case R.id.nav_vinit:
                Intent s= new Intent(MainActivity.this,SplashScreenVinit.class);
                startActivity(s);
            case R.id.nav_calendar:
                Intent t= new Intent(MainActivity.this,SplashScreenCalendar.class);
                startActivity(t);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
