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

public class StoreActivity extends AppCompatActivity {
    public static final String EXTRA_STOREID = "storeId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        //Get the store from the intent
        int storeId = (Integer)getIntent().getExtras().get(EXTRA_STOREID);

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("STORE",
                    new String[] {"NAME","ADDRESS","HOURS","IMAGE_RESOURCE_ID","FAVORITE"},
                    "_id = ?",
                    new String[] {Integer.toString(storeId)},
                    null,null,null);


            if(cursor.moveToFirst()) {
                //Get the store details from the cursor
                String nameText = cursor.getString(0);
                String addressText = cursor.getString(1);
                String hoursText = cursor.getString(2);
                int photoId = cursor.getInt(3);
                boolean isFavorite = (cursor.getInt(4)==1);

                //Populate the store name
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);
                //Populate the store description
                TextView address = (TextView)findViewById(R.id.address);
                address.setText(addressText);
                //Populate the store hours
                TextView hours = (TextView)findViewById(R.id.hours);
                hours.setText(hoursText);
                //Populate the drink image
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
        int storeId = (Integer) getIntent().getExtras().get(EXTRA_STOREID);
        new UpdateStoreTask().execute(storeId);
    }

    public class UpdateStoreTask extends AsyncTask<Integer, Void, Boolean> {
        private ContentValues storeValues;

        protected void onPreExecute() {
            //Get the value of the checkbox
            CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
            storeValues = new ContentValues();
            storeValues.put("FAVORITE", favorite.isChecked());
        }

        protected Boolean doInBackground(Integer... stores) {
            int storeId = stores[0];
            SQLiteOpenHelper starbuzzDatabaseHelper =
                    new StarbuzzDatabaseHelper(StoreActivity.this);
            try {
                SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase();
                db.update("STORE", storeValues,
                        "_id = ?", new String[] {Integer.toString(storeId)});
                db.close();
                return true;
            } catch(SQLiteException e) {
                return false;
            }
        }
        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast toast = Toast.makeText(StoreActivity.this,
                        "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
