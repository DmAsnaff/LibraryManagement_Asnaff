package com.example.librarymanagement_asnaff;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import kotlin.text.UStringsKt;

public class DBHandler extends SQLiteOpenHelper {
    public Context context;

    public static final String databaseName = "SignLog.db";
//    public DBHandler(@Nullable Context context) {
//        super(context, "SignLog.db", null, 1);
//    }

    DBHandler(@Nullable Context context) {
        super(context, "SignLog.db", null, 1);
    }

    public static final String BOOKTABLE_NAME = "my_library";
    public static final String BOOKCOLUMN_ID = "book_id";
    public static final String BOOKCOLUMN_TITLE = "book_title";
    public static final String BOOKCOLUMN_AUTHOR = "book_author";
    public static final String BOOKCOLUMN_PUBLISHER = "book_publisher";

    public static final String PUBLISHERTABLE_NAME = "my_publisher";
    public static final String PUBLISHERCOLUMN_ID = "publisher_id";
    public static final String PUBLISHERCOLUMN_NAME = "publisher_name";
    public static final String PUBLISHERCOLUMN_ADDRESS = "publisher_address";
    public static final String PUBLISHERCOLUMN_PHONE = "book_phone";
    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(fullname TEXT,email TEXT primary key, nic TEXT, password TEXT)");

        String query = "CREATE TABLE " + BOOKTABLE_NAME +
                " (" + BOOKCOLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BOOKCOLUMN_TITLE + " TEXT, " +
                BOOKCOLUMN_AUTHOR + " TEXT, " +
                BOOKCOLUMN_PUBLISHER + " TEXT);";
        MyDatabase.execSQL(query);

        String querypub= "CREATE TABLE " + PUBLISHERTABLE_NAME +
                " (" + PUBLISHERCOLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PUBLISHERCOLUMN_NAME + " TEXT, " +
                PUBLISHERCOLUMN_ADDRESS + " TEXT, " +
                PUBLISHERCOLUMN_PHONE + " TEXT);";
        MyDatabase.execSQL(querypub);
    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop Table if exists users");

        MyDatabase.execSQL("DROP TABLE IF EXISTS " + BOOKTABLE_NAME);
        onCreate(MyDatabase);

        MyDatabase.execSQL("DROP TABLE IF EXISTS " + PUBLISHERTABLE_NAME);
        onCreate(MyDatabase);
    }
    public boolean addBookdetails(String title, String author, String publisher) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(BOOKCOLUMN_TITLE, title);
        cv.put(BOOKCOLUMN_AUTHOR, author);
        cv.put(BOOKCOLUMN_PUBLISHER, publisher);
        long result = MyDatabase.insert(BOOKTABLE_NAME, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }



    public boolean addPublisherdetails(String name, String address, String phone) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PUBLISHERCOLUMN_NAME, name);
        cv.put(PUBLISHERCOLUMN_ADDRESS, address);
        cv.put(PUBLISHERCOLUMN_PHONE, phone);
        long result = MyDatabase.insert(PUBLISHERTABLE_NAME, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + BOOKTABLE_NAME;
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
//
        Cursor cursor = null;
        if(MyDatabase != null){
           cursor = MyDatabase.rawQuery(query, null);
      }
       return cursor;
    }
    Cursor readAllPublisherdata(){
        String query = "SELECT * FROM " + PUBLISHERTABLE_NAME;
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
//
        Cursor cursor = null;
        if(MyDatabase != null){
            cursor = MyDatabase.rawQuery(query, null);
        }
        return cursor;
    }

    public boolean updateBookdata(String row_id, String title, String author, String publisher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BOOKCOLUMN_TITLE, title);
        cv.put(BOOKCOLUMN_AUTHOR, author);
        cv.put(BOOKCOLUMN_PUBLISHER, publisher);

        long result = db.update(BOOKTABLE_NAME, cv, "book_id=?", new String[]{row_id});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updatepublisherdata(String row_id, String name, String address, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PUBLISHERCOLUMN_NAME, name);
        cv.put(PUBLISHERCOLUMN_ADDRESS, address);
        cv.put(PUBLISHERCOLUMN_PHONE, phone);

        long result = db.update(PUBLISHERTABLE_NAME, cv, "publisher_id=?", new String[]{row_id});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


//    void updateBookdata(String row_id, String title, String author, String publisher){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(BOOKCOLUMN_TITLE, title);
//        cv.put(BOOKCOLUMN_AUTHOR, author);
//        cv.put(BOOKCOLUMN_PUBLISHER, publisher);
//
//        long result = db.update(BOOKTABLE_NAME, cv, "book_id=?",new String[]{row_id});
//        if(result == -1){
//            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();
//        }

//        SQLiteDatabase MyDatabase = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(BOOKCOLUMN_TITLE, title);
//        cv.put(BOOKCOLUMN_AUTHOR, author);
//        cv.put(BOOKCOLUMN_PUBLISHER, publisher);
//
//          MyDatabase.update(BOOKTABLE_NAME, cv, "book_id=?",new String[]{row_id});
//        long result = MyDatabase.update(BOOKTABLE_NAME, cv, "book_id",new String[]{row_id});
//        if(result == -1){
//            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();
//        }

  //  }
    
    public Boolean insertData(String fullname,String email, String nic, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("email", email);
        contentValues.put("nic",nic);
        contentValues.put("password", password);

        long result = MyDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
}