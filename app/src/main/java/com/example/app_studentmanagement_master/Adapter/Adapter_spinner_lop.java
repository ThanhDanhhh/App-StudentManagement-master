package com.example.app_studentmanagement_master.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_studentmanagement_master.Modal.Lop;
import com.example.app_studentmanagement_master.R;
import com.example.app_studentmanagement_master.SQL.LopDAO;

import java.util.ArrayList;



public class Adapter_spinner_lop extends BaseAdapter {

    public ArrayList<Lop> ds = new ArrayList<Lop>();
    public Context c;
    public LopDAO lopDAO;

    public Adapter_spinner_lop(Context c, ArrayList<Lop> ds){
        this.c=c;
        this.ds=ds;
    }
    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        LayoutInflater inf = ((Activity)c).getLayoutInflater();
        view = inf.inflate(R.layout.spinner_lop,viewGroup,false);

        TextView tv_item_lop = view.findViewById(R.id.tv_sp_lop);
        Lop lt = ds.get(i);
        tv_item_lop.setText(lt.getTentheloai());

        return view;
    }



}
