package com.example.app_studentmanagement_master.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.app_studentmanagement_master.Modal.Lop;
import com.example.app_studentmanagement_master.R;
import com.example.app_studentmanagement_master.SQL.LopDAO;


import java.util.ArrayList;


import static com.example.app_studentmanagement_master.LopActivity.lop_adapter;
import static com.example.app_studentmanagement_master.LopActivity.lv_theloai;

public class LopAdapter extends BaseAdapter {
    Context context;
    ArrayList<Lop> data;
    LopDAO dao;
    public LopAdapter(Context context, ArrayList<Lop> data){
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final view holder;
        if(convertView == null){
            holder = new view();

            LayoutInflater inflater =((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_theloai,null);
            holder.tv_nametl = convertView.findViewById(R.id.tv_item_theloai);
            holder.img_select = convertView.findViewById(R.id.img_select);

            convertView.setTag(holder);
        }else
            holder =(view) convertView.getTag();
        holder.img_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                //LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_sua_tl, null);
                dialogBuilder.setView(dialogView);

                final EditText edt_nametl = (EditText) dialogView.findViewById(R.id.edt_stenTL);
                Button btn_suatl = dialogView.findViewById(R.id.btn_suatl);
                Button btn_xoa_themtl = dialogView.findViewById(R.id.btn_xoa_tl);

                edt_nametl.setText(data.get(position).getTentheloai());

                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                btn_suatl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dao = new LopDAO(context);
                        String id = data.get(position).getId();
                        String nameTl = edt_nametl.getText().toString();
                        dao.update(id, nameTl);

                        data = dao.getTL();
                        lop_adapter = new LopAdapter(context,data);
                        lv_theloai.setAdapter(lop_adapter);
                        alertDialog.cancel();
                    }
                });

                btn_xoa_themtl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dao = new LopDAO(context);
                        dao.xoa_tl(data.get(position).getId());

                        data = dao.getTL();
                        lop_adapter = new LopAdapter(context,data);
                        lv_theloai.setAdapter(lop_adapter);
                        alertDialog.cancel();
                    }
                });



            }
        });
        holder.tv_nametl.setText(data.get(position).getTentheloai());

        return convertView;

    }


    class view{
        ImageView img_select;
        TextView tv_nametl;
    }
}
