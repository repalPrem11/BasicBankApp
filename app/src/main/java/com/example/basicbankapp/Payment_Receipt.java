package com.example.basicbankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Payment_Receipt extends AppCompatActivity {


    TextView transId,senderAcc,receiverAcc,amount,date,status;
    ImageView success,failed;
    TextView paymentStatus;
    String TransactionID, pystatus;
    String senderId, receiverId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment__receipt);

        transId = findViewById(R.id.tv_transactionId);
        senderAcc = findViewById(R.id.tv_sender_account);
        receiverAcc = findViewById(R.id.tv_receiver_account);
        amount = findViewById(R.id.tv_amount);
        date = findViewById(R.id.tv_date_time);
        status = findViewById(R.id.tv_status);
        success = findViewById(R.id.iv_success);
        failed = findViewById(R.id.iv_failed);
        paymentStatus = findViewById(R.id.paymentstatus);

        TransactionID = getIntent().getExtras().getString("TransactionID");
        pystatus = getIntent().getExtras().getString("PaymentStatus");

        if(pystatus.equals("Failed")){
            success.setVisibility(View.GONE);
            failed.setVisibility(View.VISIBLE);
            paymentStatus.setText("Payment Failed");
            paymentStatus.setTextColor(Color.parseColor("#ffcc0000"));
            status.setTextColor(Color.parseColor("#fb7268"));
        }

        showTransData(TransactionID);

        getSupportActionBar().setTitle("Payment Receipt");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void showTransData(String transactionID) {
        Cursor cursor = new Database(this).readtransferdata(transactionID);
        while (cursor.moveToNext()) {
            transId.setText(cursor.getString(1));
           senderId = cursor.getString(3);
           receiverId =  cursor.getString(4);
            amount.setText(cursor.getString(5));
            status.setText(cursor.getString(6));
            date.setText(cursor.getString(2));
        }

        Cursor cursor1 = new Database(this).readparticulardata(senderId);
        while (cursor1.moveToNext()) {
            senderAcc.setText(cursor1.getString(4));
        }
        Cursor cursor2 = new Database(this).readparticulardata(receiverId);
        while (cursor2.moveToNext()) {
            receiverAcc.setText(cursor2.getString(4));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}