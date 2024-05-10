package com.example.librarymanagement_asnaff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class publisherDashboard extends AppCompatActivity {
    RecyclerView publisherRecyclerview;

    FloatingActionButton add_publisher;

    DBHandler myDB;
    ArrayList<String> publisher_id, publisher_name, publisher_address, publisher_phone;
    CustomAdapter_Publisher customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher_dashboard);

       publisherRecyclerview = findViewById(R.id.publisherRecyclerview);
        add_publisher = findViewById(R.id.add_publisher);
        getWindow().setStatusBarColor(Color.parseColor("#2C3E50"));


        add_publisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent intent = new Intent(publisherDashboard.this, addPublisher.class);
                  startActivity(intent);
            }

        });


        myDB = new DBHandler(publisherDashboard.this);
        publisher_id = new ArrayList<>();
        publisher_name = new ArrayList<>();
        publisher_address = new ArrayList<>();
        publisher_phone = new ArrayList<>();

        storepubDataInArrays();
//
        customAdapter = new CustomAdapter_Publisher(publisherDashboard.this, publisher_id, publisher_name,publisher_address, publisher_phone);

//       // customAdapter = new CustomAdapter(Dashboard.this,Dashboard.this, book_id, book_title,book_author, book_publisher);
        publisherRecyclerview.setAdapter(customAdapter);
        publisherRecyclerview.setLayoutManager(new LinearLayoutManager(publisherDashboard.this));
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1){
//            recreate();
//        }
    }

    void storepubDataInArrays() {
        Cursor cursor = myDB.readAllPublisherdata();
        if (cursor.getCount() == 0) {
            // empty_imageview.setVisibility(View.VISIBLE);
            // no_data.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()) {
                publisher_id.add(cursor.getString(0));
                publisher_name.add(cursor.getString(1));
                publisher_address.add(cursor.getString(2));
                publisher_phone.add(cursor.getString(3));
            }
        }



    }
}