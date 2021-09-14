package com.example.app_studentmanagement_master.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_studentmanagement_master.Modal.Sinhvien;

import java.util.ArrayList;


public class SinhvienDAO {
    public SQLiteDatabase db;
    public SQLite dbh1;

    public SinhvienDAO(Context context){
        dbh1 = new SQLite(context);
    }

    public boolean themsv(Sinhvien sv){
        db = dbh1.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensv",sv.getTensv());
        values.put("mssv",sv.getMssv());
        values.put("nganh",sv.getNganh());
        values.put("id_lop",sv.getMalop());
        long row = db.insert("sinhvien", null, values);
        return row > 0;
    }



    public ArrayList<Sinhvien> getSV() {
        ArrayList<Sinhvien> ds = new ArrayList<Sinhvien>();

        db = dbh1.getReadableDatabase();
        Cursor c = db.query("sinhvien", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                String idsv = c.getString(0);
                String tensv = c.getString(1);
                String mssv = c.getString(2);
                String nganh = c.getString(3);
                String lop = c.getString(4);

                Sinhvien mt = new Sinhvien(idsv,tensv,mssv,nganh,lop);
                ds.add(mt);

            } while (c.moveToNext());
        }


        return ds;
    }


}
