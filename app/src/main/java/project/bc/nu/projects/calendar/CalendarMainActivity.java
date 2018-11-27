package project.bc.nu.projects.calendar;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import project.bc.nu.projects.R;
import project.bc.nu.projects.SQLite.MyDB;
import project.bc.nu.projects.TimelineAdapter;
import project.bc.nu.projects.SQLite.myDBClass;

public class CalendarMainActivity extends AppCompatActivity {

    Integer d, m, y;
    private static final String channelId = "1000";
    private static final String channelDescription = "Default Channel";
    private TimelineAdapter mMyAdapter;
    private NotificationManager notifManager;
    private static final String TAG = "CalendarMainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private boolean doubleBackToExitPressedOnce;
    private static final String sTagAlarms = ":alarms";

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = channelId;
            String description = channelDescription;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_add);
        final myDBClass myDb = new myDBClass(this);
        // btnCancel (Cancel)
        //createNotificationChannel();


        SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
        SharedPreferences.Editor editor = sharedPref.edit();

        Random rand = new Random();
        int n = rand.nextInt(200);


        Calendar c = Calendar.getInstance();
        y = c.get(Calendar.YEAR);
        m = c.get(Calendar.MONTH);
        d = c.get(Calendar.DAY_OF_MONTH);

        // btnCancel (Cancel)
        final ImageView cancel = (ImageView) findViewById(R.id.backBtnC);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                CalendarMainActivity.this.finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });
        final List<String> arrList = new ArrayList<String>();
        //  final Spinner spin1 = (Spinner) findViewById(R.id.sp_Type);
        final Spinner spin2 = (Spinner) findViewById(R.id.sp_Calendar);

        final Spinner sp_Calendar = (Spinner) findViewById(R.id.sp_Calendar);
        final Button nxt = (Button) findViewById(R.id.btnCalender);
        final MyDB db = new MyDB(this);
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sp_Calendar.getSelectedItem().toString().equals("กะหล่ำดอก")) {
                    db.setVegatable(1, y, m, d);

                    ArrayList<MyDB.Timeline> arrayList = db.getTimeline(1);
                    Intent intent = new Intent(CalendarMainActivity.this, ManagerCalendar.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    int sizOfdata = arrayList.size();

                    Context ctx = getApplicationContext();
                    Intent cancelServiceIntent = new Intent(ctx, AlarmReceiver.class);
                    cancelAllAlarms(ctx, cancelServiceIntent);


                    for (int i = 0; i < sizOfdata; i++) {
                        start(arrayList.get(i).getDATE(), y, m, d, "กะหล่ำดอก", arrayList.get(i).getOBJECTIVE(), n + i);
                    }


                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                } else if (sp_Calendar.getSelectedItem().toString().equals("บวบเหลี่ยม")) {

                    db.setVegatable(7, y, m, d);

                    ArrayList<MyDB.Timeline> arrayList = db.getTimeline(7);
                    Intent intent = new Intent(CalendarMainActivity.this, ManagerCalendar.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    int sizOfdata = arrayList.size();

                    Context ctx = getApplicationContext();
                    Intent cancelServiceIntent = new Intent(ctx, AlarmReceiver.class);
                    cancelAllAlarms(ctx, cancelServiceIntent);

                    for (int i = 0; i < sizOfdata; i++) {
                        start(arrayList.get(i).getDATE(), y, m, d, "บวบเหลี่ยม", arrayList.get(i).getOBJECTIVE(), n + i);
                    }
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                } else if (sp_Calendar.getSelectedItem().toString().equals("มะเขือเทศ")) {

                    db.setVegatable(12, y, m, d);

                    ArrayList<MyDB.Timeline> arrayList = db.getTimeline(12);
                    Intent intent = new Intent(CalendarMainActivity.this, ManagerCalendar.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    int sizOfdata = arrayList.size();

                    Context ctx = getApplicationContext();
                    Intent cancelServiceIntent = new Intent(ctx, AlarmReceiver.class);
                    cancelAllAlarms(ctx, cancelServiceIntent);

                    for (int i = 0; i < sizOfdata; i++) {
                        start(arrayList.get(i).getDATE(), y, m, d, "มะเขือเทศ", arrayList.get(i).getOBJECTIVE(), n + i);
                    }

                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                } else if (sp_Calendar.getSelectedItem().toString().equals("คะน้าฮ้องกง")) {

                    db.setVegatable(20, y, m, d);

                    ArrayList<MyDB.Timeline> arrayList = db.getTimeline(20);
                    Intent intent = new Intent(CalendarMainActivity.this, ManagerCalendar.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    int sizOfdata = arrayList.size();

                    Context ctx = getApplicationContext();
                    Intent cancelServiceIntent = new Intent(ctx, AlarmReceiver.class);
                    cancelAllAlarms(ctx, cancelServiceIntent);

                    for (int i = 0; i < sizOfdata; i++) {
                        start(arrayList.get(i).getDATE(), y, m, d, "คะน้าฮ้องกง", arrayList.get(i).getOBJECTIVE(), n + i);
                    }

                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                } else if (sp_Calendar.getSelectedItem().toString().equals("กระเทียม")) {

                    db.setVegatable(38, y, m, d);

                    ArrayList<MyDB.Timeline> arrayList = db.getTimeline(38);
                    Intent intent = new Intent(CalendarMainActivity.this, ManagerCalendar.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    int sizOfdata = arrayList.size();

                    Context ctx = getApplicationContext();
                    Intent cancelServiceIntent = new Intent(ctx, AlarmReceiver.class);
                    cancelAllAlarms(ctx, cancelServiceIntent);

                    for (int i = 0; i < sizOfdata; i++) {
                        start(arrayList.get(i).getDATE(), y, m, d, "กระเทียม", arrayList.get(i).getOBJECTIVE(), n + i);
                    }


                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();

                }

            }
        });

        String[] veg = new String[]{
                "กรุณาเลือกผัก",
                "กะหล่ำดอก",
                "บวบเหลี่ยม",
                "มะเขือเทศ",
                "คะน้าฮ้องกง",
                "กระเทียม",


        };
        // Initializing an ArrayAdapter
        final List<String> vegList = new ArrayList<>(Arrays.asList(veg));

        ArrayAdapter<String> spinnerArrayVegType = new ArrayAdapter<String>(this, R.layout.spinner_item, vegList) {
        };

        //   final  ArrayList<String> vegList = myDb.SelectAllVeg1();

        ArrayAdapter<String> spinnerArrayVeg = new ArrayAdapter<String>(
                this, R.layout.spinner_item, vegList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {

                    return true;
                } else {

                    return true;

                }

            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayVeg.setDropDownViewResource(R.layout.spinner_item);
        //  spinnerArrayVegType.setDropDownViewResource(R.layout.spinner_item);
        spin2.setAdapter(spinnerArrayVeg);
        // spin1.setAdapter(spinnerArrayVegType);


        mDisplayDate = (TextView) findViewById(R.id.PlantDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(
                        CalendarMainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        y, m, d);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                y = year;
                m = month + 1;
                d = day;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);
                String date = day + "/" + (month+1) + "/" + year;
                mDisplayDate.setText(date);
            }
        };

    }


    void start(int number_Date, int year, int mont, int day, String title, String content, int rqcode) {
        try {

            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
/*
            Date dat = new Date();
            Calendar cal_alarm = Calendar.getInstance();
            Calendar cal_now = Calendar.getInstance();
            cal_now.setTime(dat);


            cal_alarm.setTime(dat);
            cal_alarm.set(y,m-1,d);
            cal_alarm.set(Calendar.HOUR_OF_DAY, 12);
            cal_alarm.set(Calendar.MINUTE, 50);
            cal_alarm.set(Calendar.SECOND,0);
            if(cal_alarm.before(cal_now)){

                cal_alarm.add(Calendar.DATE,1);

            }


*/
            Calendar cal_alarm = Calendar.getInstance();
            cal_alarm.set(y, m - 1, d);
            cal_alarm.set(Calendar.HOUR_OF_DAY, 8);
            cal_alarm.set(Calendar.MINUTE, 0);
            cal_alarm.set(Calendar.SECOND, 0);
            cal_alarm.add(Calendar.DAY_OF_YEAR, number_Date);


            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra("extra", content);
            intent.putExtra("extrat", title);
            intent.putExtra("rqcode", rqcode);
            PendingIntent ppi = PendingIntent.getBroadcast(this.getApplicationContext(), rqcode, intent, 0);

            manager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), ppi);

            saveAlarmId(this, rqcode);


        } catch (Exception e) {
            Toast.makeText(CalendarMainActivity.this, "Error Intent", Toast.LENGTH_SHORT).show();
        }

    }


    public static void cancelAlarm(Context context, Intent intent, int notificationId) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();

        removeAlarmId(context, notificationId);
    }

    public static void cancelAllAlarms(Context context, Intent intent) {
        for (int idAlarm : getAlarmIds(context)) {
            cancelAlarm(context, intent, idAlarm);
        }
    }

    public static boolean hasAlarm(Context context, Intent intent, int notificationId) {
        return PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_NO_CREATE) != null;
    }

    private static void saveAlarmId(Context context, int id) {
        List<Integer> idsAlarms = getAlarmIds(context);

        if (idsAlarms.contains(id)) {
            return;
        }

        idsAlarms.add(id);

        saveIdsInPreferences(context, idsAlarms);
    }

    private static void removeAlarmId(Context context, int id) {
        List<Integer> idsAlarms = getAlarmIds(context);

        for (int i = 0; i < idsAlarms.size(); i++) {
            if (idsAlarms.get(i) == id)
                idsAlarms.remove(i);
        }

        saveIdsInPreferences(context, idsAlarms);
    }

    private static List<Integer> getAlarmIds(Context context) {
        List<Integer> ids = new ArrayList<>();
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            JSONArray jsonArray2 = new JSONArray(prefs.getString(context.getPackageName() + sTagAlarms, "[]"));

            for (int i = 0; i < jsonArray2.length(); i++) {
                ids.add(jsonArray2.getInt(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ids;
    }

    private static void saveIdsInPreferences(Context context, List<Integer> lstIds) {
        JSONArray jsonArray = new JSONArray();
        for (Integer idAlarm : lstIds) {
            jsonArray.put(idAlarm);
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(context.getPackageName() + sTagAlarms, jsonArray.toString());

        editor.apply();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }


}
