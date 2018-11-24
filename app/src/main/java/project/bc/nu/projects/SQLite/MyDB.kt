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