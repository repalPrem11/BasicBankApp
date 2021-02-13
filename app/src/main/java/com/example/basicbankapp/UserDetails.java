package com.example.basicbankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class UserDetails extends AppCompatActivity {
    String id;
    TextView name,balance,email,phone,account_no,IFSC_code,user_image;
    CardView transaction , passbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        name = findViewById(R.id.tv_user_name);
        balance = findViewById(R.id.tv_user_balance);
        email = findViewById(R.id.tv_user_email);
        phone = findViewById(R.id.tv_user_phone);
        account_no = findViewById(R.id.tv_user_Acc_no);
        IFSC_code = findViewById(R.id.tv_user_IFSC_code);
        user_image = findViewById(R.id.userImage);
        transaction = findViewById(R.id.transaction);
        passbook = findViewById(R.id.passbook);


        id= getIntent().getExtras().getString("UserID");

        ShowData(id);

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetails.this, SelectUser.class);
                intent.putExtra("UserID", id);
                startActivity(intent);
            }
        });

        passbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetails.this, PassBook.class);
                intent.putExtra("UserID", id);
                startActivity(intent);
            }
        });

        getSupportActionBar().setTitle(name.getText());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void ShowData(String id) {
        Cursor cursor = new Database(this).readparticulardata(id);
            while (cursor.moveToNext()) {


                user_image.setText(String.valueOf(cursor.getString(1).charAt(0)));
                name.setText(cursor.getString(1));
                email.setText(cursor.getString(2));
                phone.setText(cursor.getString(3));
                account_no.setText(cursor.getString(4));
                IFSC_code.setText(cursor.getString(5));
                balance.setText(cursor.getString(6));
            }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}