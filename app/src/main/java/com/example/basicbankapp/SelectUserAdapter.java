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

import java.util.List;

public class SelectUserAdapter extends RecyclerView.Adapter<SelectUserAdapter.myviewholder>
{
    private Context context;
    private List<model> dataholder;
    String senderID;

    public SelectUserAdapter(Context context, List<model> dataholder) {
        this.context = context;
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public SelectUserAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_user_row, parent, false);
        return new SelectUserAdapter.myviewholder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectUserAdapter.myviewholder holder, int position) {
        holder.Image.setText(String.valueOf(dataholder.get(position).getName().charAt(0)));
        holder.Name.setText(dataholder.get(position).getName());
        holder.phone.setText(dataholder.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }


    class myviewholder extends RecyclerView.ViewHolder{
        TextView Image, Name, phone;

        public myviewholder( View itemView, Context context) {
            super(itemView);
            Image = itemView.findViewById(R.id.userImage);
            Name = itemView.findViewById(R.id.tv_user_name);
            phone = itemView.findViewById(R.id.tv_user_phone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    model md = dataholder.get(position);
                    Intent intent = new Intent(context, Transaction.class);
                    intent.putExtra("SenderId",senderID);
                    intent.putExtra("ReceiverId",md.getId());
                    context.startActivity(intent);

                }
            });
        }
    }
}
