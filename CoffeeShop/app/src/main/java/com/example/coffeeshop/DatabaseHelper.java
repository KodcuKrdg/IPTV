package com.example.coffeeshop;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myCoffee.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USER_CREATE =
            "CREATE TABLE " + TablesInfo.UserEntry.TABLE_NAME + " (" +
                    TablesInfo.UserEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TablesInfo.UserEntry.COLUMN_EMAIL + " TEXT, " +
                    TablesInfo.UserEntry.COLUMN_PASSWORD + " TEXT" +
                    ")";

    private static final String TABLE_Order_CREATE =
            "CREATE TABLE " + TablesInfo.OrderEntry.TABLE_NAME + " (" +
                    TablesInfo.OrderEntry.Order_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TablesInfo.OrderEntry.USER_ID + " TEXT, " +
                    TablesInfo.OrderEntry.PRODUCT_ID + " TEXT," +
                    TablesInfo.OrderEntry.PRODUCT_MONEY + " TEXT," +
                    TablesInfo.OrderEntry.NUMBER + " TEXT," +
                    TablesInfo.OrderEntry.TOTAL_MONEY + " TEXT" +
                    ")";

    private static final String TABLE_SEND_ORDER_CREATE =
            "CREATE TABLE " + TablesInfo.SendOrderEntry.TABLE_NAME + " (" +
                    TablesInfo.SendOrderEntry.SENDORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TablesInfo.SendOrderEntry.USER_ID + " TEXT, " +
                    TablesInfo.SendOrderEntry.PRODUCT_ID + " TEXT," +
                    TablesInfo.SendOrderEntry.PRODUCT_MONEY + " TEXT," +
                    TablesInfo.SendOrderEntry.NUMBER + " TEXT," +
                    TablesInfo.SendOrderEntry.TOTAL_MONEY + " TEXT" +
                    ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER_CREATE);
        db.execSQL(TABLE_Order_CREATE);
        db.execSQL(TABLE_SEND_ORDER_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TablesInfo.UserEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TablesInfo.OrderEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TablesInfo.SendOrderEntry.TABLE_NAME);
    }

    public void AddUser(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TablesInfo.UserEntry.COLUMN_EMAIL, email.trim());
        cv.put(TablesInfo.UserEntry.COLUMN_PASSWORD, password.trim());

        long result = db.insert(TablesInfo.UserEntry.TABLE_NAME, null, cv);

        if (result > -1)
            Log.i("DatabaseHelper", "Kullanıcı Başarı ile kaydedildi");
        else
            Log.i("DatabaseHelper", "Kullanıcı kaydedilemedi");
    }

    public boolean Login(String email, String password){

        String[] columns = {"email"};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "email=? and password=?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TablesInfo.UserEntry.TABLE_NAME, columns, selection,selectionArgs,null,null,null);

        int count = cursor.getCount();

        cursor.close();
        db.close();

        if (count >0)
            return true;
        else
            return false;

    }

    public void AddOrder(Product product) throws InterruptedException {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TablesInfo.OrderEntry.USER_ID, product.getUserId());
        cv.put(TablesInfo.OrderEntry.PRODUCT_ID, product.getProductId());
        cv.put(TablesInfo.OrderEntry.PRODUCT_MONEY, product.getProductMoney());
        cv.put(TablesInfo.OrderEntry.NUMBER, product.getNumber());
        cv.put(TablesInfo.OrderEntry.TOTAL_MONEY, product.getTotalMoney());

        long result = db.insert(TablesInfo.OrderEntry.TABLE_NAME, null,cv);

        if (result > -1)
            Log.i("DatabaseHelper", "Sepete Ürün Başarı ile kaydedildi");
        else
            Log.i("DatabaseHelper", "Seğete ürün EKLENEMEDİ!!!");

        Thread.sleep(100);
    }

    public void DeleteOrders(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TablesInfo.OrderEntry.TABLE_NAME,null,null);

    }

    //Sepetteki ürünleri almak için

    public Product[] GetProducts(){

        Product[] products = new Product[3];
        products[0] = new Product();
        products[1] = new Product();
        products[2] = new Product();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                TablesInfo.OrderEntry.Order_ID,
                TablesInfo.OrderEntry.USER_ID,
                TablesInfo.OrderEntry.PRODUCT_ID,
                TablesInfo.OrderEntry.PRODUCT_MONEY,
                TablesInfo.OrderEntry.NUMBER,
                TablesInfo.OrderEntry.TOTAL_MONEY,
        };

        Cursor cursor = db.query(TablesInfo.OrderEntry.TABLE_NAME, projection,null,null,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                products[Integer.parseInt(cursor.getString(2))] = new Product(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));

            }
        }



        return products;
    }

    public void SendOrders() throws InterruptedException {

        SQLiteDatabase db = this.getWritableDatabase();

        for(Product product : GetProducts()){


            ContentValues cv = new ContentValues();

            cv.put(TablesInfo.SendOrderEntry.USER_ID, product.getUserId());
            cv.put(TablesInfo.SendOrderEntry.PRODUCT_ID, product.getProductId());
            cv.put(TablesInfo.SendOrderEntry.PRODUCT_MONEY, product.getProductMoney());
            cv.put(TablesInfo.SendOrderEntry.NUMBER, product.getNumber());
            cv.put(TablesInfo.SendOrderEntry.TOTAL_MONEY, product.getTotalMoney());

            long result = db.insert(TablesInfo.SendOrderEntry.TABLE_NAME, null, cv);

            if (result > -1)
                Log.i("SendOrder", "Sipariş Başarılı");
            else
                Log.i("SendOrder", "Sipraiş HATALI!!!!!");

            Thread.sleep(100);
        }
    }


}
