package com.example.basicbankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class PassBook extends AppCompatActivity {

    RecyclerView recyclerView;
    List<model> dataholder = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    PassBook_Adapter adapter;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passbook);

        recyclerView = findViewById(R.id.rv_payment_history);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        id = getIntent().getExtras().getString("UserID");

        rowShow(id);
        adapter.ID = id;

        getSupportActionBar().setTitle("Passbook");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void rowShow(String id) {
        dataholder.clear();
        Cursor cursor = new Database(this).readperticulartransferdata(id);

        while(cursor.moveToNext()){
            model md = new model(cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
            this.dataholder.add(md);
        }

        adapter = new PassBook_Adapter(PassBook.this, dataholder);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}