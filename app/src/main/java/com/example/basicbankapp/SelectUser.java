package com.example.basicbankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SelectUser extends AppCompatActivity {

    public String senderId;

    RecyclerView recyclerView;
    List<model> dataholder = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    SelectUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user);

        recyclerView = findViewById(R.id.rv_alluser);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        senderId = getIntent().getExtras().getString("UserID");

        rowSelecteduser(senderId);
        adapter.senderID = senderId;

        getSupportActionBar().setTitle("Select User");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void rowSelecteduser(String senderId) {
        dataholder.clear();
        Cursor cursor = new Database(this).readselectuserdata(senderId);

        while(cursor.moveToNext()){
            model md = new model(cursor.getString(0),cursor.getString(1), cursor.getString(3),cursor.getString(6));
            this.dataholder.add(md);
        }

        adapter = new SelectUserAdapter(SelectUser.this, dataholder);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}