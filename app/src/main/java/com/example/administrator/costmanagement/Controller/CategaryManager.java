package com.example.administrator.costmanagement.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.costmanagement.Model.Extra_Cost;


import java.util.ArrayList;

/**
 * Created by Niloy Chowdhury on 2017-08-31.
 */

public class CategaryManager {
    private Extra_Cost extraCost;
private DatabaseHelper helper;
    public SQLiteDatabase database;

    public void open()
    {
        database=helper.getWritableDatabase();
    }
    public void close()
    {
        helper.close();
    }
    public  CategaryManager(Context context)
    {
        helper=new DatabaseHelper(context);
    }
    public boolean addCatagery(Extra_Cost aExtraCost) {
        this.open();

        ContentValues contentvalue=new ContentValues();
        contentvalue.put(DatabaseHelper.EXTRA_FIELD_NAME, aExtraCost.getExtraCostPurposeName());
        contentvalue.put(DatabaseHelper.EXTRA_COST, aExtraCost.getExtraCostPurposeAmount());
        contentvalue.put(helper.CREATED_AT,System.currentTimeMillis());
       long insert= database.insert(DatabaseHelper.TABLE_NAME_EXTRA_FIELD,null,contentvalue);
        this.close();
        if (insert>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public ArrayList<Extra_Cost> getAllCategary() {
        ArrayList<Extra_Cost>categaries=new ArrayList<>();
        this.open();
        Cursor cursor=database.query(DatabaseHelper.TABLE_NAME_EXTRA_FIELD,null,null,null,null,null,null);
        if (cursor!=null&&cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                int _id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.EXTRA_FIELD_ID));
                String name=cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXTRA_FIELD_NAME));
                String extraCostAmount=cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXTRA_COST));
                String dateTime=cursor.getString(cursor.getColumnIndex(DatabaseHelper.CREATED_AT));
                extraCost =new Extra_Cost(_id,name,Double.valueOf(extraCostAmount),dateTime);
                categaries.add(extraCost);
            }
        }



        this.close();
return categaries;

    }
    public String getCategoryNameById(int id){
        Extra_Cost extraCost = getSelectedCategory(id);
        String name = extraCost.getExtraCostPurposeName();
        return  name;
    }

    public Extra_Cost getSelectedCategory(int recivedCategoryID) {



            this.open();
            Cursor cursor=database.query(helper.TABLE_NAME_EXTRA_FIELD,null,helper.EXTRA_FIELD_ID + " = " + recivedCategoryID,null,null,null,null);
            if (cursor!=null&&cursor.getCount()>0)
            {
                cursor.moveToFirst();
                int _id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.EXTRA_FIELD_ID));
                String name=cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXTRA_FIELD_NAME));
                String extraCostAmount=cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXTRA_COST));
                String dateTime=cursor.getString(cursor.getColumnIndex(DatabaseHelper.CREATED_AT));
                extraCost =new Extra_Cost(_id,name,Double.valueOf(extraCostAmount),dateTime);
                extraCost =new Extra_Cost(_id,name,Double.valueOf(extraCostAmount),dateTime);
            }
            this.close();


            return extraCost;

    }

    public boolean updateStudent(Extra_Cost aExtraCost, int recivedCategoryID) {

        this.open();

        ContentValues contentVales=new ContentValues();
                contentVales.put(helper.EXTRA_FIELD_NAME, aExtraCost.getExtraCostPurposeName());
        contentVales.put(DatabaseHelper.EXTRA_COST, aExtraCost.getExtraCostPurposeAmount());
       long update= database.update(helper.TABLE_NAME_EXTRA_FIELD,contentVales,helper.EXTRA_FIELD_ID + "=" +recivedCategoryID,null);
        this.close();
        if (update>0)
        {
            return true;
        }
        else
        {
            return false;
        }




    }

    public boolean deleteCategory(int recivedCategoryID) {

        this.open();
        int deleted=0;
        deleted=database.delete(helper.TABLE_NAME_EXTRA_FIELD,helper.EXTRA_FIELD_ID+ "=" + recivedCategoryID,null);
        this.close();
        if (deleted>0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public ArrayList<Extra_Cost> getAllExtaCost(int days) {
        this.open();
        long milis = days *86400000;
        long oldMillis = System.currentTimeMillis()-milis;

        ArrayList<Extra_Cost>costs=new ArrayList<>();
        Cursor cursor=database.query(DatabaseHelper.TABLE_NAME_EXTRA_FIELD,null,DatabaseHelper.CREATED_AT + " BETWEEN " + oldMillis + " AND "
                + System.currentTimeMillis() ,null,null,null,null);
        if (cursor!=null&&cursor.getCount()>0)
        {

            while (cursor.moveToNext())
            {
                int _id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.EXTRA_FIELD_ID));
                String name=cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXTRA_FIELD_NAME));
                String extraCostAmount=cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXTRA_COST));
                String dateTime=cursor.getString(cursor.getColumnIndex(DatabaseHelper.CREATED_AT));
                extraCost =new Extra_Cost(_id,name,Double.valueOf(extraCostAmount),dateTime);
                costs.add(extraCost);
            }
        }



        this.close();
        return costs;

    }
}
