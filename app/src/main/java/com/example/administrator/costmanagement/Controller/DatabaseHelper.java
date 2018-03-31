package com.example.administrator.costmanagement.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Niloy Chowdhury on 2017-08-31.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
static final String DATABASE_NAME="CostManagement.db";
    static final int DATABASE_VERSION=1;
    static final String TABLE_NAME_EXTRA_FIELD="costCategary";
    static final String TABLE_NAME_COSTINPUT="costInput";
    static final String EXTRA_FIELD_ID="EFid";
    static final String COST_INPUT_ID="costInputID";

    static final String MOBILE_COST_AMOUNT="mAmount";
    static final String TRANSPORT_COST_AMOUNT="tAmount";
    static final String FOOD_COST_AMOUNT="fAmount";
    static final String ENTERTAINMENT_COST_AMOUNT="eAmount";
    static final String STATUS="status";
    static final String EXTRA_FIELD_NAME="eFName";
    static final String EXTRA_COST="extraCost";
    static final String CREATED_AT="create_at";

    String CREATE_TABLE_EXTRA_FIELD="CREATE TABLE " + TABLE_NAME_EXTRA_FIELD + " ( "
            + EXTRA_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EXTRA_FIELD_NAME + " TEXT, "
            + EXTRA_COST + " TEXT, "
            + CREATED_AT + " TEXT)";
    String CREATE_TABLE_COSTINPUT="CREATE TABLE " + TABLE_NAME_COSTINPUT + " ( "
            + COST_INPUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MOBILE_COST_AMOUNT + " TEXT, "
            + TRANSPORT_COST_AMOUNT + " TEXT, "
            + FOOD_COST_AMOUNT + " TEXT, "
            + ENTERTAINMENT_COST_AMOUNT + " TEXT, "
            + CREATED_AT + " TEXT)";


           // + CATEGARY_ID + " INTEGER REFERENCES " + TABLE_NAME_CATEGARY + ")";
            //+ " FOREIGN KEY ("+CATEGARY_ID+") REFERENCES "+TABLE_NAME_CATEGARY+"("+CATEGARY_ID+"));";
           // + " FOREIGN KEY ("+CATEGARY_ID+") REFERENCES "+TABLE_NAME_CATEGARY+"("+CATEGARY_ID+"));";
    //TASK_CAT+" INTEGER REFERENCES "+CAT_TABLE+");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EXTRA_FIELD);
        db.execSQL(CREATE_TABLE_COSTINPUT);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
