package com.example.app_cdc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="patient1.db";
    public static final String TABLE_NAME="patients_table1";


    public static final String COL_1P="ID";
    public static final String COL_2P="first_NAME";
    public static final String COL_3P="last_NAME";
    public static final String COL_4P="gender";
    public static final String COL_5P="level";
    //..........................................
    public static final String TABLE_NAME2="Regtable";


    public static final String COL_1D="ID_D";
    public static final String COL_2D="ID";
    public static final String COL_3D="Desired_Dose_Unit";
    public static final String COL_4D="Unit";
    public static final String COL_5D="Name_Of_Dose";
    public static final String COL_6D="Date";






    public MyDataBase(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME+"(ID INTEGER PRIMARY KEY, first_NAME TEXT ,last_NAME TEXT,gender TEXT ,level TEXT );");
        db.execSQL("create table "+ TABLE_NAME2+"(ID_D INTEGER PRIMARY KEY  AUTOINCREMENT, ID INTEGER references patients_table (COL_1P),Desired_Dose_Unit TEXT,Unit Text,Name_Of_Dose TEXT ,Date TEXT );");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
        onCreate(db);

    }



    public boolean insertData(int ID ,String first_NAME ,String last_NAME , String gender,String level  ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1P,ID);
        contentValues.put(COL_2P,first_NAME);
        contentValues.put(COL_3P,last_NAME);
        contentValues.put(COL_4P,gender);
        contentValues.put(COL_5P,level);



        long result = db.insert(TABLE_NAME,null,contentValues );
        if(result ==-1)
            return false;
        else
            return true;


    }







    public boolean insertData2(int ID , String Desired_Dose_Unit , String Unit , String Name_Of_Dose , String Date  ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2D,ID);
        contentValues.put(COL_3D,Desired_Dose_Unit);
        contentValues.put(COL_4D,Unit);
        contentValues.put(COL_5D,Name_Of_Dose);
        contentValues.put(COL_6D,Date);



        long result = db.insert(TABLE_NAME2,null,contentValues);
        if(result ==-1)
            return false;
        else
            return true;


    }




    ////////////////////////////////////
    public Integer deleteData1 (String ID){
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NAME2,"ID=?",new String[]{ID});
    }
    /////////////////////////////
    public Integer deleteData2 (String ID){
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NAME2,"ID_D=?",new String[]{ID});
    }
    ///////////////////////////////










    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }




    public Cursor getAllData2(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME2,null);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }




    public Cursor search1(String ID){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME2 + " where " + COL_2D + " = ?", new String[] {ID});

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;


    }



    public Cursor search2(String ID){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME+ " where " + COL_1P + " = ?", new String[] {ID});

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;


    }



    public boolean UpdateData1( String ID ,String first_NAME ,String last_NAME , String gender,String level) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2P,first_NAME);
        contentValues.put(COL_3P,last_NAME);
        contentValues.put(COL_4P,gender);
        contentValues.put(COL_5P,level);
        db.update(TABLE_NAME, contentValues, "ID = ? ", new String[]{ID});
        return true;
    }

}







