package com.example.starbuzzbd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FoodActivity extends AppCompatActivity {

    public static final String EXTRA_FOODID = "foodId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        //Get the drink from the intent
        int foodId = (Integer)getIntent().getExtras().get(EXTRA_FOODID);

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("FOOD",
                    new String[] {"NAME","DESCRIPTION","IMAGE_RESOURCE_ID","FAVORITE"},
                    "_id = ?",
                    new String[] {Integer.toString(foodId)},
                    null,null,null);


            if(cursor.moveToFirst()) {
                //Get the food details from the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3)==1);

                //Populate the food name
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);
                //Populate the food description
                TextView description = (TextView)findViewById(R.id.description);
                description.setText(descriptionText);
                //Populate the foodimage
                ImageView photo = (ImageView)findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                //Populate the favorite checkbox
                CheckBox favorite = (CheckBox)findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);
            }
            cursor.close();
            db.close();
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this,"Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    //Update the database when the checkbox is clicked
    public void onFavoriteClicked(View view){
        int foodId = (Integer) getIntent().getExtras().get(EXTRA_FOODID);
        new UpdateFoodTask().execute(foodId);
    }

    public class UpdateFoodTask extends AsyncTask<Integer, Void, Boolean> {
        private ContentValues foodValues;

        protected void onPreExecute() {
            //Get the value of the checkbox
            CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
            foodValues = new ContentValues();
            foodValues.put("FAVORITE", favorite.isChecked());
        }

        protected Boolean doInBackground(Integer... foods) {
            int foodId = foods[0];
            SQLiteOpenHelper starbuzzDatabaseHelper =
                    new StarbuzzDatabaseHelper(FoodActivity.this);
            try {
                SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase();
                db.update("FOOD", foodValues,
                        "_id = ?", new String[] {Integer.toString(foodId)});
                db.close();
                return true;
            } catch(SQLiteException e) {
                return false;
            }
        }
        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast toast = Toast.makeText(FoodActivity.this,
                        "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
