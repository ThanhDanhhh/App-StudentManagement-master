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
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.app_studentmanagement_master.Modal.Lop;
import com.example.app_studentmanagement_master.Modal.Sinhvien;
import com.example.app_studentmanagement_master.R;
import com.example.app_studentmanagement_master.SQL.LopDAO;
import com.example.app_studentmanagement_master.SQL.SinhvienDAO;


import java.util.ArrayList;




public class SvAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sinhvien> data;
    SinhvienDAO dao;
    LopDAO lopDAO;
    ArrayList<Lop> ds_lop = new ArrayList<Lop>();
    public SvAdapter(Context context, ArrayList<Sinhvien> data){
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
            convertView = inflater.inflate(R.layout.item_sinhvien,null);
            holder.tv_nametl = convertView.findViewById(R.id.tv_item_tensv);
            holder.tv_lop = convertView.findViewById(R.id.tv_item_lop);
            holder.img_select = convertView.findViewById(R.id.img_select_sv);

            convertView.setTag(holder);
        }else
            holder =(view) convertView.getTag();
        holder.img_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                //LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_sua_sinhvien, null);
                dialogBuilder.setView(dialogView);

                final EditText edt_sua_tensv = (EditText) dialogView.findViewById(R.id.edt_sua_ten_sv);
                final EditText edt_sua_mssv = (EditText) dialogView.findViewById(R.id.edt_sua_ma_sv);
                final EditText edt_sua_nganh = (EditText) dialogView.findViewById(R.id.edt_sua_nganh_sv);
                Spinner sp_sua_lop = dialogView.findViewById(R.id.sp_sua_lop);
                Button btn_sua_sv = dialogView.findViewById(R.id.btn_sua_sv);
                Button btn_huy_sua_sv = dialogView.findViewById(R.id.btn_huy_sua_sv);

                lopDAO = new LopDAO(context);
                ds_lop = lopDAO.getTL();
                Adapter_spinner_lop adapter = new Adapter_spinner_lop(context,ds_lop);
                sp_sua_lop.setAdapter(adapter);

                
                int id_lop = Integer.parseInt(data.get(position).getMalop());
                sp_sua_lop.setSelection(id_lop - 1);


               // String myString = ds_lop.get(id_lop).getTentheloai();


                edt_sua_tensv.setText(data.get(position).getTensv());
                edt_sua_mssv.setText(data.get(position).getMssv());
                edt_sua_nganh.setText(data.get(position).getNganh());

                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                btn_sua_sv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        dao = new LopDAO(context);
//                        String id = data.get(position).getId();
//                        String nameTl = edt_nametl.getText().toString();
//                        dao.update(id, nameTl);
//
//                        data = dao.getTL();
//                        lop_adapter = new SvAdapter(context,data);
//                        lv_theloai.setAdapter(lop_adapter);
//                        alertDialog.cancel();
                    }
                });

//                btn_xoa_themtl.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dao = new LopDAO(context);
//                        dao.xoa_tl(data.get(position).getId());
//
//                        data = dao.getTL();
//                        lop_adapter = new SvAdapter(context,data);
//                        lv_theloai.setAdapter(lop_adapter);
//                        alertDialog.cancel();
//                    }
//                });

            }
        });

        holder.tv_nametl.setText(data.get(position).getTensv());
        holder.tv_lop.setText(data.get(position).getMssv());

        return convertView;

    }

    public void filterList(ArrayList<Sinhvien> filteredList) {
        data = filteredList;
        notifyDataSetChanged();
    }



    class view{
        ImageView img_select;
        TextView tv_nametl, tv_lop;
    }
}
