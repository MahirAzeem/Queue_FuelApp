package com.example.fuel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "users.db";
    public DBHelper(Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase users) {
        String query = "create Table users(email TEXT primary key, password TEXT)";

        users.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase users, int i, int i1) {
        users.execSQL("drop Table if exists users");
    }

    //    Insert Station Owner Data
    public Boolean insertStationOwner(String email, String password){

        SQLiteDatabase users = this.getWritableDatabase();

//      Create Station Owner Table
        ContentValues contentValues= new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);

        long insertStationOwner = users.insert("users", null, contentValues);
        if(insertStationOwner==-1 ) return false;
        else
            return true;
    }

    //    Insert User Data
    public Boolean insertUser(String email, String password){

        SQLiteDatabase users = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("email", email);
        contentValues1.put("password", password);
        long insertUser = users.insert("users", null, contentValues1);
        if(insertUser==-1) return false;
        else
            return true;
    }


    //    Check for existence of station owner email
    public Boolean checkUserName(String email) {
        SQLiteDatabase users = this.getWritableDatabase();
        Cursor cursorSO = users.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursorSO.getCount() > 0)
            return true;
        else
            return false;

    }

//    //    Check for existence of user email
//    public Boolean checkUser_UserName(String email) {
//        SQLiteDatabase users = this.getWritableDatabase();
//        Cursor cursorUsers = users.rawQuery("Select * from queueUsers where email = ?", new String[]{email});
//        if (cursorUsers.getCount() > 0)
//            return true;
//        else
//            return false;
//    }

//    //    Check for existence of station owner account
//    public Boolean checkExistingStationOwner(String email, String password){
//        SQLiteDatabase users = this.getWritableDatabase();
//        Cursor cursorSO = users.rawQuery("Select * from stationOwners where email = ? and password = ?", new String[] {email,password});
//        if(cursorSO.getCount()>0)
//            return true;
//        else
//            return false;
//    }

    //    Check for existence of user account
    public Boolean checkExistingUser(String email, String password){
        SQLiteDatabase users = this.getWritableDatabase();
        Cursor cursor = users.rawQuery("Select * from users where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
