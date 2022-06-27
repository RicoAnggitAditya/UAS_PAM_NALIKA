package com.example.nalika;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String nama="admin";
    String password="admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText edt_nama=findViewById(R.id.edt_nama);
        EditText edt_password=findViewById(R.id.edt_password);
        Button login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_nama.getText().toString().equalsIgnoreCase(nama)&&edt_password.getText().toString().equalsIgnoreCase(password)){
                    startActivity(new Intent(MainActivity.this,Home.class));
                }else{
                    Toast.makeText(MainActivity.this, "Name atau Passworrd anda Salah", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}