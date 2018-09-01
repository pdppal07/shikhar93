package com.example.shikharjai.contact;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class contactAdapter extends RecyclerView.Adapter<contactAdapter.contactViewHolder> {
    List<String> contactStringList;
    Context context;

    public contactAdapter(List<String> contactStringList, Context context) {
        this.contactStringList = contactStringList;
        this.context = context;
    }

    @NonNull
    @Override
    public contactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.contact_rec, viewGroup, false);

        return new contactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull contactViewHolder holder, int i) {
        final String str[] = contactStringList.get(i).toString().split(":");
        holder.contactname.setText(str[0]);
        holder.contactnumber.setText(str[1]);
        holder.contactemail.setText(str[2]);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);
                TextView ename= dialog.findViewById(R.id.Ename);
                TextView eemail= dialog.findViewById(R.id.Eemail);
                TextView enumber= dialog.findViewById(R.id.Enumber);
                ename.setText(str[0]);
                enumber.setText(str[1]);
                eemail.setText(str[2]);
                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return contactStringList.size();
    }

    public class contactViewHolder extends RecyclerView.ViewHolder{
        ImageView contact_pic;
        TextView contactname;
        TextView contactemail;
        TextView contactnumber;
        CardView cardView;
        public contactViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_pic = itemView.findViewById(R.id.contactpic);
            contactname = itemView.findViewById(R.id.contactname);
            contactnumber = itemView.findViewById(R.id.contactnumber);
            contactemail = itemView.findViewById(R.id.contactemail);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
