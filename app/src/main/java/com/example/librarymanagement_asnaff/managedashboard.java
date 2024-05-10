package com.example.librarymanagement_asnaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.librarymanagement_asnaff.databinding.ActivityManagedashboardBinding;
import com.example.librarymanagement_asnaff.databinding.ActivityRegisterPageBinding;


public class managedashboard extends AppCompatActivity {

    ActivityManagedashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managedashboard);

        binding = ActivityManagedashboardBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getWindow().setStatusBarColor(Color.parseColor("#2C3E50"));


        binding.mgtBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(managedashboard.this, Dashboard.class);
                startActivity(intent);
            }
        });

    }
}

