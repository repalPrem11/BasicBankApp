package com.example.basicbankapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Transaction extends AppCompatActivity {
    TextView senderImage,receiverImage,senderName,senderAccno,senderIfsc,receiverName,receiverAccno,receiverIfsc;
    TextInputLayout Amount;
    FloatingActionButton MakeTransaction;
    String SenderId,ReceiverId;
    Double SenderBalance,ReceiverBalance;
    String amount;
    String formattedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        senderImage = findViewById(R.id.SenderImage);
        senderName = findViewById(R.id.SenderName);
        senderAccno = findViewById(R.id.SenderAccount);
        senderIfsc = findViewById(R.id.SenderIFSC);
        receiverImage =findViewById(R.id.ReceiverImage);
        receiverName =findViewById(R.id.ReceiverName);
        receiverAccno =findViewById(R.id.RecieverAccount);
        receiverIfsc =findViewById(R.id.ReceiverIFSC);
        Amount = findViewById(R.id.sendAmount);
        MakeTransaction = findViewById(R.id.maketansaction);



        SenderId = getIntent().getExtras().getString("SenderId");
        ReceiverId = getIntent().getExtras().getString("ReceiverId");

        showSenderData(SenderId);
        showReceiverData(ReceiverId);

        MakeTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoneyTransfer();
            }
        });

    }




    private void showSenderData(String senderId) {
        Cursor cursor = new Database(this).readparticulardata(senderId);
        while (cursor.moveToNext()) {


            senderImage.setText(String.valueOf(cursor.getString(1).charAt(0)));
            senderName.setText(cursor.getString(1));
            senderAccno.setText(cursor.getString(4));
            senderIfsc.setText(cursor.getString(5));
            SenderBalance = Double.parseDouble(cursor.getString(6));
        }

    }

    private void showReceiverData(String receiverId) {
        Cursor cursor = new Database(this).readparticulardata(ReceiverId);
        while (cursor.moveToNext()) {


            receiverImage.setText(String.valueOf(cursor.getString(1).charAt(0)));
            receiverName.setText(cursor.getString(1));
            receiverAccno.setText(cursor.getString(4));
            receiverIfsc.setText(cursor.getString(5));
            ReceiverBalance = Double.parseDouble(cursor.getString(6));
        }
    }

    private void MoneyTransfer() {
        amount = String.valueOf(Amount.getEditText().getText());

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        formattedDate = df.format(c.getTime());

        String Transaction_id = String.valueOf(new Random().nextInt((9999999 - 1000000) + 1) + 1000000);


        if (amount.isEmpty()){
            AlertDialog alertDialog = new AlertDialog.Builder(Transaction.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setIcon(R.drawable.ic_baseline_error_24 );
            alertDialog.setMessage("Please Enter a Amount");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else if ( Double.parseDouble(amount) > SenderBalance){
            AlertDialog alertDialog = new AlertDialog.Builder(Transaction.this).create();
            alertDialog.setTitle("Insufficient Balance");
            alertDialog.setIcon(R.drawable.ic_baseline_error_24 );
            alertDialog.setMessage("You Dont Have Enough Money.");
            alertDialog.setCancelable(true);
            alertDialog.setCanceledOnTouchOutside(true);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                            Intent intent = new Intent(Transaction.this,Payment_Receipt.class);
                            intent.putExtra("PaymentStatus", "Failed");
                            intent.putExtra("TransactionID", Transaction_id);
                            startActivity(intent);
                            finish();
                        }
                    });
            alertDialog.show();

            new Database(this).insertTransferData(Transaction_id,formattedDate, SenderId,ReceiverId,amount,"Failed");
        }
        else{
            SenderBalance = SenderBalance - Double.parseDouble(amount);
            new Database(this).updateAmount(SenderId , SenderBalance);
            ReceiverBalance = ReceiverBalance + Double.parseDouble(amount);
             new Database(this).updateAmount(ReceiverId , ReceiverBalance);

            boolean result = new Database(this).insertTransferData(Transaction_id,formattedDate, SenderId,ReceiverId,amount,"Successful");
            if (result == true){
                Toast.makeText(this, "SuccessFul", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Transaction.this, Payment_Receipt.class);
                intent.putExtra("TransactionID", Transaction_id);
                intent.putExtra("PaymentStatus", "Successful");
                startActivity(intent);
                finish();
            }
        }
    }

}