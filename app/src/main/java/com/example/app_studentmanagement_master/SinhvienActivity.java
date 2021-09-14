package com.example.app_studentmanagement_master;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_studentmanagement_master.Adapter.Adapter_spinner_lop;
import com.example.app_studentmanagement_master.Adapter.SvAdapter;
import com.example.app_studentmanagement_master.Modal.Lop;
import com.example.app_studentmanagement_master.Modal.Sinhvien;
import com.example.app_studentmanagement_master.SQL.LopDAO;
import com.example.app_studentmanagement_master.SQL.SinhvienDAO;

import java.util.ArrayList;



public class SinhvienActivity extends AppCompatActivity {
    ImageView imgBacksv;
    TextView tv_them_sv;
    EditText search_sv;
    public static ListView lv_sv;
    ArrayList<Lop> dslops=new ArrayList<Lop>();
    public static ArrayList<Sinhvien> datasv = new ArrayList<>();

    LopDAO lopDAO;
    SinhvienDAO sinhvienDAO;
    SvAdapter svAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien);


        initView();
        capnhatsv();

        search_sv.setImeOptions(EditorInfo.IME_ACTION_DONE);
        search_sv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

        imgBacksv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_them_sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SinhvienActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_them_sinhvien, null);
                dialogBuilder.setView(dialogView);

                final EditText edt_tensv =  dialogView.findViewById(R.id.edt_ten_sv);
                final EditText edt_mssv = dialogView.findViewById(R.id.edt_ma_sv);
                final EditText edt_nganh =  dialogView.findViewById(R.id.edt_nganh_sv);
                final Spinner sp_lop =  dialogView.findViewById(R.id.sp_lop);
                Button btn_them_sv = dialogView.findViewById(R.id.btn_them_sv);

                lopDAO = new LopDAO(SinhvienActivity.this);
                dslops = lopDAO.getTL();

                Adapter_spinner_lop adapter = new Adapter_spinner_lop(SinhvienActivity.this,dslops);
                sp_lop.setAdapter(adapter);

                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                btn_them_sv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tensv = edt_tensv.getText().toString();
                        String mssv = edt_mssv.getText().toString();
                        String nganh = edt_nganh.getText().toString();

                        int index_ = sp_lop.getSelectedItemPosition();
                        String id_lop = dslops.get(index_).getId();
                        //String ten_lop = dslops.get(index).getTentheloai();

                        sinhvienDAO = new SinhvienDAO(SinhvienActivity.this);
                        sinhvienDAO.themsv(new Sinhvien(null,tensv,mssv,nganh,id_lop));
                        capnhatsv();
                        alertDialog.cancel();
                        //Toast.makeText(SinhvienActivity.this, id_lop, Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });
    }

    private void filter(String text) {
        ArrayList<Sinhvien> filteredList = new ArrayList<>();

        for (Sinhvien item : datasv) {
            if (item.getTensv().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        svAdapter.filterList(filteredList);
    }

    public void initView(){
        imgBacksv = findViewById(R.id.imgBackSV);
        tv_them_sv = findViewById(R.id.tv_sinhvien);
        lv_sv = findViewById(R.id.lv_sinhvien);
        search_sv = findViewById(R.id.edt_search_sv);
    }

    public void capnhatsv(){
        sinhvienDAO = new SinhvienDAO(SinhvienActivity.this);
        datasv = sinhvienDAO.getSV();
        svAdapter = new SvAdapter(SinhvienActivity.this, datasv);
        lv_sv.setAdapter(svAdapter);

    }
}