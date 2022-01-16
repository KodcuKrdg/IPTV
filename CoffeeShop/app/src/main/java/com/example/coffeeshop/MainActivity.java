package com.example.coffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        findViewById(R.id.singnIn).setOnClickListener(this::SignIn);
        findViewById(R.id.login).setOnClickListener(this::Login);

    }

    public void SignIn(View view) {

        DatabaseHelper db =new DatabaseHelper(getApplicationContext());
        db.AddUser(email.getText().toString(), password.getText().toString());
        Intent intent = new Intent(MainActivity.this, Index.class);
        intent.putExtra("email",email.getText().toString());
        startActivity(intent);
    }

    public void Login(View view) {
        DatabaseHelper db =new DatabaseHelper(getApplicationContext());
        boolean result = db.Login(email.getText().toString().trim(), password.getText().toString().trim());

        if (result){
            Intent intent = new Intent(MainActivity.this, Index.class);
            intent.putExtra("email",email.getText().toString());
            startActivity(intent);
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Email ve ya Şifre Hatalı",Toast.LENGTH_LONG);
            toast.show();
        }
    }

}