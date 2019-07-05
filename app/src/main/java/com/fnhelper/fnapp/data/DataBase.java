package com.fnhelper.fnapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 基础数据存储
 * Created by guoyu on 15/5/19.
 */
public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS pddetail (id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "pddtailid TEXT,pdtName TEXT,buyerName TEXT,buyerAddress TEXT,initTime TEXT,rcvTime TEXT,"
                + "dlrName TEXT,dlrAddress TEXT,dlrTime TEXT,dlrPerson TEXT,carNo TEXT,"
                + "sourceType TEXT,sourceName TEXT,sourceArea TEXT,sourceAddress TEXT,marketName TEXT,"
                + "examType TEXT,examPro TEXT,stanValue TEXT,meaValue TEXT,examRes TEXT,"
                + "examTime TEXT,examPerson TEXT,examCorp TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

}
