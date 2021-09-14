package com.example.app_studentmanagement_master.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.app_studentmanagement_master.Modal.User;


public class SQLite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "tutor";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERS = "users";
    public static final String KEY_ID = "id";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public static final String TABLE_LOP = "lop";
    public static final String KEY_NAME_LOP = "tenlop";

    public static final String TABLE_SINHVIEN = "sinhvien";
    public static final String KEY_NAME_SV = "tensv";
    public static final String KEY_MSSV = "mssv";
    public static final String KEY_NGANH = "nganh";
    public static final String KEY_LOP = "id_lop";

    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";

    public static final String SQL_TABLE_LOP = " CREATE TABLE " + TABLE_LOP
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME_LOP + " TEXT "
            + " ) ";

    public static final String SQL_TABLE_SINHVIEN = " CREATE TABLE " + TABLE_SINHVIEN
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME_SV + " TEXT, "
            + KEY_MSSV + " TEXT, "
            + KEY_NGANH + " TEXT, "
            + KEY_LOP + " TEXT "
            + " ) ";

    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_LOP);
        sqLiteDatabase.execSQL(SQL_TABLE_SINHVIEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + SQL_TABLE_LOP);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + SQL_TABLE_SINHVIEN);
    }

    public void Register(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.userName);
        values.put(KEY_EMAIL, user.email);
        values.put(KEY_PASSWORD, user.password);
        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Login(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},
                KEY_EMAIL + "=?",
                new String[]{user.email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }
        return null;
    }

    public boolean isEmailExists(String email) {
        //ngoc@gmail.com
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }
        return false;
    }

    public boolean changepass(String email, String newPass){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD,newPass);
        int update = db.update(TABLE_USERS,values,"email=?",new String[]{email});
        return update>0;
    }

}
