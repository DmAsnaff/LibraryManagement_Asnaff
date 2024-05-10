package com.example.librarymanagement_asnaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addBook extends AppCompatActivity {

    EditText bookTitleedt, bookAuthoredt, bookPublisheredt;
    androidx.appcompat.widget.AppCompatButton addBookbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        getWindow().setStatusBarColor(Color.parseColor("#2C3E50"));


        bookTitleedt = findViewById(R.id.bookTitleedt);
        bookAuthoredt = findViewById(R.id.bookAuthoredt);
        bookPublisheredt = findViewById(R.id.bookPublisheredt);
        addBookbtn = findViewById(R.id.addBookbtn);
        addBookbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DBHandler myDB = new DBHandler(addBook.this);
                Boolean insert = myDB.addBookdetails(bookTitleedt.getText().toString().trim(),
                        bookAuthoredt.getText().toString().trim(),
                        bookPublisheredt.getText().toString().trim());
                if(insert == true){
                    Toast.makeText(addBook.this, "Book Added Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(addBook.this, "Book Add Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

