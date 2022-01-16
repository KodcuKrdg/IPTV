package com.example.coffeeshop;

public class Product {

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductMoney() {
        return ProductMoney;
    }

    public void setProductMoney(String productMoney) {
        ProductMoney = productMoney;
    }


    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        TotalMoney = totalMoney;
    }

    private String UserId;
    private String ProductId;
    private String ProductMoney;
    private String Number;
    private String TotalMoney;

    public  Product(){
        UserId ="";
        ProductId ="";
        ProductMoney ="0";
        Number ="0";
        TotalMoney ="0";
    }

    public Product(String userId, String productId, String productMoney, String number, String totalMoney) {
        UserId = userId;
        ProductId = productId;
        ProductMoney = productMoney;
        Number = number;
        TotalMoney = totalMoney;
    }



}
