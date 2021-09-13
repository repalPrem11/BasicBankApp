package com.example.basicbankapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private String TABLE_NAME = "userInformation";
    private String TABLE_NAME1 = "transferInformation";

    public Database(@Nullable Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY ,NAME TEXT,PHONE_NO VARCHAR, EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR,BALANCE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,TRANSACTION_ID VARCHAR, DATE VARCHAR,SENDER_ID VARCHAR,RECEIVER_ID VARCHAR,AMOUNT VARCHAR,STATUS TEXT)");

        db.execSQL("insert into userInformation values(1, 'John', 'john@gmail.com','9513574621', 'XXXXXX0001', 'MKLG4215','9000.00')");
        db.execSQL("insert into userInformation values(2, 'Shawn', 'shawn@gmail.com', '9632587412','XXXXXX0031', 'PSRS5123','5000.65')");
        db.execSQL("insert into userInformation values(3, 'Mike', 'mike@gmail.com', '1478523695','XXXXXX0032', 'LEMK85236','3000.30')");
        db.execSQL("insert into userInformation values(4, 'Rory',' max@gmail.com', '2589631478','XXXXXX0033', 'TRHU1245','4000.00')");
        db.execSQL("insert into userInformation values(5, 'Zed', 'zed@gmail.com', '6541239874','XXXXXX0044', 'YEGD8745','6000.85')");
        db.execSQL("insert into userInformation values(6, 'Moe', 'moe@gmail.com', '3216549870','XXXXXX0065', 'MKLG4215','5000.00')");
        db.execSQL("insert into userInformation values(7, 'Alex', 'alex@gmail.com', '1239516478','XXXXXX0096', 'TRHU1245','12000.00')");
        db.execSQL("insert into userInformation values(8, 'Imma', 'imma@gmail.com','7539514268', 'XXXXXX0087', 'BHID3216','1000.65')");
        db.execSQL("insert into userInformation values(9, 'Angel', 'angel@gmail.com', '8529473627','XXXXXX0028', 'PSRS5123','3000.48')");
        db.execSQL("insert into userInformation values(10, 'Lily', 'lily@gmail.com', '3519574628','XXXXXX0069', 'KTVP1234','6000.45')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from userInformation", null);
        return cursor;
    }

    public Cursor readparticulardata(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from userInformation where id = " +id, null);
        return cursor;
    }

    public Cursor readselectuserdata(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from userInformation except select * from userInformation where id = " +id, null);
        return cursor;
    }

    public void updateAmount(String id, Double amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update userInformation set balance = " + amount + " where id = " +id);
    }

    public Cursor readtransferdata(String transactionId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transferInformation where TRANSACTION_ID = " + transactionId, null);
        return cursor;
    }


    public Cursor readperticulartransferdata(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transferInformation where SENDER_ID = " + id +" OR RECEIVER_ID = "+ id +" ORDER BY id DESC ", null);
        return cursor;
    }


    public boolean insertTransferData(String Transaction_id, String date, String SenderID, String ReceiverID, String amount, String status){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TRANSACTION_ID", Transaction_id);
        contentValues.put("DATE", date);
        contentValues.put("SENDER_ID", SenderID);
        contentValues.put("RECEIVER_ID", ReceiverID);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1,null,  contentValues );
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
