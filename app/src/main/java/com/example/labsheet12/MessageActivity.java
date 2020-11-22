package com.example.labsheet12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    TextView subject, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        subject = findViewById(R.id.textViewSubject5);
        message = findViewById(R.id.textViewMessage5);

        Intent intent = getIntent();

        subject.setText(intent.getStringExtra("subject"));
        message.setText(intent.getStringExtra("message"));
    }
}