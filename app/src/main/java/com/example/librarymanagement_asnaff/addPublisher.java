package com.example.librarymanagement_asnaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addPublisher extends AppCompatActivity {
    EditText publisherNameeedt, publisherAddressedt, publisherPhoneedt;
    androidx.appcompat.widget.AppCompatButton addPublisherbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_publisher);
        getWindow().setStatusBarColor(Color.parseColor("#2C3E50"));

        publisherNameeedt = findViewById(R.id.publisherNameedt);
        publisherAddressedt = findViewById(R.id.publisherAddressedt);
        publisherPhoneedt = findViewById(R.id.publisherPhoneedt);
        addPublisherbtn = findViewById(R.id.addPublisherbtn);
        addPublisherbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DBHandler myDB = new DBHandler(addPublisher.this);
                Boolean insert = myDB.addPublisherdetails(publisherNameeedt.getText().toString().trim(),
                        publisherAddressedt.getText().toString().trim(),
                        publisherPhoneedt.getText().toString().trim());
                if(insert == true){
                    Toast.makeText(addPublisher.this, "Publisher Added Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),publisherDashboard.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(addPublisher.this, "Publisher Add Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}