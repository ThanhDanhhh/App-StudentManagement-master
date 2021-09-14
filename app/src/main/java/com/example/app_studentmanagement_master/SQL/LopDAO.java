package com.example.app_studentmanagement_master.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_studentmanagement_master.Modal.Lop;

import java.util.ArrayList;



public class LopDAO {
    public SQLiteDatabase db;
    public SQLite dbh1;

    public LopDAO(Context c) {
        dbh1 = new SQLite(c);
    }

    public boolean addTL(Lop tl){
        db = dbh1.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenlop",tl.getTentheloai());
        long row =  db.insert("lop",null,values);
        return row >0;

    }

    //Mình sẽ vẫn dùng cái LopDAO này để lấy dữ liệu ra nhé!
    public ArrayList<Lop> getTL() {
        ArrayList<Lop> ds = new ArrayList<Lop>();

        db = dbh1.getReadableDatabase();
        Cursor c = db.query("lop", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                String idtl = c.getString(0);
                String nameTL = c.getString(1);
                Lop mt = new Lop(idtl,nameTL);
                ds.add(mt);

            } while (c.moveToNext());
        }
        return ds;
    }

        public void xoa_tl(String id) {
        db = dbh1.getWritableDatabase();

        db.delete("lop",
                "id=?",
                new String[]{id + ""});
    }
    public boolean update(String id, String tenTL){
        db = dbh1.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenlop",tenTL);
        int update = db.update("lop",values,"id=?",new String[]{id});
        return update>0;
    }

}
