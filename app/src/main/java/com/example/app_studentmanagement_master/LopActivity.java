package com.example.app_studentmanagement_master;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_studentmanagement_master.Adapter.LopAdapter;
import com.example.app_studentmanagement_master.Modal.Lop;
import com.example.app_studentmanagement_master.SQL.LopDAO;

import java.util.ArrayList;



public class LopActivity extends AppCompatActivity {
    ImageView imgBack;
    TextView tv_them;
    public static ListView lv_theloai;
    public static LopDAO theloaiDao;
    public static LopAdapter lop_adapter;
    public static ArrayList<Lop> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theloai);
        initView();

       capnhat();

        tv_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LopActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_them_lop, null);
                dialogBuilder.setView(dialogView);

                final EditText edt_nametl = (EditText) dialogView.findViewById(R.id.edt_tenTL);
                Button btn_themtl = dialogView.findViewById(R.id.btn_themtl);


                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                btn_themtl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nameTl = edt_nametl.getText().toString();
                        theloaiDao.addTL(new Lop(null, nameTl));
                        capnhat();
                        alertDialog.cancel();


                    }
                });
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initView(){
        imgBack = findViewById(R.id.imgBackLop);
        tv_them = findViewById(R.id.tv_theloai);
        lv_theloai = findViewById(R.id.lv_theloai);
    }

    public void capnhat(){
        theloaiDao = new LopDAO(LopActivity.this);
        data = theloaiDao.getTL();
        lop_adapter = new LopAdapter(LopActivity.this, data);
        lv_theloai.setAdapter(lop_adapter);
    }
}