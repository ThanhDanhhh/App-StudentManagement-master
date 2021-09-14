package com.example.app_studentmanagement_master;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_studentmanagement_master.Modal.User;
import com.example.app_studentmanagement_master.SQL.SQLite;


public class ChangePassUser extends AppCompatActivity {
    EditText edt_old_pass, edt_new_pass, edt_confirm_pass, edt_email_;
    Button btn_update, btn_huy;
    SQLite sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass_user);

        initView();
        sqliteHelper = new SQLite(this);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    Intent intent = getIntent();
                    String _id = intent.getStringExtra("id_activity");
                    String Email = edt_email_.getText().toString();
                    String OldPass = edt_old_pass.getText().toString();
                    String NewPassword = edt_new_pass.getText().toString();

                    User currentUser = sqliteHelper.Login(new User(null, null, Email, OldPass));
                    if (currentUser != null) {

                        sqliteHelper.changepass(Email,NewPassword);
                        Toast.makeText(ChangePassUser.this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();

                        finish();
                    } else {
                        Toast.makeText(ChangePassUser.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });



    }

    public void initView(){
        edt_old_pass = findViewById(R.id.edt_old_pass);
        edt_new_pass = findViewById(R.id.edt_new_pass);
        edt_confirm_pass = findViewById(R.id.edt_confirm_pass);
        edt_email_ = findViewById(R.id.edt_email_);
        btn_update = findViewById(R.id.btn_change_pass);
        btn_huy = findViewById(R.id.btn_huy_sua_pass);
    }

    public boolean validate() {
        boolean valid = false;

        String OldPass = edt_old_pass.getText().toString();
        String NewPassword = edt_new_pass.getText().toString();
        String ConfirmPass = edt_confirm_pass.getText().toString();
        String Email = edt_email_.getText().toString();

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            edt_email_.setError("Vui lòng nhập email!");
        } else {
            valid = true;
            edt_email_.setError(null);
        }



        if (OldPass.isEmpty()) {
            valid = false;
            edt_old_pass.setError("Vui lòng nhập mật khẩu cũ !");
        } else {
            valid = true;
            edt_old_pass.setError(null);
        }

        if (NewPassword.isEmpty()) {
            valid = false;
            edt_new_pass.setError("Vui lòng nhập mật khẩu mới !");
        } else {
            if (NewPassword.length() > 5) {
                valid = true;
                edt_new_pass.setError(null);
            } else {
                valid = false;
                edt_new_pass.setError("Mật khẩu quá ngắn!");
            }
        }
        if(!ConfirmPass.equals(NewPassword)){
            valid = false;
            edt_confirm_pass.setError("Mật khẩu không trùng khớp!");
        } else {
            valid = true;
            edt_confirm_pass.setError(null);
        }

        return valid;
    }
}