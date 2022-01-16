package com.example.coffeeshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OrdersPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_page);

        findViewById(R.id.home).setOnClickListener(this::Home);
        findViewById(R.id.sendOrders).setOnClickListener(this::SendOrders);
        findViewById(R.id.delete).setOnClickListener(this::DeleteOrdersDatabase);

        SetOrder();
    }

    public  void Home(View view){
        Intent intent = new Intent(OrdersPage.this, Index.class);
        startActivity(intent);
    }

    public void SetOrder(){

        Product[] products = new Product[3];
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        products = db.GetProducts();

        TextView productMoney1 =(TextView)findViewById(R.id.productMoney1);
        TextView productNumber1 =(TextView)findViewById(R.id.productNumber1);
        TextView productTotalMoney1 =(TextView)findViewById(R.id.productTotalMoney1);

        productMoney1.setText("Fiyatı: "+products[0].getProductMoney()+" Tl");
        productNumber1.setText("Adet: "+products[0].getNumber()+" Tl");
        productTotalMoney1.setText("Toplam Fiyat: "+products[0].getTotalMoney()+" Tl");

        TextView productMoney2 =(TextView)findViewById(R.id.productMoney2);
        TextView productNumber2 =(TextView)findViewById(R.id.productNumber2);
        TextView productTotalMoney2=(TextView)findViewById(R.id.productTotalMoney2);

        productMoney2.setText("Fiyatı: "+products[1].getProductMoney()+" Tl");
        productNumber2.setText("Adet: "+products[1].getNumber()+" Tl");
        productTotalMoney2.setText("Toplam Fiyat: "+products[1].getTotalMoney()+" Tl");

        TextView productMoney3 =(TextView)findViewById(R.id.productMoney3);
        TextView productNumber3 =(TextView)findViewById(R.id.productNumber3);
        TextView productTotalMoney3 =(TextView)findViewById(R.id.productTotalMoney3);

        productMoney3.setText("Fiyatı: "+products[2].getProductMoney()+" Tl");
        productNumber3.setText("Adet: "+products[2].getNumber()+" Tl");
        productTotalMoney3.setText("Toplam Fiyat: "+products[2].getTotalMoney()+" Tl");

        TextView totalOrderMoney =(TextView)findViewById(R.id.totalOrderMoney);

        int totalMoney = 0;

        for (Product product:products) {
            totalMoney+=Integer.parseInt(product.getTotalMoney());
        }

        totalOrderMoney.setText("Toplam Tutar: " + totalMoney +" Tl");


    }

    public void SendOrders(View view){

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        try {
            db.SendOrders();
            Toast toast = Toast.makeText(getApplicationContext(), "Sipraiş Verildi",Toast.LENGTH_LONG);

            toast.show();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(), "Sipraiş Verilirken hata oluştu",Toast.LENGTH_LONG);

            toast.show();
        }
        Delete();
    }
    public void DeleteOrdersDatabase(View view){
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        db.DeleteOrders();
        Delete();
    }

    public void Delete(){
        TextView productMoney1 =(TextView)findViewById(R.id.productMoney1);
        TextView productNumber1 =(TextView)findViewById(R.id.productNumber1);
        TextView productTotalMoney1 =(TextView)findViewById(R.id.productTotalMoney1);
        productMoney1.setText("Fiyat: 0 Tl");
        productNumber1.setText("Adet: 0 Tl");
        productTotalMoney1.setText("Toplam Fiyat: 0 Tl");

        TextView productMoney2 =(TextView)findViewById(R.id.productMoney2);
        TextView productNumber2 =(TextView)findViewById(R.id.productNumber2);
        TextView productTotalMoney2=(TextView)findViewById(R.id.productTotalMoney2);
        productMoney2.setText("Fiyat: 0 Tl");
        productNumber2.setText("Adet:0 Tl");
        productTotalMoney2.setText("Toplam Fiyat: 0 Tl");

        TextView productMoney3 =(TextView)findViewById(R.id.productMoney3);
        TextView productNumber3 =(TextView)findViewById(R.id.productNumber3);
        TextView productTotalMoney3 =(TextView)findViewById(R.id.productTotalMoney3);
        productMoney3.setText("Fiyat: 0 Tl");
        productNumber3.setText("Adet: 0 Tl");
        productTotalMoney3.setText("Toplam Fiyat: 0 Tl");

        TextView totalOrderMoney =(TextView)findViewById(R.id.totalOrderMoney);
        totalOrderMoney.setText("Toplam Fiyat: 0 Tl");

        Toast toast = Toast.makeText(getApplicationContext(), "Sepet Silindi",Toast.LENGTH_LONG);
        toast.show();
    }
}