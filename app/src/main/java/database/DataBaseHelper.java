package database;

/**
 * Created by DELL on 10-02-2016.
 */
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import constant.Constant;
import data.Time;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String SAMPLE_DB_NAME = Environment.getExternalStorageDirectory()+ File.separator+ Constant.rootFolderName
            +File.separator+Constant.databaseFolder+File.separator+"hanuapp";
    public static  final String SAMPLE_TABLE_NAME = "time";
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "place";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    private HashMap hp;
    private Context context;

    public DataBaseHelper(Context context)
    {
        super(context, SAMPLE_DB_NAME , null, 1);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        try{
            File path = context.getDatabasePath(SAMPLE_DB_NAME);
            db = SQLiteDatabase.openOrCreateDatabase(path, null);
        }

        catch(SQLiteException e){
            Log.e("Error", "" + e.getMessage());
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + SAMPLE_TABLE_NAME + "( " +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "PARENT_ID VARCHAR, " +
                        "ID VARCHAR, " +
                        "ALARM_TITLE, " +
                        "ALARM_AM_PM, " +
                        "WEEK_DAY VARCHAR, " +
                        "HOUR VARCHAR, " +
                        "MINUTE VARCHAR )"
        );



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS time");
        onCreate(db);
    }

    public boolean insertTime(Time time)
    {

        try{


            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("WEEK_DAY", time.getDay());
            if(time.getHours().length()==1){

                String newTimeHour = "0"+time.getHours();
                contentValues.put("HOUR", newTimeHour);
                int hourOfDay = Integer.parseInt(newTimeHour);
                String am_pm = (hourOfDay < 12) ? "AM" : "PM";
                contentValues.put("ALARM_AM_PM", am_pm);
            }else{

                contentValues.put("HOUR", time.getHours());
                int hourOfDay = Integer.parseInt(time.getHours());
                String am_pm = (hourOfDay < 12) ? "AM" : "PM";
                contentValues.put("ALARM_AM_PM", am_pm);
            }

            if(time.getMinutes().length()==1){

                String newTimeMinutes = "0"+time.getMinutes();
                contentValues.put("MINUTE", newTimeMinutes);
            }else{

                contentValues.put("MINUTE", time.getMinutes());
            }



            contentValues.put("ALARM_TITLE",time.getAlarmTitle());
            contentValues.put("ID",time.getId());
            contentValues.put("PARENT_ID",time.getParentId());
            db.insert("time", null, contentValues);

        }catch (Exception e){

            e.printStackTrace();
        }
        return true;
    }

    public Time getData(String day){
        Time time = null;
        SQLiteDatabase db = this.getReadableDatabase();

        try{


            Cursor cursor =  db.rawQuery("select * from time where WEEK_DAY=" + day + "", null);
            cursor.moveToFirst();

            while(cursor.isAfterLast() == false){
                String hours = cursor.getString(cursor.getColumnIndex("HOUR"));
                String minutes = cursor.getString(cursor.getColumnIndex("MINUTE"));
                String alarm_title = cursor.getString(cursor.getColumnIndex("ALARM_TITLE"));
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID")));
                String parent_id = cursor.getString(cursor.getColumnIndex("PARENT_ID"));

                time = new Time(day,hours,minutes,alarm_title,id,parent_id);
                cursor.moveToNext();
            }

        }catch (Exception e){


        }


        return time;
    }

    public ArrayList<Time> getDataAccordingToTile(String day){
        Time time = null;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Time> timeArrayList = new ArrayList<>();

        try{


            Cursor cursor =  db.rawQuery("select * from time where PARENT_ID = '" +  day + "'", null);
            cursor.moveToFirst();

            while(cursor.isAfterLast() == false){
                String hours = cursor.getString(cursor.getColumnIndex("HOUR"));
                String minutes = cursor.getString(cursor.getColumnIndex("MINUTE"));
                String alarm_title = cursor.getString(cursor.getColumnIndex("ALARM_TITLE"));
                String week_day =  cursor.getString(cursor.getColumnIndex("WEEK_DAY"));
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));
                String parent_id = cursor.getString(cursor.getColumnIndex("PARENT_ID"));

                time = new Time(day,hours,minutes,alarm_title,week_day,id,parent_id);
                timeArrayList.add(time);
                cursor.moveToNext();
            }

        }catch (Exception e){

            e.printStackTrace();

        }


        return timeArrayList;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (String title)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("time",
                "PARENT_ID = ? ",
                new String[] { title });
    }

    public HashMap<String,String> getAllCotacts()
    {
        HashMap<String,String> timeHashMap = new HashMap<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from time", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String hours = res.getString(res.getColumnIndex("HOUR"));
            String minutes = res.getString(res.getColumnIndex("MINUTE"));
            String alarm_title = res.getString(res.getColumnIndex("ALARM_TITLE"));
            String day = res.getString(res.getColumnIndex("WEEK_DAY"));
            String am_pm = res.getString(res.getColumnIndex("ALARM_AM_PM"));
            int id = Integer.parseInt(res.getString(res.getColumnIndex("ID")));
            String parent_id = res.getString(res.getColumnIndex("PARENT_ID"));


            Time time = new Time(day,hours,minutes,alarm_title,id,parent_id);
            timeHashMap.put(parent_id,hours+" : " + minutes + " " + am_pm+"-"+alarm_title);
            res.moveToNext();
        }
        return timeHashMap;
    }
}
