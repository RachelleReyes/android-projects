package com.example.starbuzzbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz"; //Name of our database
    private static final int DB_VERSION = 2; // Version of the database

    StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       /*db.execSQL("CREATE TABLE DRINK( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT, "
                + "DESCRIPTION TEXT,"
                + "IMAGE_RESOURCE_ID INTEGER);");

        insertDrink(db, "Latte", "Espresso and steamed milk", R.drawable.latte);
        insertDrink(db, "Cappuccino", "Espresso, hot milk and steamed-milk foam", R.drawable.cappuccino);
        insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter);


        */
        /*
        db.execSQL("CREATE TABLE FOOD("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT, "
                + "DESCRIPTION TEXT,"
                + "IMAGE_RESOURCE_ID INTEGER);");

        insertFood(db, "Muffin", "Delicious chocolate muffin with chocolate chips", R.drawable.muffin);
        insertFood(db, "Sandwich",  "Breakfast sandwich with chicken and vegetable", R.drawable.sandwich);
        insertFood(db, "Cookies", "Chewy chocolate chip cookies", R.drawable.cookies);


        db.execSQL("CREATE TABLE STORE("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT, "
                + "ADDRESS TEXT,"
                + "HOURS TEXT,"
                + "IMAGE_RESOURCE_ID INTEGER);");

        insertStore(db, "Chula Vista",  "555 Broadway # 147 Chula Vista, CA 91910",  "Monday to Sunday, 5:30 AM to 9:00 PM", R.drawable.chulavista);
        insertStore(db, "San Ysidro",  "4201 Camino De La Plaza #102, San Ysidro, CA 92173, USA", "Monday to Sunday, 4:30 AM to 10:00 PM", R.drawable.sanysidro);
        insertStore(db, "National City",  "404 Mile of Cars Way 101 National City, CA 91950","Monday to Sunday, 4:00 AM to 11:00 PM", R.drawable.nationalcity);
        */

        updateMyDatabase(db,0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db,oldVersion,newVersion);
    }


    private static void insertDrink(SQLiteDatabase db,String name,
                                    String description, int resourceId) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("DRINK", null, drinkValues);
    }

    private static void insertFood(SQLiteDatabase db,String name,
                                   String description, int resourceId) {
        ContentValues foodValues = new ContentValues();
        foodValues.put("NAME", name);
        foodValues.put("DESCRIPTION", description);
        foodValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("FOOD", null, foodValues);
    }

    private static void insertStore(SQLiteDatabase db,String name,
                                    String address, String hours, int resourceId) {
        ContentValues storeValues = new ContentValues();
        storeValues.put("NAME", name);
        storeValues.put("ADDRESS", address);
        storeValues.put("HOURS", hours);
        storeValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("STORE", null, storeValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion<1) {
            db.execSQL("CREATE TABLE DRINK( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT,"
                    + "IMAGE_RESOURCE_ID INTEGER);");

            insertDrink(db, "Latte", "Espresso and steamed milk", R.drawable.latte);
            insertDrink(db, "Cappuccino", "Espresso, hot milk and steamed-milk foam", R.drawable.cappuccino);
            insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter);

            db.execSQL("CREATE TABLE FOOD(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT,"
                    + "IMAGE_RESOURCE_ID INTEGER);");

            insertFood(db, "Muffin", "Delicious chocolate muffin with chocolate chips", R.drawable.muffin);
            insertFood(db, "Sandwich",  "Breakfast sandwich with chicken and vegetable", R.drawable.sandwich);
            insertFood(db, "Cookies", "Chewy chocolate chip cookies", R.drawable.cookies);

            db.execSQL("CREATE TABLE STORE(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT, "
                    + "ADDRESS TEXT,"
                    + "HOURS TEXT,"
                    + "IMAGE_RESOURCE_ID INTEGER);");

            insertStore(db, "Chula Vista",  "555 Broadway # 147 Chula Vista, CA 91910",  "Monday to Sunday, 5:30 AM to 9:00 PM", R.drawable.chulavista);
            insertStore(db, "San Ysidro",  "4201 Camino De La Plaza #102, San Ysidro, CA 92173, USA", "Monday to Sunday, 4:30 AM to 10:00 PM", R.drawable.sanysidro);
            insertStore(db, "National City",  "404 Mile of Cars Way 101 National City, CA 91950","Monday to Sunday, 4:00 AM to 11:00 PM", R.drawable.nationalcity);

        }
        if(oldVersion<2) {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
            db.execSQL("ALTER TABLE FOOD ADD COLUMN FAVORITE NUMERIC;");
            db.execSQL("ALTER TABLE STORE ADD COLUMN FAVORITE NUMERIC;");
        }
    }
}
