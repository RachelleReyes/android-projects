package com.example.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StoreActivity extends Activity {
    public static final String EXTRA_STOREID = "storeId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        //Get the store from the intent
        int storeId = (Integer)getIntent().getExtras().get(EXTRA_STOREID);
        Store store = Store.store[storeId];

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("STORE",
                    new String[] {"NAME","ADDRESS","HOURS","IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[] {Integer.toString(storeId)},
                    null,null,null);


            if(cursor.moveToFirst()) {
                //Get the store details from the cursor
                String nameText = cursor.getString(0);
                String addressText = cursor.getString(1);
                String hoursText = cursor.getString(2);
                int photoId = cursor.getInt(3);

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
            }
            cursor.close();
            db.close();
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this,"Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        /*
        //Populate the store name
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(store.getName());

        //Populate the store address
        TextView address = (TextView)findViewById(R.id.address);
        address.setText(store.getAddress());

        //Populate the store hours
        TextView hours = (TextView)findViewById(R.id.hours);
        hours.setText(store.getHours());

        //Populate the store image
        ImageView photo = (ImageView)findViewById(R.id.photo);
        photo.setImageResource(store.getImageResourceId());
        photo.setContentDescription(store.getName());
         */
    }
}
