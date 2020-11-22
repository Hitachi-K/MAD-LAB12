package com.example.labsheet12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import Database.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    Button register;
    EditText username, password;
    CheckBox student, teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //mapping the variables into the object of the layout
        register = findViewById(R.id.btn2Register);
        username = findViewById(R.id.txtRegisterUname);
        password = findViewById(R.id.txtRegisterPass);
        student = findViewById(R.id.checkBoxStudent);
        teacher = findViewById(R.id.checkBoxTeacher);

        // on-click listener for the register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper = new DatabaseHelper(RegisterActivity.this);

                if (student.isChecked()) {
                    long val = dbHelper.addUserData(username.getText().toString(), password.getText().toString(), "Student");

                    if (val>0) {
                        Toast.makeText(RegisterActivity.this, "Registered Student Successfully!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Registratiom Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (teacher.isChecked()) {
                    long val = dbHelper.addUserData(username.getText().toString(), password.getText().toString(), "Teacher");

                    if (val>0) {
                        Toast.makeText(RegisterActivity.this, "Registered Teacher Successfully!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}