package project.bc.nu.projects.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import project.bc.nu.projects.SQLite.myDBClass.TABLE_VEG
import java.util.*
import kotlin.collections.ArrayList

class MyDB(private val context: Context) : SQLiteOpenHelper(context, MyDB.DatabaseName, null, 1) {

    private val ID = "VEGID"
    private var TYPE = "VEGTYPE"
    private var NAME = "VEGNAME"

    private var DATE_START = "DATE"
    private var DATE_STOP = "STOP"
    private var OBJECTIVE = "OBJECTIVE"

    private var DAY = "DAY"
    private var MONTH = "MONTH"
    private var YEAR = "YEAR"
    private var PASS = "PASS"


    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL("CREATE TABLE ${MyDB.resourceTableName} (_id INTEGER PRIMARY KEY AUTOINCREMENT, $ID INTEGER,$TYPE TEXT,$NAME TEXT)")
        db.execSQL("CREATE TABLE ${MyDB.resourceTimeLine} (_id INTEGER PRIMARY KEY AUTOINCREMENT, $ID INTEGER,$NAME,$DATE_START INTEGER,$OBJECTIVE TEXT)")

        db.execSQL("CREATE TABLE ${MyDB.myData} (_id INTEGER PRIMARY KEY AUTOINCREMENT, $ID INTEGER,$NAME TEXT,$YEAR INTEGER,$MONTH INTEGER,$DAY INTEGER,$PASS INTEGER)")

        db.execSQL("create table " + MyDB.vegdis_table + " (vegdis_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
                ",vegdis_name TEXT," +
                "vegdis_area TEXT," +
                "vegdis_type TEXT," +
                "vegdis_cause TEXT," +
                "vegdis_syndrome1 TEXT," +
                "vegdis_syndrome2 TEXT," +
                "vegdis_protect TEXT," +
                "vegdis_remedy TEXT," +
                "vegdis_gallery_name TEXT," +
                "vegdis_gallery_path TEXT);")

        db.execSQL("create table " + MyDB.veg_table + " (veg_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
                ",veg_name_th TEXT," +
                "veg_name_cm TEXT," +
                "veg_name_sc TEXT," +
                "veg_structure TEXT," +
                "veg_nutrient TEXT," +
                "veg_plant TEXT," +
                "veg_treatment TEXT," +
                "veg_type TEXT," +
                "veg_gallery_name TEXT," +
                "veg_gallery_path TEXT);")

