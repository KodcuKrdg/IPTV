package com.example.coffeeshop;

import android.provider.BaseColumns;

public class TablesInfo {

    public static final class UserEntry implements BaseColumns{
        public static final String TABLE_NAME = "users";

        public static final String COLUMN_ID = "user_id";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
    }

    public static final class OrderEntry implements BaseColumns{
        public static final String TABLE_NAME = "orders";

        public static final String Order_ID = "Order_id";
        public static final String USER_ID = "user_id";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_MONEY = "product_money";
        public static final String NUMBER = "number";
        public static final String TOTAL_MONEY = "total_money";
    }

    public static final class SendOrderEntry implements BaseColumns{
        public static final String TABLE_NAME = "sendorders";

        public static final String SENDORDER_ID = "send_order_id";
        public static final String USER_ID = "user_id";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_MONEY = "product_money";
        public static final String NUMBER = "number";
        public static final String TOTAL_MONEY = "total_money";
    }
}
