package com.example.douglaslist_sellItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "SellingItems.db";
    final static int DATABASE_VERSION = 1;
    final static String TABLE1_NAME = "Items";
    final static String T1COL1 = "UserId";// primary key, should be unique
    final static String T1COL2 = "ItemName"; //title
    final static String T1COL3 =  "ItemId";// should be unique, not assigned by userinput
    final static String T1COL4 = "Price";
    final static String T1COL5 = "ItemInfo";//ItemDescription



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE1_NAME + "(" +
                T1COL1 + " INTEGER PRIMARY KEY," +//UserId
                T1COL2 + " TEXT," + //ItemName
                T1COL3 + " INTEGER PRIMARY KEY AUTOINCREMENT," + //ItemId
                T1COL4 + " REAL," + //this is double value
                T1COL5 + " TEXT" + //ItemInfo
                ")";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean post(String itemName,double price,String inFo){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL2,itemName);
        values.put(T1COL4,price);
        values.put(T1COL5,inFo);


        long r = sqLiteDatabase.insert(TABLE1_NAME,null,values);
        if(r>0)
            return true;
        else
            return false;

    }


}
