package com.sophie.travelagent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Created by Sophie Tan on 12/10/2020.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION=2;
    public static final String  DB_NAME="TravelExpertsSqlLite.db";
    public static final String TABLE_NAME="agents";



    public static final String CREATE_TABLE= "CREATE TABLE " + TABLE_NAME + " ("+
            "'AgentId' "+ "INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "'AgtFirstName' "+"TEXT NOT NULL, "+
            "'AgtMiddleInitial' TEXT, 'AgtLastName'	TEXT NOT NULL, "+
            "'AgtBusPhone' TEXT NOT NULL, "+
            "'AgtEmail' TEXT NOT NULL"+" )";
    public static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;


    //constructor
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);

    }


    //override methods
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Sophie","Creating database");
        db.execSQL(CREATE_TABLE);

        //for test
        String sql = "insert into agents values" +
                " (1,'sophie','s.t','tan','4089999999','sophie@sait.ca')";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Sophie","Upgrading database,old version: " +oldVersion+ " new version "+
                newVersion);
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }
}
