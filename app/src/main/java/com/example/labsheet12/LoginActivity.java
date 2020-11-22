package com.example.labsheet12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    Button btnRegister, btnLogin;
    EditText userName, passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = findViewById(R.id.btn1Register);
        btnLogin = findViewById(R.id.btnLogin);
        userName = findViewById(R.id.txtUsername);
        passWord = findViewById(R.id.txtPassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper = new DatabaseHelper(LoginActivity.this);

                if (dbHelper.validateUser(userName.getText().toString().trim(), passWord.getText().toString().trim()) == 1) {
                    Intent intent = new Intent(LoginActivity.this, TeacherActivity.class);
                    intent.putExtra("usernameTeacher", userName.getText().toString());  //sending the username to the next activity
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }
                else if (dbHelper.validateUser(userName.getText().toString().trim(), passWord.getText().toString().trim()) == 2) {
                    Intent intent = new Intent(LoginActivity.this, StudentActivity.class);
                    intent.putExtra("usernameStudent", userName.getText().toString());
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid Login Credentials OR user does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}