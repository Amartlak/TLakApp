package com.watconsult.tlakapp.ui.Itineary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ItinearyDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Itineary.db";
    public static final String ITINEARY_TABLE_NAME = "itineary";
    public static final String ITINEARY_ID = "itinearyId";
    public static final String ITINEARY_DAYNUMBER = "dayNumber";
    public static final String ITINEARY_DAYHEADING = "dayHeading";
    public static final String ITINEARY_DESCRIPTION = "description";
    public static final String ITINEARY_IMAGE = "itinearyImage";
    public static final String ITINEARY_PKGNAME = "pkgName";
    public static final String ITINEARY_COMPANYNAME = "companyName";
    public static final String ITINEARY_TOTALDAYS = "totalDays";
    public static final String ITINEARY_TOTALNIGHT = "totalNight";
    public ItinearyDatabase(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table itineary " +
                        "(itinearyId integer primary key, dayNumber text,dayHeading text,description text, itinearyImage text,pkgName text,companyName text,totalDays text, totalNight text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS itineary");
        onCreate(db);
    }
    public boolean insertItineary (String dayNumber, String dayHeading, String description, String itinearyImage,String pkgName,String companyName,String totalDays,String totalNight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dayNumber", dayNumber);
        contentValues.put("dayHeading", dayHeading);
        contentValues.put("description", description);
        contentValues.put("itinearyImage", itinearyImage);
        contentValues.put("pkgName", pkgName);
        contentValues.put("companyName", companyName);
        contentValues.put("totalDays", totalDays);
        contentValues.put("totalNight", totalNight);
        db.insert("itineary", null, contentValues);
        return true;
    }
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from itineary where itinearyId="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, ITINEARY_TABLE_NAME);
        return numRows;
    }
    public boolean updateItineary (Integer itinearyId, String dayNumber, String dayHeading, String description, String itinearyImage,String pkgName,String companyName,String totalDays,String totalNight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dayNumber", dayNumber);
        contentValues.put("dayHeading", dayHeading);
        contentValues.put("description", description);
        contentValues.put("itinearyImage", itinearyImage);
        contentValues.put("pkgName", pkgName);
        contentValues.put("companyName", companyName);
        contentValues.put("totalDays", totalDays);
        contentValues.put("totalNight", totalNight);
        db.update("contacts", contentValues, "itinearyId = ? ", new String[] { Integer.toString(itinearyId) } );
        return true;
    }
    public ArrayList<String> getAllItineary() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from itineary", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(ITINEARY_ID)));
            res.moveToNext();
        }
        return array_list;
    }
}
