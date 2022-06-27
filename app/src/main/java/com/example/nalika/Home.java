package com.example.nalika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button b_buy1, b_buy2, b_buy3, b_buy4, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        b_buy1 = findViewById(R.id.b_buy1);
        b_buy2 = findViewById(R.id.b_buy2);//berfungsi menghubungkan dengan button yang digunakan pada layout xml
        b_buy3 = findViewById(R.id.b_buy3);
        b_buy4 = findViewById(R.id.b_buy4);
        data = findViewById(R.id.data);

        data.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), TampilanOrder.class);
            startActivity(intent);

        });

        b_buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DetailOrder.class);
                startActivity(i);
            }
        });

        b_buy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DetailOrder.class);
                startActivity(i);
            }
        });

        b_buy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DetailOrder.class);
                startActivity(i);
            }
        });

        b_buy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DetailOrder.class);
                startActivity(i);
            }
        });

    }
}