package com.example.starbuzzbd;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TopLevelActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor drinkFavoritesCursor;
    private Cursor foodFavoritesCursor;
    private Cursor storeFavoritesCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        setupOptionsListView();
        setupDrinkFavoritesListView();
        setupFoodFavoritesListView();
        setupStoreFavoritesListView();
    }

    private void setupOptionsListView() {
        //Create an OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> listView,
                                    View itemView,
                                    int position,
                                    long id) {
                if (position == 0) {
                    Intent intent = new Intent(TopLevelActivity.this,
                            DrinkCategoryActivity.class);
                    startActivity(intent);
                }else if (position == 1) {
                    Intent intent = new Intent(TopLevelActivity.this,
                            FoodCategoryActivity.class);
                    startActivity(intent);
                }else if (position == 2) {
                    Intent intent = new Intent(TopLevelActivity.this,
                            StoreCategoryActivity.class);
                    startActivity(intent);
                }
            }
        };
        //Add the listener to the list view
        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }

    private void setupDrinkFavoritesListView() {
        //Populate the list_favorites ListView from a cursor
        ListView listDrinkFavorites = (ListView) findViewById(R.id.list_drinkfavorites);
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();
            drinkFavoritesCursor = db.query("DRINK",
                    new String[]{"_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);

            CursorAdapter favoriteAdapter =
                    new SimpleCursorAdapter(TopLevelActivity.this,
                            android.R.layout.simple_list_item_1,
                            drinkFavoritesCursor,
                            new String[]{"NAME"},
                            new int[]{android.R.id.text1}, 0);
            listDrinkFavorites.setAdapter(favoriteAdapter);
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        //Navigate to DrinkActivity if a drink is clicked
        listDrinkFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int)id);
                startActivity(intent);
            }
        });
    }

    private void setupFoodFavoritesListView() {
        //Populate the list_favorites ListView from a cursor
        ListView listFoodFavorites = (ListView) findViewById(R.id.list_foodfavorites);
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();
            foodFavoritesCursor = db.query("FOOD",
                    new String[]{"_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);

            CursorAdapter favoriteAdapter =
                    new SimpleCursorAdapter(TopLevelActivity.this,
                            android.R.layout.simple_list_item_1,
                            foodFavoritesCursor,
                            new String[]{"NAME"},
                            new int[]{android.R.id.text1}, 0);
            listFoodFavorites.setAdapter(favoriteAdapter);
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        //Navigate to DrinkActivity if a drink is clicked
        listFoodFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this, FoodActivity.class);
                intent.putExtra(FoodActivity.EXTRA_FOODID, (int)id);
                startActivity(intent);
            }
        });
    }

    private void setupStoreFavoritesListView() {
        //Populate the list_favorites ListView from a cursor
        ListView listStoreFavorites = (ListView) findViewById(R.id.list_storefavorites);
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();
            storeFavoritesCursor = db.query("STORE",
                    new String[]{"_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);

            CursorAdapter favoriteAdapter =
                    new SimpleCursorAdapter(TopLevelActivity.this,
                            android.R.layout.simple_list_item_1,
                            storeFavoritesCursor,
                            new String[]{"NAME"},
                            new int[]{android.R.id.text1}, 0);
            listStoreFavorites.setAdapter(favoriteAdapter);
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        //Navigate to DrinkActivity if a drink is clicked
        listStoreFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this, StoreActivity.class);
                intent.putExtra(StoreActivity.EXTRA_STOREID, (int)id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Cursor drinkCursor = db.query("DRINK",
                new String[]{"_id", "NAME"},
                "FAVORITE = 1",
                null, null, null, null);
        ListView listDrinkFavorites = (ListView) findViewById(R.id.list_drinkfavorites);
        CursorAdapter drinkAdapter = (CursorAdapter) listDrinkFavorites.getAdapter();
        drinkAdapter.changeCursor(drinkCursor);
        drinkFavoritesCursor = drinkCursor;

        Cursor foodCursor = db.query("FOOD",
                new String[]{"_id", "NAME"},
                "FAVORITE = 1",
                null, null, null, null);
        ListView listFoodFavorites = (ListView) findViewById(R.id.list_foodfavorites);
        CursorAdapter foodAdapter = (CursorAdapter) listFoodFavorites.getAdapter();
        foodAdapter.changeCursor(foodCursor);
        foodFavoritesCursor = foodCursor;

        Cursor storeCursor = db.query("STORE",
                new String[]{"_id", "NAME"},
                "FAVORITE = 1",
                null, null, null, null);
        ListView listStoreFavorites = (ListView) findViewById(R.id.list_storefavorites);
        CursorAdapter storeAdapter = (CursorAdapter) listStoreFavorites.getAdapter();
        storeAdapter.changeCursor(storeCursor);
        storeFavoritesCursor = storeCursor;
    }

    //Close the cursor and database in the onDestroy() method
    @Override
    public void onDestroy(){
        super.onDestroy();
        drinkFavoritesCursor.close();
        foodFavoritesCursor.close();
        storeFavoritesCursor.close();
        db.close();
    }

}
