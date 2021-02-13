package com.example.basicbankapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AllUserAdapter extends RecyclerView.Adapter<AllUserAdapter.myviewholder>
{
    private Context context;
    private List<model> dataholder;

    public AllUserAdapter(Context context, List<model> dataholder) {
        this.context = context;
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new myviewholder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.Image.setText(String.valueOf(dataholder.get(position).getName().charAt(0)));
        holder.Name.setText(dataholder.get(position).getName());
        holder.phone.setText(dataholder.get(position).getPhone());
        holder.Balance.setText("Rs. "+ dataholder.get(position).getBalance());

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView Image, Name, phone, Balance;

        public myviewholder( View itemView, Context context) {
            super(itemView);
            Image = itemView.findViewById(R.id.userImage);
            Name = itemView.findViewById(R.id.tv_user_name);
            phone = itemView.findViewById(R.id.tv_user_phone);
            Balance = itemView.findViewById(R.id.tv_user_balance);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    model md = dataholder.get(position);
                    Intent intent = new Intent(context, UserDetails.class);
                    intent.putExtra("UserID",md.getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