        db.init()

    }

    private fun SQLiteDatabase.init() {

        this.insertTable(1, "ผักกินดอก", "กะหล่ำดอก")
        this.insertTable(7, "ผักกินฝักและผล", "บวบเหลี่ยม")
        this.insertTable(12, "ผักกินฝักและผล", "มะเขือเทศ")
        this.insertTable(20, "ผักกินใบและลำต้น", "คะน้าฮ้องกง")
        this.insertTable(38, "กินหัวและกินราก", "กระเทียม")

       // this.insertTimeline(1,"กะหล่ำดอก", 0, "เริ่มปลูก กะหล่ำดอก")
        this.insertTimeline(1,"กะหล่ำดอก", 0, "ใส่ปุ๋ยครั่งที่ 1 " + "สูตร 46-0-0 อัตรา 30 กก./ไร่")
        this.insertTimeline(1,"กะหล่ำดอก", 2, "ใส่ปุ๋ยครั่งที่ 2 " + "สูตร 15-15-15 อัตรา 50 กก./ไร่")
        this.insertTimeline(1,"กะหล่ำดอก", 3, "ใส่ปุ๋ยครั่งที่ 3 "+"สูตร 13-13-21 หรือ 14-14-21 อัตรา 50 กก./ไร่")
        this.insertTimeline(1,"กะหล่ำดอก", 10, "เริ่มเก็บเกี่ยว กะหล่ำดอก")

      //  this.insertTimeline(7,"บวบเหลี่ยม", 0, "เริ่มปลูก บวบเหลี่ยม")
        this.insertTimeline(7,"บวบเหลี่ยม", 0, "ใส่ปุ๋ยครั่งที่ 1 " + "สูตร 46-0-0 อัตรา 30 กก./ไร่")
        this.insertTimeline(7,"บวบเหลี่ยม", 20, "ใส่ปุ๋ยครั่งที่ 2 " + "สูตร 15-15-15 หรือ 16-16-16 อัตรา 40 กก./ไร่")
        this.insertTimeline(7,"บวบเหลี่ยม", 30, "ใส่ปุ๋ยครั่งที่ 3 " + "สูตร 13-13-21 อัตรา 30-50 กก./ไร่")
        this.insertTimeline(7,"บวบเหลี่ยม", 40, "เริ่มเก็บเกี่ยว บวบเหลี่ยม")

      //  this.insertTimeline(12,"มะเขือเทศ", 0, "เริ่มปลูก มะเขือเทศ")
        this.insertTimeline(12,"มะเขือเทศ", 0, "ใส่ปุ๋ยครั่งที่ 1 " + "สูตร 13-13-21 อัตรา 100 กก./ไร่")
        this.insertTimeline(12,"มะเขือเทศ", 52, "ใส่ปุ๋ยครั่งที่ 2 " + "สูตร 13-13-21")
        this.insertTimeline(12,"มะเขือเทศ", 57, "ใส่ปุ๋ยครั่งที่ 3 " + "สูตร 13-13-21")
        this.insertTimeline(12,"มะเขือเทศ", 70, "เริ่มเก็บเกี่ยว มะเขือเทศ")

     //   this.insertTimeline(20,"คะน้าฮ้องกง", 0, "เริ่มปลูก คะน้าฮ้องกง")
        this.insertTimeline(20,"คะน้าฮ้องกง", 0, "ใส่ปุ๋ยครั่งที่ 1 " + "สูตร 46–0–0 หรือ 21–0–0 อัตรา 120 กก./ไร่")
        this.insertTimeline(20,"คะน้าฮ้องกง", 14, "ใส่ปุ๋ยครั่งที่ 2 " + "สูตร 21–0–0 ผสมปุ๋ยสูตร 15–15–15 อัตรา 2:1 120 กก./ไร่")
        this.insertTimeline(20,"คะน้าฮ้องกง", 35, "เริ่มเก็บเกี่ยว คะน้าฮ้องกง")

       // this.insertTimeline(38,"กระเทียม", 0, "เริ่มปลูก กระเทียม")
        this.insertTimeline(38,"กระเทียม", 0, "ใส่ปุ๋ยครั่งที่ 1 " + "สูตร 46–0–0 หรือ 21–0–0 อัตรา 120 กก./ไร่")
        this.insertTimeline(38,"กระเทียม", 90, "เริ่มเก็บเกี่ยว กระเทียม")

        var values = ContentValues()
        values.apply {
            put(ID, 0)
            put(YEAR, 0)
            put(MONTH, 0)
            put(DAY, 0)
            put(PASS,0)
        }

        this.insert(MyDB.myData, null, values)

    }



    // Insert Data โรคผัก
    fun InsertData(strVegdisName: String, strVegdisArea: String, strVegdisType: String, strVegdisCause: String,
                   strSyndrome1: String, strSyndrome2: String, strProtect: String, strRemedy: String, VegdisGallaryName: String, VegdisGallaryPath: String): Long {

        try {


            val db: SQLiteDatabase
            db = this.writableDatabase // Write Data


            val Val = ContentValues()
            Val.put("vegdis_name", strVegdisName)
            Val.put("vegdis_area", strVegdisArea)
            Val.put("vegdis_type", strVegdisType)
            Val.put("vegdis_cause", strVegdisCause)
            Val.put("vegdis_syndrome1", strSyndrome1)
            Val.put("vegdis_syndrome2", strSyndrome2)
            Val.put("vegdis_protect", strProtect)
            Val.put("vegdis_remedy", strRemedy)
            Val.put("vegdis_gallery_name", VegdisGallaryName)
            Val.put("vegdis_gallery_path", VegdisGallaryPath)

            val rows = db.insert(MyDB.vegdis_table, null, Val)

            db.close()
            return rows // return rows inserted.

        } catch (e: Exception) {
            return -1
        }


    }

    // Insert Data ผัก
    fun InsertDataVeg(VegNameTH: String, VegNameCM: String, VegNameSC: String, VegStructure: String,
                      VegNutrient: String, VegPlant: String, VegTreatment: String, VegType: String, VegGallaryName: String, VegGallaryPath: String): Long {

        try {


            val db: SQLiteDatabase
            db = this.writableDatabase // Write Data


            val Val = ContentValues()
            Val.put("veg_name_th", VegNameTH)
            Val.put("veg_name_cm", VegNameCM)
            Val.put("veg_name_sc", VegNameSC)
            Val.put("veg_structure", VegStructure)
            Val.put("veg_nutrient", VegNutrient)
            Val.put("veg_plant", VegPlant)
            Val.put("veg_treatment", VegTreatment)
            Val.put("veg_type", VegType)
            Val.put("veg_gallery_name", VegGallaryName)
            Val.put("veg_gallery_path", VegGallaryPath)

            val rows = db.insert(MyDB.veg_table, null, Val)

            db.close()
            return rows // return rows inserted.

        } catch (e: Exception) {
            return -1
        }


    }

    // Select Data โรคผัก
    fun SelectData(strVegdisID: String): Array<String>? {
        try {
            var arrData: Array<String>? = null
            val db: SQLiteDatabase
            db = this.readableDatabase // Read Data

            val cursor = db.query(MyDB.vegdis_table, arrayOf("*"),
                    "vegdis_id=?",
                    arrayOf(strVegdisID), null, null, null, null)

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        val   arrData = Array<String>(cursor.columnCount)
                        /***
                         * 0 = vegdis_id
                         * 1 = vegdis_name
                         * 2 = vegdis_area
                         * 3 = vegdis_type
                         * 4 = vegdis_cause
                         * 5 = vegdis_syndrome1
                         * 6 = vegdis_syndrome2
                         * 7 = vegdis_protect
                         * 8 = vegdis_remedy
                         * 9 = vegdis_gallery_name
                         * 10 = vegdis_gallery_path
                         */

                        arrData[0] = cursor.getString(0)
                        arrData[1] = cursor.getString(1)
                        arrData[2] = cursor.getString(2)
                        arrData[3] = cursor.getString(3)
                        arrData[4] = cursor.getString(4)
                        arrData[5] = cursor.getString(5)
                        arrData[6] = cursor.getString(6)
                        arrData[7] = cursor.getString(7)
                        arrData[8] = cursor.getString(8)
                        arrData[9] = cursor.getString(9)
                        arrData[10] = cursor.getString(10)
                    } while (cursor.moveToNext())
                }
            }

            cursor!!.close()
            db.close()
            return arrData

        } catch (e: Exception) {
            return null
        }


    }

    // Select Data ผัก
    fun SelectDataVeg(VegID: String): Array<String>? {
        try {
            var arrData: Array<String>? = null
            val db: SQLiteDatabase
            db = this.readableDatabase // Read Data

            val cursor = db.query(TABLE_VEG, arrayOf("*"),
                    "veg_id=?",
                    arrayOf(VegID), null, null, null, null)

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                       val arrData = Array<String>(cursor.columnCount)

                        arrData[0] = cursor.getString(0)
                        arrData[1] = cursor.getString(1)
                        arrData[2] = cursor.getString(2)
                        arrData[3] = cursor.getString(3)
                        arrData[4] = cursor.getString(4)
                        arrData[5] = cursor.getString(5)
                        arrData[6] = cursor.getString(6)
                        arrData[7] = cursor.getString(7)
                        arrData[8] = cursor.getString(8)
                        arrData[9] = cursor.getString(9)
                        arrData[10] = cursor.getString(10)
                    } while (cursor.moveToNext())
                }
            }

            cursor!!.close()
            db.close()
            return arrData

        } catch (e: Exception) {
            return null
        }


    }

    private fun <T> Array(size: Int): Array<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // Select All Data โรคผัก
    fun SelectAllVegDis(): java.util.ArrayList<String>? {
        // TODO Auto-generated method stub

        try {
            var arrData = java.util.ArrayList<String>()
            val db: SQLiteDatabase
            db = this.readableDatabase // Read Data

            val strSQL = "SELECT  * FROM $MyDB.vegdis_table"
            val cursor = db.rawQuery(strSQL, null)

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                 val   arrData = java.util.ArrayList<String>()
                    /***
                     * 0 = vegdis_id
                     * 1 = vegdis_name
                     * 2 = vegdis_area
                     * 3 = vegdis_type
                     * 4 = vegdis_cause
                     * 5 = vegdis_syndrome1
                     * 6 = vegdis_syndrome2
                     * 7 = vegdis_protect
                     * 8 = vegdis_remedy
                     * 9 = vegdis_gallery_name
                     * 10 = vegdis_gallery_path
                     *
                     */

                    do {
                        arrData.add(cursor.getString(0))
                        arrData.add(cursor.getString(1))
                        arrData.add(cursor.getString(2))
                        arrData.add(cursor.getString(3))
                        arrData.add(cursor.getString(4))
                        arrData.add(cursor.getString(5))
                        arrData.add(cursor.getString(6))
                        arrData.add(cursor.getString(7))
                        arrData.add(cursor.getString(8))
                        arrData.add(cursor.getString(9))
                        arrData.add(cursor.getString(10))

                    } while (cursor.moveToNext())

                }
            }
            cursor!!.close()

            return arrData

        } catch (e: Exception) {
            return null
        }

    }

    // Select All Data ปฏิทิน
    fun SelectAllVeg1(): java.util.ArrayList<String>? {
        // TODO Auto-generated method stub

        try {
            var arrData = java.util.ArrayList<String>()

            val db: SQLiteDatabase
            db = this.readableDatabase // Read Data

            val strSQL = "SELECT  * FROM $MyDB.veg_table"
            val cursor = db.rawQuery(strSQL, null)

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = java.util.ArrayList()

                    do {
                        arrData.add(cursor.getString(1))
                    } while (cursor.moveToNext())

                }
            }
            cursor!!.close()

            return arrData

        } catch (e: Exception) {
            return null
        }

    }

    // Show All Data โรค
    fun SelectAllData(): ArrayList<HashMap<String, String>>? {

        try {

            val MyArrList = ArrayList<HashMap<String, String>>()
            var map: HashMap<String, String>

            val db: SQLiteDatabase
            db = this.readableDatabase // Read Data

            val strSQL = "SELECT  * FROM $MyDB.vegdis_table"
            val cursor = db.rawQuery(strSQL, null)

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = HashMap()
                        map["vegdis_id"] = cursor.getString(0)
                        map["vegdis_name"] = cursor.getString(1)
                        map["vegdis_area"] = cursor.getString(2)
                        map["vegdis_type"] = cursor.getString(3)
                        map["vegdis_cause"] = cursor.getString(4)
                        map["vegdis_syndrome1"] = cursor.getString(5)
                        map["vegdis_syndrome2"] = cursor.getString(6)
                        map["vegdis_protect"] = cursor.getString(7)
                        map["vegdis_remedy"] = cursor.getString(8)
                        map["vegdis_gallery_name"] = cursor.getString(9)
                        map["vegdis_gallery_path"] = cursor.getString(10)

                        MyArrList.add(map)
                    } while (cursor.moveToNext())
                }
            }
            cursor!!.close()
            db.close()
            return MyArrList

        } catch (e: Exception) {
            return null
        }

    }

    // Show All Data ผัก
    fun SelectAllDataVeg(): java.util.ArrayList<HashMap<String, String>>? {

        try {

            val MyArrList = java.util.ArrayList<HashMap<String, String>>()
            var map: HashMap<String, String>

            val db: SQLiteDatabase
            db = this.readableDatabase // Read Data

            val strSQL = "SELECT  * FROM $MyDB.veg_table"
            val cursor = db.rawQuery(strSQL, null)

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = HashMap()
                        map["veg_id"] = cursor.getString(0)
                        map["veg_name_th"] = cursor.getString(1)
                        map["veg_name_cm"] = cursor.getString(2)
                        map["veg_name_sc"] = cursor.getString(3)
                        map["veg_structure"] = cursor.getString(4)
                        map["veg_nutrient"] = cursor.getString(5)
                        map["veg_plant"] = cursor.getString(6)
                        map["veg_treatment"] = cursor.getString(7)
                        map["veg_type"] = cursor.getString(8)
                        map["veg_gallery_name"] = cursor.getString(9)
                        map["veg_gallery_path"] = cursor.getString(10)
                        MyArrList.add(map)
                    } while (cursor.moveToNext())
                }
            }
            cursor!!.close()
            db.close()
            return MyArrList

        } catch (e: Exception) {
            return null
        }

    }


    // Update Data โรค
    fun UpdateData(strVegdisID: String, strVegdisName: String, strVegdisArea: String, strVegdisType: String, strVegdisCause: String,
                   strSyndrome1: String, strSyndrome2: String, strProtect: String, strRemedy: String): Long {

        try {

            val db: SQLiteDatabase
            db = this.writableDatabase // Write Data

            val Val = ContentValues()
            Val.put("vegdis_name", strVegdisName)
            Val.put("vegdis_area", strVegdisArea)
            Val.put("vegdis_type", strVegdisType)
            Val.put("vegdis_cause", strVegdisCause)
            Val.put("vegdis_syndrome1", strSyndrome1)
            Val.put("vegdis_syndrome2", strSyndrome2)
            Val.put("vegdis_protect", strProtect)
            Val.put("vegdis_remedy", strRemedy)

            val rows = db.update(MyDB.vegdis_table, Val, " vegdis_id = ?",
                    arrayOf(strVegdisID)).toLong()

            db.close()
            return rows // return rows updated.

        } catch (e: Exception) {
            return -1
        }

    }

    // Update Data ผัก
    fun UpdateDataVeg(VegID: String, VegNameTH: String, VegNameCM: String, VegNameSC: String, VegStructure: String,
                      VegNutrient: String, VegPlant: String, VegTreatment: String, VegType: String, VegGallaryName: String, VegGallaryPath: String): Long {

        try {

            val db: SQLiteDatabase
            db = this.writableDatabase // Write Data

            val Val = ContentValues()
            Val.put("veg_name_th", VegNameTH)
            Val.put("veg_name_cm", VegNameCM)
            Val.put("veg_name_sc", VegNameSC)
            Val.put("veg_structure", VegStructure)
            Val.put("veg_nutrient", VegNutrient)
            Val.put("veg_plant", VegPlant)
            Val.put("veg_treatment", VegTreatment)
            Val.put("veg_type", VegType)
            Val.put("veg_gallery_name", VegGallaryName)
            Val.put("veg_gallery_path", VegGallaryPath)

            val rows = db.update(MyDB.veg_table, Val, " veg_id = ?",
                    arrayOf(VegID)).toLong()

            db.close()
            return rows // return rows updated.

        } catch (e: Exception) {
            return -1
        }

    }

    // Delete Data โรค
    fun DeleteData(strVegdisID: String): Long {
        // TODO Auto-generated method stub

        try {

            val db: SQLiteDatabase
            db = this.writableDatabase // Write Data


            val rows = db.delete(MyDB.vegdis_table, "vegdis_id = ?",
                    arrayOf(strVegdisID)).toLong()

            db.close()
            return rows // return rows deleted.

        } catch (e: Exception) {
            return -1
        }

    }

    // Delete Data ผัก
    fun DeleteDataVeg(VegID: String): Long {
        // TODO Auto-generated method stub

        try {

            val db: SQLiteDatabase
            db = this.writableDatabase // Write Data


            val rows = db.delete(MyDB.veg_table, "veg_id = ?",
                    arrayOf(VegID)).toLong()

            db.close()
            return rows // return rows deleted.

        } catch (e: Exception) {
            return -1
        }

    }
    fun setVegatable(id: Int) {
        var data = getVEGInfo(id)
        var calendar = Calendar.getInstance()
        thisSqlite().execSQL("UPDATE $myData SET $ID = ${data.ID} , $NAME = '${data.NAME}',$YEAR = ${calendar.get(Calendar.YEAR)} ,$MONTH = ${calendar.get(Calendar.MONTH)} , $DAY = ${calendar.get(Calendar.DAY_OF_MONTH)} ,$PASS = 1")
    }

    fun setVegatable(id: Int,year:Int,month :Int,day :Int) {
        var data = getVEGInfo(id)
        thisSqlite().execSQL("UPDATE $myData SET $ID = ${data.ID} , $NAME = '${data.NAME}',$YEAR = ${year} ,$MONTH = ${month - 1} , $DAY = ${day} ,$PASS = 1")
    }

    fun setPass(pass :Boolean) {
        var data = getMyVEG()
        if(pass){
            thisSqlite().execSQL("UPDATE $myData SET $ID = ${data.ID} , $NAME = '${data.NAME}',$YEAR = ${data.YEAR} ,$MONTH = ${data.MONTH} , $DAY = ${data.DAY} ,$PASS = 1")
        }else {
            thisSqlite().execSQL("UPDATE $myData SET $ID = ${data.ID} , $NAME = '${data.NAME}',$YEAR = ${data.YEAR} ,$MONTH = ${data.MONTH} , $DAY = ${data.DAY} ,$PASS = 0")
        }
    }

    fun clear(){
        thisSqlite().execSQL("UPDATE $myData SET $ID = ${0} , $NAME = '${""}',$YEAR = ${0} ,$MONTH = ${0} , $DAY = ${0} ,$PASS = 0")
    }

    private fun SQLiteDatabase.insertTable(id: Int, type: String, name: String) {
        //this.execSQL("INSERT INTO ${TimelineSQ.resourceTableName} ($ID,$TYPE,$NAME) VALUES ($id,$type,$name)")

        val values = ContentValues()
        values.apply {
            put(ID, id)
            put(TYPE, type)
            put(NAME, name)
        }

        this.insert(MyDB.resourceTableName, null, values)
    }

    private fun SQLiteDatabase.insertTimeline(id: Int,veg_name: String, start: Int, objective: String) {
        //this.execSQL("INSERT INTO ${TimelineSQ.resourceTimeLine} ($id,$start,$stop,$objective) VALUES ($id,$start,$stop,$objective)")
        var values = ContentValues()
        values.apply {
            put(ID, id)
            put(NAME,veg_name)
            put(DATE_START, start)
            put(OBJECTIVE, objective)
        }

        this.insert(MyDB.resourceTimeLine, null, values)
    }

    fun isPass() :Boolean{
        return getMyVEG().PASS == 1
    }

    fun getMyVEG(): MyVegatable {
        return thisSqlite().rawQuery("SELECT * FROM $myData", null).getMyVegatable()
    }

    fun getVEGInfo(id: Int): Vegatable {
        //return thisSqlite().rawQuery("SELECT * FROM $TABLE_NAME", null).getAllRoom()
        return thisSqlite().rawQuery("SELECT * FROM $resourceTableName WHERE $resourceTableName.$ID = $id", null).getAllVegatable().get(0)
    }

    fun getTimeline(id: Int): ArrayList<Timeline> {
        if(isPass()){
            return thisSqlite().rawQuery("SELECT * FROM $resourceTimeLine WHERE $resourceTimeLine.$ID = $id ORDER BY $resourceTimeLine.$DATE_START ASC", null).getAllTimeline()
        } else {
            return ArrayList<Timeline>()
        }
    }

    private fun thisSqlite(): SQLiteDatabase {
        return this.writableDatabase
    }


    private fun Cursor.getAllTimeline(): ArrayList<Timeline> {
        var data = ArrayList<Timeline>()
        return try {
            this.apply {
                this.moveToFirst()
                do {
                    data.add(Timeline(this.getInt(this@MyDB.DATE_START),
                            this.getString(this@MyDB.OBJECTIVE)
                    ))
                } while (this.moveToNext())
            }
            data
        } catch (e: Exception) {
            ArrayList<Timeline>()
        }
    }


    private fun Cursor.getAllVegatable(): ArrayList<Vegatable> {
        var data = ArrayList<Vegatable>()
        return try {
            this.apply {
                this.moveToFirst()
                do {
                    data.add(Vegatable(
                            this.getString(this@MyDB.NAME),
                            this.getInt(this@MyDB.ID)
                    ))
                } while (this.moveToNext())
            }
            data
        } catch (e: Exception) {
            ArrayList<Vegatable>()
        }
    }

    private fun Cursor.getMyVegatable(): MyVegatable {
        return try {

            this.moveToFirst()

            MyVegatable(
                    this.getString(this@MyDB.NAME),
                    this.getInt(this@MyDB.ID),
                    this.getInt(this@MyDB.YEAR),
                    this.getInt(this@MyDB.MONTH),
                    this.getInt(this@MyDB.DAY),
                    this.getInt(this@MyDB.PASS)


            )

        } catch (e: Exception) {
            MyVegatable("", 0, 0, 0, 0, 0)
        }
    }

    private fun Cursor.getInt(id: String): Int {
        return this.getInt(this.getColumnIndex(id))
    }

    private fun Cursor.getString(id: String): String {
        return this.getString(this.getColumnIndex(id))
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS ${MyDB.resourceTableName}")
        db.execSQL("DROP TABLE IF EXISTS ${MyDB.resourceTimeLine}")
        db.execSQL("DROP TABLE IF EXISTS ${MyDB.veg_table}")
        db.execSQL("DROP TABLE IF EXISTS ${MyDB.vegdis_table}")
        onCreate(db)
    }

    companion object {
        var DatabaseName = "Mydb.db"
        var resourceTableName = "resource"
        var resourceTimeLine = "timeline_date"
        var veg_table = "vegetable"
        var vegdis_table = "vegatable_dis"
        var myData = "timeline_date_start"


    }

    data class Timeline(var DATE: Int, var OBJECTIVE: String)
    data class Vegatable(var NAME: String, var ID: Int)

    data class MyVegatable(var NAME: String, var ID: Int,  var YEAR: Int, var MONTH: Int, var DAY: Int,var PASS :Int)

}