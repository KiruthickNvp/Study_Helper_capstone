package com.kiruthicknvp.studyhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(MainActivity context) {
        super(context, "Userdata.db", null, 1);
    }

    public DBHelper(SignUp context) {
        super(context, "Userdata.db", null, 1);
    }

    public DBHelper(ForgotPassword context) {
        super(context, "Userdata.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table SH_UserDetails(Name VARCHAR PRIMARY KEY,Pass VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists SH_UserDetails");
    }

    public Boolean InsertTable(String Name, String Pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", Name);
        values.put("Pass", Pass);
        long Result = db.insert("SH_UserDetails", null, values);
        if (Result == -1)
            return false;
        else
            return true;
    }

    public Boolean UpdateTable(String Name, String Pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", Name);
        values.put("Pass", Pass);
        Cursor cur = db.rawQuery("Select * from SH_UserDetails where Name = ?", new String[]{Name});
        if (cur.getCount() > 0) {
            long Result = db.update("SH_UserDetails", values, "Name=?", new String[]{Name});
            if (Result == -1)
                return false;
            else
                return true;
        } else {
            return false;
        }

    }

    public Boolean DeleteTable(String Name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("Select * from SH_UserDetails where Name = ?", new String[]{Name});
        if (cur.getCount() > 0) {
            long Result = db.delete("SH_UserDetails", "Name=?", new String[]{Name});
            if (Result == -1)
                return false;
            else
                return true;
        } else {
            return false;
        }

    }

    public boolean GetData(String usrname, String usrpass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("Select * from SH_UserDetails Where Name = ?" ,  new String[]{usrname});
        if (cur.getCount() == 0) {
            return false;
        } else {
            while(cur.isAfterLast() == false){
                StringBuffer buf = new StringBuffer();
                cur.moveToFirst();
                String dbPass =cur.getString(1);
                if(dbPass.equals(usrpass)){
                    return true;
                }
                else{
                    return false;
                }

            }


        }
        return false;

    }


}
