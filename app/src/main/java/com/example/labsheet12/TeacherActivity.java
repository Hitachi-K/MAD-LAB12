package com.example.labsheet12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Database.DatabaseHelper;

public class TeacherActivity extends AppCompatActivity {

    EditText subject, message;
    TextView tUsername;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        //mapping the variables to the objects in the layout
        subject = findViewById(R.id.editTextSubject);
        message = findViewById(R.id.editTextMessage);
        tUsername = findViewById(R.id.txtViewTeacherUsername);
        send = findViewById(R.id.btnSend);

        //obtaining the username from the previous activity
        Intent intent = getIntent();
        String userNameT = intent.getStringExtra("usernameTeacher");
        tUsername.setText(userNameT);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper = new DatabaseHelper(TeacherActivity.this);

                long val = dbHelper.addMessage(userNameT, subject.getText().toString().trim(), message.getText().toString().trim());

                if (val > 0) {
                    Toast.makeText(TeacherActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(TeacherActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}