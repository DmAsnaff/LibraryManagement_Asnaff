package com.example.librarymanagement_asnaff;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter_Publisher extends RecyclerView.Adapter<CustomAdapter_Publisher.MyViewHolder> {

    Context context;
    ArrayList publisher_id, publisher_name, publisher_address, publisher_phone;
    CustomAdapter_Publisher(Context context,
                  ArrayList publisher_id,
                  ArrayList publisher_name,
                  ArrayList publisher_address,
                  ArrayList publisher_phone){

        this.context = context;
        this.publisher_id = publisher_id;
        this.publisher_name = publisher_name;
        this.publisher_address = publisher_address;
        this.publisher_phone = publisher_phone;
    }

    @NonNull
    @Override
    public CustomAdapter_Publisher.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_publisher, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter_Publisher.MyViewHolder holder, int position) {
        holder.publisher_id_txt.setText(String.valueOf(publisher_id.get(position)));
        holder.publisher_name_txt.setText(String.valueOf(publisher_name.get(position)));
        holder.publisher_address_txt.setText(String.valueOf(publisher_address.get(position)));
        holder.publisher_phone_txt.setText(String.valueOf(publisher_phone.get(position)));

        holder.pub_mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, publisherUpdate.class);
                intent.putExtra("id",String.valueOf(publisher_id.get(position)));
                intent.putExtra("name",String.valueOf(publisher_name.get(position)));
                intent.putExtra("address",String.valueOf(publisher_address.get(position)));
                intent.putExtra("phone",String.valueOf(publisher_phone.get(position)));

                context.startActivity(intent);

            }

        });
    }

    @Override
    public int getItemCount() {

        return publisher_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView publisher_id_txt, publisher_name_txt, publisher_address_txt, publisher_phone_txt;
        LinearLayout pub_mainLayout;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            publisher_id_txt = itemView.findViewById(R.id.publisher_id_txt);
            publisher_name_txt = itemView.findViewById(R.id.publisher_name_txt);
            publisher_address_txt = itemView.findViewById(R.id.publisher_address_txt);
            publisher_phone_txt = itemView.findViewById(R.id.publisher_phone_txt);

            pub_mainLayout = itemView.findViewById(R.id.pub_mainLayout);


        }
    }
}