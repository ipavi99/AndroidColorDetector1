package com.example.pavithran.colourdetector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "sampledb.db";
    public static final String TABLE_NAME = "sampletable";
    public static final String SAMPLENAME = "SampleName";
    public static final String S_COLOUR1 = "Colour1";
    public static final String S_COLOUR2 = "Colour2";
    public static final String STATUS = "Status";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);


    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL("create table "+ TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                SAMPLENAME+" TEXT, "+
                S_COLOUR1+" INTEGER, "+
                S_COLOUR2+" INTEGER, "+
                STATUS+" INTEGER "+
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String name, int c1,int c2, boolean status){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SAMPLENAME,name);
        contentValues.put(S_COLOUR1,c1);
        contentValues.put(S_COLOUR2,c2);


        if(status == true)
            contentValues.put(STATUS,1);
        else
            contentValues.put(STATUS,0);

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return  true;
    }

    public Cursor getAllData(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
                return res;

    }

    public void droptab(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_NAME);
    }


}
