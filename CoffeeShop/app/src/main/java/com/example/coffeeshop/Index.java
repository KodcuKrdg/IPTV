package com.example.coffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Index extends AppCompatActivity {
    private String email ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        email = getIntent().getStringExtra("email");

        findViewById(R.id.add1).setOnClickListener(this::Add1);
        findViewById(R.id.add2).setOnClickListener(this::Add2);
        findViewById(R.id.add3).setOnClickListener(this::Add3);
        findViewById(R.id.orderPages).setOnClickListener(this::OrderPages);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        db.DeleteOrders();

    }


    public void OrderPages(View view) {
        Intent intent = new Intent(Index.this, OrdersPage.class);

        startActivity(intent);
    }


    public void Add1(View view){

        EditText numberText = (EditText)findViewById(R.id.editTextNumberSigned1);

        String userId="email";
        String productId = "0";
        String productMoney = "45";
        String number = numberText.getText().toString().trim();
        String totalMoney = String.valueOf((Integer.parseInt(number) * Integer.parseInt(productMoney)));

        Product product = new Product(userId,productId,productMoney,number,totalMoney);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        try {
            db.AddOrder(product);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Toast toast = Toast.makeText(getApplicationContext(), "Sepete Eklendi",Toast.LENGTH_SHORT);
        toast.show();
    }
    public void Add2(View view){

        EditText numberText = (EditText)findViewById(R.id.editTextNumberSigned2);

        String userId="email";
        String productId = "1";
        String productMoney = "30";
        String number = numberText.getText().toString().trim();
        String totalMoney = String.valueOf((Integer.parseInt(number) * Integer.parseInt(productMoney)));

        Product product = new Product(userId,productId,productMoney,number,totalMoney);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        try {
            db.AddOrder(product);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Toast toast = Toast.makeText(getApplicationContext(), "Sepete Eklendi",Toast.LENGTH_SHORT);
        toast.show();
    }
    public void Add3(View view){
        EditText numberText = (EditText)findViewById(R.id.editTextNumberSigned3);

        String userId="email";
        String productId = "2";
        String productMoney = "120";
        String number = numberText.getText().toString().trim();
        String totalMoney = String.valueOf((Integer.parseInt(number) * Integer.parseInt(productMoney)));

        Product product = new Product(userId,productId,productMoney,number,totalMoney);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        try {
            db.AddOrder(product);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Toast toast = Toast.makeText(getApplicationContext(), "Sepete Eklendi",Toast.LENGTH_SHORT);
        toast.show();
    }
}