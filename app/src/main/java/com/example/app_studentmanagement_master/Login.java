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


public class Login extends AppCompatActivity {

    TextView tv_dk;
    EditText edt_emailg, edt_passg;
    Button btn_login;
    SQLite sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(Login.this,R.color.colortime));
        initView();
        sqliteHelper = new SQLite(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String Email = edt_emailg.getText().toString();
                    String Password = edt_passg.getText().toString();


                    User currentUser = sqliteHelper.Login(new User(null, null, Email, Password));

                    if (currentUser != null) {
                        Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(Login.this,Home.class);
                        String id_user = currentUser.id;
                        intent.putExtra("id_user", id_user);
                        startActivity(intent);
                        finish();
                    } else {

                        Toast.makeText(Login.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


        tv_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }

    public void initView(){
        tv_dk = findViewById(R.id.tv_dk);
        btn_login = findViewById(R.id.btn_login);
        edt_emailg = findViewById(R.id.edt_emailg);
        edt_passg = findViewById(R.id.edt_passg);
    }


    public boolean validate() {
        boolean valid = false;


        String Email = edt_emailg.getText().toString();
        String Password = edt_passg.getText().toString();


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            edt_emailg.setError("Vui lòng nhập email!");
        } else {
            valid = true;
            edt_emailg.setError(null);
        }

        if (Password.isEmpty()) {
            valid = false;
            edt_passg.setError("Vui lòng nhập mật khẩu !");
        } else {
            if (Password.length() > 5) {
                valid = true;
                edt_passg.setError(null);
            } else {
                valid = false;
                edt_passg.setError("Mật khẩu quá ngắn!");
            }
        }

        return valid;
    }
}
