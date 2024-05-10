package com.example.librarymanagement_asnaff;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    RecyclerView bookRecyclerview;
    FloatingActionButton add_button, menubtn;
    //ImageView empty_imageview;
   // TextView no_data;
    Button buttonmgt;
      DBHandler myDB;
    ArrayList<String> book_id, book_title, book_author, book_publisher;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bookRecyclerview = findViewById(R.id.bookRecyclerview);
        add_button = findViewById(R.id.add_button);
        getWindow().setStatusBarColor(Color.parseColor("#2C3E50"));

//        empty_imageview = findViewById(R.id.empty_imageview);
//        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, addBook.class);
                startActivity(intent);
            }

        });
        buttonmgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, managedashboard.class);
                startActivity(intent);
            }

        });




        myDB = new DBHandler(Dashboard.this);
         book_id = new ArrayList<>();
         book_title = new ArrayList<>();
         book_author = new ArrayList<>();
         book_publisher = new ArrayList<>();

         storeDataInArrays();
//
         customAdapter = new CustomAdapter(Dashboard.this, book_id, book_title,book_author, book_publisher);

//       // customAdapter = new CustomAdapter(Dashboard.this,Dashboard.this, book_id, book_title,book_author, book_publisher);
        bookRecyclerview.setAdapter(customAdapter);
        bookRecyclerview.setLayoutManager(new LinearLayoutManager(Dashboard.this));
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1){
//            recreate();
//        }
    }

     void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
           // empty_imageview.setVisibility(View.VISIBLE);
           // no_data.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()) {
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_publisher.add(cursor.getString(3));
            }
        }
    }
    }
