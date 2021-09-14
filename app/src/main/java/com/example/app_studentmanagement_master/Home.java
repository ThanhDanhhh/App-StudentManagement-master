package com.example.app_studentmanagement_master;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.app_studentmanagement_master.Adapter.SliderAdapterExample;
import com.example.app_studentmanagement_master.Modal.SliderItem;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;



public class Home extends AppCompatActivity {
    String email;
    TextView tv_id, tv_new_pass;
    private ListView rvUserList;
    SliderView sliderView;
    private SliderAdapterExample adapter;

    Button btnTheloai, btn_sinhvien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Set mau cho thanh statusbar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(Home.this,R.color.colortime));

        initView();



        //Phan nay anh lam cai sliderview cho dep thoi, ban nao muon lam thi lam theo nhe
        //Neu lam phan nay thi buoi sau a se huong dan lai
        sliderView = findViewById(R.id.imageSlider);
        adapter = new SliderAdapterExample(Home.this);
        sliderView.setSliderAdapter(adapter);
        Slider();

        btnTheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, LopActivity.class);
                startActivity(i);
            }
        });

        btn_sinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, SinhvienActivity.class);
                startActivity(i);
            }
        });

        tv_new_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String _id = intent.getStringExtra("id_user");
                tv_id.setText(_id);

                Intent i = new Intent(Home.this, ChangePassUser.class);
                i.putExtra("id_activity", _id);
                startActivity(i);
            }
        });






    }
    @Override
    public void onStart() {
        super.onStart();
        List<SliderItem> sliderItemList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();
            if (i % 2 == 0) {
                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
            } else {
                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
            }
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }

    public void initView(){
        btnTheloai = findViewById(R.id.btnTheloai);
        btn_sinhvien = findViewById(R.id.btn_sinhvien);
        tv_id = findViewById(R.id.tv_id);
        tv_new_pass = findViewById(R.id.tv_new_pass);
    }

    public void Slider(){
        sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(false);
    }
}
