package com.example.app_studentmanagement_master;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.app_studentmanagement_master.Modal.User;
import com.example.app_studentmanagement_master.SQL.SQLite;


public class Register extends AppCompatActivity {
    EditText edt_user, edt_email, edt_pass, edt_confimpass;
    TextView tv_dangnhap;
    Button btn_register;

    SQLite sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(Register.this,R.color.colortime));

        sqliteHelper = new SQLite(this);
        initView();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String UserName = edt_user.getText().toString();
                    String Email = edt_email.getText().toString();
                    String Password = edt_pass.getText().toString();
                    if (!sqliteHelper.isEmailExists(Email)) {
                        sqliteHelper.Register(new User(null, UserName, Email, Password));
                        Toast.makeText(Register.this, "Dang ki thanh cong", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Register.this, "Dang ki that bai, kiem lai email va mat khau", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tv_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

    }

    public void initView(){
        edt_user = findViewById(R.id.edt_username);
        edt_email = findViewById(R.id.edt_email);
        edt_pass = findViewById(R.id.edt_pass);
        edt_confimpass = findViewById(R.id.edt_confirmpass);
        tv_dangnhap = findViewById(R.id.tv_dangnhap);
        btn_register = findViewById(R.id.btn_register);
    }

    public boolean validate() {
        boolean valid = false;
        String UserName = edt_user.getText().toString();
        String Email = edt_email.getText().toString();
        String Password = edt_pass.getText().toString();
        String ConfirmPass = edt_confimpass.getText().toString();


        if (UserName.isEmpty()) {
            valid = false;
            edt_user.setError("Vui lòng nhập tên tài khoản!");
        } else {
            if (UserName.length() > 5) {
                valid = true;
                edt_user.setError(null);
            } else {
                valid = false;
                edt_user.setError("Tên tài khoản ngắn ");
            }
        }


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            edt_email.setError("Vui lòng nhập email!");
        } else {
            valid = true;
            edt_email.setError(null);
        }

        if (Password.isEmpty()) {
            valid = false;
            edt_pass.setError("Vui lòng nhập mật khẩu !");
        } else {
            if (Password.length() > 5) {
                valid = true;
                edt_pass.setError(null);
            } else {
                valid = false;
                edt_pass.setError("Mật khẩu ngắn hơn 5!");
            }
        }

        if (ConfirmPass.isEmpty()) {
            valid = false;
            edt_confimpass.setError("Vui lòng nhập mật khẩu!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                edt_confimpass.setError(null);
            } else {
                valid = false;
                edt_confimpass.setError("Mật khẩu ngắn hơn 5!");
            }
        }

        if(!ConfirmPass.equals(Password)){
            valid = false;
            edt_confimpass.setError("Mật khẩu không trùng khớp!");
        } else {
            valid = true;
            edt_confimpass.setError(null);
        }




        return valid;
    }
}
