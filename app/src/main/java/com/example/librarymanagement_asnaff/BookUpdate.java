package com.example.librarymanagement_asnaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BookUpdate extends AppCompatActivity {

    EditText bookTitleedt, bookAuthoredt, bookPublisheredt;
    androidx.appcompat.widget.AppCompatButton update_button;
    String id, title, author, publisher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_update);

        bookTitleedt = findViewById(R.id.bookTitleedt2);
        bookAuthoredt = findViewById(R.id.bookAuthoredt2);
        bookPublisheredt = findViewById(R.id.bookPublisheredt2);
        update_button = findViewById(R.id.update_button);
        getWindow().setStatusBarColor(Color.parseColor("#2C3E50"));

        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler myDB = new DBHandler(BookUpdate.this);
                title = bookTitleedt.getText().toString().trim();
                author = bookAuthoredt.getText().toString().trim();
                publisher = bookPublisheredt.getText().toString().trim();
                //then i should call this
                Boolean upd = myDB.updateBookdata(id, title, author, publisher);

//                Boolean upd = myDB.addBookdetails(bookTitleedt.getText().toString().trim(),
//                        bookAuthoredt.getText().toString().trim(),
//                        bookPublisheredt.getText().toString().trim());
                if(upd == true){
                    Toast.makeText(BookUpdate.this, "Book Updated Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(BookUpdate.this, "Book Update  Failed!", Toast.LENGTH_SHORT).show();
                }


            }
        });
        //first i should call this




        //update_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
    //    getAndSetIntentData();
//        DBHandler myDB = new DBHandler(BookUpdate.this);
//
//        myDB.updateBookdata(id, title, author, publisher);
    }


    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("publisher")) {

            //im getting data from intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            publisher = getIntent().getStringExtra("publisher");

            //setting that intent data
            bookTitleedt.setText(title);
            bookAuthoredt.setText(author);
            bookPublisheredt.setText(publisher);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();

        }
    }

}