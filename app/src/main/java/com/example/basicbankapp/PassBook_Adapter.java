package com.example.basicbankapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PassBook_Adapter extends RecyclerView.Adapter<PassBook_Adapter.myviewholder>
{
    private Context context;
    private List<model> dataholder;
    String ID ;

    public PassBook_Adapter(Context context, List<model> dataholder) {
        this.context = context;
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public PassBook_Adapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.passbook_row, parent, false);
        return new PassBook_Adapter.myviewholder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull PassBook_Adapter.myviewholder holder, int position) {

        String user1_id = dataholder.get(position).getSender_id();
        String user2_id = dataholder.get(position).getReceiver_id();
        String status =dataholder.get(position).getStatus();


        holder.amount.setText("Rs. " + dataholder.get(position).getAmount());
        holder.status.setText(dataholder.get(position).getStatus());
        holder.date.setText(dataholder.get(position).getDate());

        if (user1_id.equals(ID)) {
            Cursor cursor = new Database(context).readparticulardata(user1_id);
            while (cursor.moveToNext()) {
                holder.user1.setText(cursor.getString(1));
            }
            Cursor cursor1 = new Database(context).readparticulardata(user2_id);
            while (cursor1.moveToNext()) {
                holder.user2.setText(cursor1.getString(1));
            }

            if (status.equals( "Successful")){
                holder.status.setTextColor(Color.parseColor("#ff669900"));
            }
            else {
                holder.status.setTextColor(Color.parseColor("#ffcc0000"));
            }

            holder.cr_dr_status.setText("Debit");
            holder.cr_dr_status.setTextColor(Color.parseColor("#ffcc0000"));
            holder.iv_cr_dr.setImageResource(R.drawable.ic_debit);
        }
        else {
            Cursor cursor = new Database(context).readparticulardata(user1_id);
            while (cursor.moveToNext()) {
                holder.user2.setText(cursor.getString(1));
            }
            Cursor cursor1 = new Database(context).readparticulardata(user2_id);
            while (cursor1.moveToNext()) {
                holder.user1.setText(cursor1.getString(1));
            }

            if (status.equals( "Successful")){
                holder.status.setTextColor(Color.parseColor("#ff669900"));
            }
            else {
                holder.status.setTextColor(Color.parseColor("#ffcc0000"));
            }

            holder.cr_dr_status.setText("Credit");
            holder.cr_dr_status.setTextColor(Color.parseColor("#ff669900"));
            holder.iv_cr_dr.setImageResource(R.drawable.ic_credit);
        }


    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }


    class myviewholder extends RecyclerView.ViewHolder{
        TextView user1,user2, amount, status,date,cr_dr_status;
        ImageView iv_cr_dr;

        public myviewholder( View itemView, Context context) {
            super(itemView);
            user1 = itemView.findViewById(R.id.tv_user1);
            user2 = itemView.findViewById(R.id.tv_user2);
            amount = itemView.findViewById(R.id.tv_py_amount);
            status = itemView.findViewById(R.id.tv_py_status);
            date = itemView.findViewById(R.id.tv_py_date);
            cr_dr_status = itemView.findViewById(R.id.tv_cr_dr_status);
            iv_cr_dr = itemView.findViewById(R.id.iv_cr_dr_status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    model md = dataholder.get(position);
                    Intent intent = new Intent(context, Payment_Receipt.class);
                    intent.putExtra("TransactionID",md.getTrans_id());
                    intent.putExtra("PaymentStatus", md.getStatus());
                    context.startActivity(intent);

                }
            });
        }
    }


}
