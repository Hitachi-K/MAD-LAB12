package com.example.labsheet12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Database.DatabaseHelper;
import Database.Message;

public class StudentActivity extends AppCompatActivity {

    private static final String TAG = "StudentActivity";
    private List<Message> messages = new ArrayList<>();
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper = new DatabaseHelper(StudentActivity.this);
    private RecyclerAdapter recyclerAdapter;

    TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        username = findViewById(R.id.textViewStudentUsername);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("usernameStudent"));
        initViews();
        initObjects();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.messageRecycler);
    }

    private void initObjects() {
        messages.addAll(databaseHelper.getAllmsgs());
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
    }

}