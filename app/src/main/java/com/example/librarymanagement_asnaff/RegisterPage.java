package com.example.librarymanagement_asnaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.librarymanagement_asnaff.databinding.ActivityRegisterPageBinding;

public class RegisterPage extends AppCompatActivity {

    ActivityRegisterPageBinding binding;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterPageBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        dbHandler = new DBHandler(this);

        getWindow().setStatusBarColor(Color.parseColor("#2C3E50"));
        setVariable();
    }

    private void setVariable() {

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname= binding.registerName.getText().toString();
                String email = binding.registerEmail.getText().toString();
                String nic = binding.registerNic.getText().toString();
                String password = binding.registerPassword.getText().toString();
                String confirmPassword = binding.registerConpassword.getText().toString();
                if(email.equals("")||password.equals("")||confirmPassword.equals(""))
                    Toast.makeText(RegisterPage.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else{
                    if(password.equals(confirmPassword)){
                        Boolean checkUserEmail = dbHandler.checkEmail(email);
                        if(checkUserEmail == false){
                            Boolean insert = dbHandler.insertData(fullname,email, nic,password);
                            if(insert == true){
                                Toast.makeText(RegisterPage.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterPage.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterPage.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterPage.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                startActivity(intent);
            }
        });


    }
}