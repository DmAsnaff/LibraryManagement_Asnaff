package com.example.librarymanagement_asnaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class publisherUpdate extends AppCompatActivity {

    EditText publisherNameedt, publisherAddressedt, publisherPhoneedt;
    androidx.appcompat.widget.AppCompatButton updatePublisherbtn;
    String id, name, address, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher_update);

        publisherNameedt = findViewById(R.id.publisherNameedt2);
        publisherAddressedt = findViewById(R.id.publisherAddressedt2);
        publisherPhoneedt = findViewById(R.id.publisherPhoneedt2);
        updatePublisherbtn = findViewById(R.id.updatePublisherbtn);
        getWindow().setStatusBarColor(Color.parseColor("#2C3E50"));

        getAndSetIntentData();

        updatePublisherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler myDB = new DBHandler(publisherUpdate.this);
                name = publisherNameedt.getText().toString().trim();
                address = publisherAddressedt.getText().toString().trim();
                phone = publisherPhoneedt.getText().toString().trim();
                //then i should call this
                Boolean upd = myDB.updatepublisherdata(id, name, address, phone);

//                Boolean upd = myDB.addBookdetails(bookTitleedt.getText().toString().trim(),
//                        bookAuthoredt.getText().toString().trim(),
//                        bookPublisheredt.getText().toString().trim());
                if(upd == true){
                    Toast.makeText(publisherUpdate.this, "Publisher Updated Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(publisherUpdate.this, "Publisher Update  Failed!", Toast.LENGTH_SHORT).show();
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
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("address") && getIntent().hasExtra("phone")) {

            //im getting data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            address = getIntent().getStringExtra("address");
            phone = getIntent().getStringExtra("phone");

            //setting that intent data
            publisherNameedt.setText(name);
            publisherAddressedt.setText(address);
            publisherPhoneedt.setText(phone);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();

        }
    }

}