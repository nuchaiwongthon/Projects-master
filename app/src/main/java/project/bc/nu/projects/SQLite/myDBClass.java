package project.bc.nu.projects.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import project.bc.nu.projects.calendar.ManagerCalendar;

import static project.bc.nu.projects.manager.AddActivityManager.db;

public class myDBClass extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "database.db";
    // Table Name
    public static final String TABLE_VEGDIS = "vegetabledisease";

    public static final String TABLE_VEG = "vegetable";


    public myDBClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_VEGDIS + " (vegdis_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
                ",vegdis_name TEXT," +
                "vegdis_area TEXT," +
                "vegdis_type TEXT," +
                "vegdis_cause TEXT," +
                "vegdis_syndrome1 TEXT," +
                "vegdis_syndrome2 TEXT," +
                "vegdis_protect TEXT," +
                "vegdis_remedy TEXT," +
                "vegdis_gallery_name TEXT," +
                "vegdis_gallery_path TEXT);");


        db.execSQL("create table " + TABLE_VEG + " (veg_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
                ",veg_name_th TEXT," +
                "veg_name_cm TEXT," +
                "veg_name_sc TEXT," +
                "veg_structure TEXT," +
                "veg_nutrient TEXT," +
                "veg_plant TEXT," +
                "veg_treatment TEXT," +
                "veg_type TEXT," +
                "veg_gallery_name TEXT," +
                "veg_gallery_path TEXT);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEGDIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEG);
        // Re Create on method  onCreate
        onCreate(db);
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String VID) {
        SQLiteDatabase db;
        db = this.getReadableDatabase(); // Read Data
        return db.rawQuery("SELECT * FROM vegetabledisease ", null);

    }


    // Insert Data โรคผัก
    public long InsertData(String strVegdisName, String strVegdisArea, String strVegdisType, String strVegdisCause,
                           String strSyndrome1, String strSyndrome2, String strProtect, String strRemedy,String VegdisGallaryName, String VegdisGallaryPath) {

        try {


            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data


            ContentValues Val = new ContentValues();
            Val.put("vegdis_name", strVegdisName);
            Val.put("vegdis_area", strVegdisArea);
            Val.put("vegdis_type", strVegdisType);
            Val.put("vegdis_cause", strVegdisCause);
            Val.put("vegdis_syndrome1", strSyndrome1);
            Val.put("vegdis_syndrome2", strSyndrome2);
            Val.put("vegdis_protect", strProtect);
            Val.put("vegdis_remedy", strRemedy);
            Val.put("vegdis_gallery_name", VegdisGallaryName);
            Val.put("vegdis_gallery_path", VegdisGallaryPath);

            long rows = db.insert(TABLE_VEGDIS, null, Val);

            db.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return -1;
        }


    }

    // Insert Data ผัก
    public long InsertDataVeg(String VegNameTH, String VegNameCM, String VegNameSC, String VegStructure,
                              String VegNutrient, String VegPlant, String VegTreatment,String VegType,String VegGallaryName,String VegGallaryPath) {

        try {


            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data


            ContentValues Val = new ContentValues();
            Val.put("veg_name_th", VegNameTH);
            Val.put("veg_name_cm", VegNameCM);
            Val.put("veg_name_sc", VegNameSC);
            Val.put("veg_structure", VegStructure);
            Val.put("veg_nutrient", VegNutrient);
            Val.put("veg_plant", VegPlant);
            Val.put("veg_treatment", VegTreatment);
            Val.put("veg_type", VegType);
            Val.put("veg_gallery_name", VegGallaryName);
            Val.put("veg_gallery_path", VegGallaryPath);

            long rows = db.insert(TABLE_VEG, null, Val);

            db.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return -1;
        }


    }



    // Select Data โรคผัก
    public String[] SelectData(String strVegdisID) {
        try {
            String arrData[] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Cursor cursor = db.query(TABLE_VEGDIS, new String[]{"*"},
                    "vegdis_id=?",
                    new String[]{String.valueOf(strVegdisID)}, null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        arrData = new String[cursor.getColumnCount()];
                        /***
                         *  0 = vegdis_id
                         *  1 = vegdis_name
                         *  2 = vegdis_area
                         *  3 = vegdis_type
                         *  4 = vegdis_cause
                         *  5 = vegdis_syndrome1
                         *  6 = vegdis_syndrome2
                         *  7 = vegdis_protect
                         *  8 = vegdis_remedy
                         *  9 = vegdis_gallery_name
                         *  10 = vegdis_gallery_path
                         */

                        arrData[0] = cursor.getString(0);
                        arrData[1] = cursor.getString(1);
                        arrData[2] = cursor.getString(2);
                        arrData[3] = cursor.getString(3);
                        arrData[4] = cursor.getString(4);
                        arrData[5] = cursor.getString(5);
                        arrData[6] = cursor.getString(6);
                        arrData[7] = cursor.getString(7);
                        arrData[8] = cursor.getString(8);
                        arrData[9] = cursor.getString(9);
                        arrData[10] = cursor.getString(10);
                    } while (cursor.moveToNext());
                }
            }

            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }


    }

    // Select Data ผัก
    public String[] SelectDataVeg(String VegID) {
        try {
            String arrData[] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Cursor cursor = db.query(TABLE_VEG, new String[]{"*"},
                    "veg_id=?",
                    new String[]{String.valueOf(VegID)}, null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        arrData = new String[cursor.getColumnCount()];

                        arrData[0] = cursor.getString(0);
                        arrData[1] = cursor.getString(1);
                        arrData[2] = cursor.getString(2);
                        arrData[3] = cursor.getString(3);
                        arrData[4] = cursor.getString(4);
                        arrData[5] = cursor.getString(5);
                        arrData[6] = cursor.getString(6);
                        arrData[7] = cursor.getString(7);
                        arrData[8] = cursor.getString(8);
                        arrData[9] = cursor.getString(9);
                        arrData[10] = cursor.getString(10);
                    } while (cursor.moveToNext());
                }
            }

            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }


    }
    // Select All Data ผัก
    public String[][] SelectAllVeg() {
        // TODO Auto-generated method stub

        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT  * FROM " + TABLE_VEG;
            Cursor cursor = db.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];


                    int i= 0;
                    do {

                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        arrData[i][3] = cursor.getString(3);
                        arrData[i][4] = cursor.getString(4);
                        arrData[i][5] = cursor.getString(5);
                        arrData[i][6] = cursor.getString(6);
                        arrData[i][7] = cursor.getString(7);
                        arrData[i][8] = cursor.getString(8);
                        arrData[i][9] = cursor.getString(9);
                        arrData[i][10] = cursor.getString(10);

                        i++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }

    }

    // Select All Data โรคผัก
    public String[][] SelectAllVegDis() {
        // TODO Auto-generated method stub

        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT  * FROM " + TABLE_VEGDIS;
            Cursor cursor = db.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    /***
                     *  0 = vegdis_id
                     *  1 = vegdis_name
                     *  2 = vegdis_area
                     *  3 = vegdis_type
                     *  4 = vegdis_cause
                     *  5 = vegdis_syndrome1
                     *  6 = vegdis_syndrome2
                     *  7 = vegdis_protect
                     *  8 = vegdis_remedy
                     *  9 = vegdis_gallery_name
                     *  10 = vegdis_gallery_path
                     *
                     */

                    int i= 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        arrData[i][3] = cursor.getString(3);
                        arrData[i][4] = cursor.getString(4);
                        arrData[i][5] = cursor.getString(5);
                        arrData[i][6] = cursor.getString(6);
                        arrData[i][7] = cursor.getString(7);
                        arrData[i][8] = cursor.getString(8);
                        arrData[i][9] = cursor.getString(9);
                        arrData[i][10] = cursor.getString(10);
                        i++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }

    }
    // Select All Data ปฏิทิน
    public ArrayList<String> SelectAllVeg1() {
        // TODO Auto-generated method stub

        try {
            ArrayList< String> arrData = new ArrayList< String>();

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT  * FROM " + TABLE_VEG;
            Cursor cursor = db.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new ArrayList<String>();

                    do {
                        arrData.add(cursor.getString(1));
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }

    }
    // Show All Data โรค
    public ArrayList<HashMap<String, String>> SelectAllData() {

        try {

            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT  * FROM " + TABLE_VEGDIS;
            Cursor cursor = db.rawQuery(strSQL, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("vegdis_id", cursor.getString(0));
                        map.put("vegdis_name", cursor.getString(1));
                        map.put("vegdis_area", cursor.getString(2));
                        map.put("vegdis_type", cursor.getString(3));
                        map.put("vegdis_cause", cursor.getString(4));
                        map.put("vegdis_syndrome1", cursor.getString(5));
                        map.put("vegdis_syndrome2", cursor.getString(6));
                        map.put("vegdis_protect", cursor.getString(7));
                        map.put("vegdis_remedy", cursor.getString(8));
                        map.put("vegdis_gallery_name", cursor.getString(9));
                        map.put("vegdis_gallery_path", cursor.getString(10));

                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            return null;
        }

    }

    // Show All Data ผัก
    public ArrayList<HashMap<String, String>> SelectAllDataVeg() {

        try {

            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT  * FROM " + TABLE_VEG;
            Cursor cursor = db.rawQuery(strSQL, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("veg_id", cursor.getString(0));
                        map.put("veg_name_th", cursor.getString(1));
                        map.put("veg_name_cm", cursor.getString(2));
                        map.put("veg_name_sc", cursor.getString(3));
                        map.put("veg_structure", cursor.getString(4));
                        map.put("veg_nutrient", cursor.getString(5));
                        map.put("veg_plant", cursor.getString(6));
                        map.put("veg_treatment", cursor.getString(7));
                        map.put("veg_type", cursor.getString(8));
                        map.put("veg_gallery_name", cursor.getString(9));
                        map.put("veg_gallery_path", cursor.getString(10));
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            return null;
        }

    }


    // Update Data โรค
    public long UpdateData(String strVegdisID, String strVegdisName, String strVegdisArea, String strVegdisType, String strVegdisCause,
                           String strSyndrome1, String strSyndrome2,String strProtect, String strRemedy) {

        try {

            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            ContentValues Val = new ContentValues();
            Val.put("vegdis_name", strVegdisName);
            Val.put("vegdis_area", strVegdisArea);
            Val.put("vegdis_type", strVegdisType);
            Val.put("vegdis_cause", strVegdisCause);
            Val.put("vegdis_syndrome1", strSyndrome1);
            Val.put("vegdis_syndrome2", strSyndrome2);
            Val.put("vegdis_protect", strProtect);
            Val.put("vegdis_remedy", strRemedy);

            long rows = db.update(TABLE_VEGDIS, Val, " vegdis_id = ?",
                    new String[]{String.valueOf(strVegdisID)});

            db.close();
            return rows; // return rows updated.

        } catch (Exception e) {
            return -1;
        }

    }

    // Update Data ผัก
    public long UpdateDataVeg(String VegID, String VegNameTH, String VegNameCM, String VegNameSC, String VegStructure,
                              String VegNutrient, String VegPlant, String VegTreatment,String VegType,String VegGallaryName,String VegGallaryPath) {

        try {

            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            ContentValues Val = new ContentValues();
            Val.put("veg_name_th", VegNameTH);
            Val.put("veg_name_cm", VegNameCM);
            Val.put("veg_name_sc", VegNameSC);
            Val.put("veg_structure", VegStructure);
            Val.put("veg_nutrient", VegNutrient);
            Val.put("veg_plant", VegPlant);
            Val.put("veg_treatment", VegTreatment);
            Val.put("veg_type", VegType);
            Val.put("veg_gallery_name", VegGallaryName);
            Val.put("veg_gallery_path", VegGallaryPath);

            long rows = db.update(TABLE_VEG, Val, " veg_id = ?",
                    new String[]{String.valueOf(VegID)});

            db.close();
            return rows; // return rows updated.

        } catch (Exception e) {
            return -1;
        }

    }

    // Delete Data โรค
    public long DeleteData(String strVegdisID) {
        // TODO Auto-generated method stub

        try {

            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data


            long rows = db.delete(TABLE_VEGDIS, "vegdis_id = ?",
                    new String[]{String.valueOf(strVegdisID)});

            db.close();
            return rows; // return rows deleted.

        } catch (Exception e) {
            return -1;
        }

    }

    // Delete Data ผัก
    public long DeleteDataVeg(String VegID) {
        // TODO Auto-generated method stub

        try {

            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data


            long rows = db.delete(TABLE_VEG, "veg_id = ?",
                    new String[]{String.valueOf(VegID)});

            db.close();
            return rows; // return rows deleted.

        } catch (Exception e) {
            return -1;
        }

    }
}