package com.example.bitsandpizzas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreDetailActivity extends AppCompatActivity {

    public static final String EXTRA_STORE_ID = "storeId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);

        //Set the toolbar as the activity's app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Display details of the pizza
        int storeId = (Integer)getIntent().getExtras().get(EXTRA_STORE_ID);
        String storeName = Store.stores[storeId].getName();
        TextView textView = (TextView)findViewById(R.id.store_text);
        textView.setText(storeName);
        int storeImage = Store.stores[storeId].getImageResourceId();
        ImageView imageView = (ImageView)findViewById(R.id.store_image);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, storeImage));
        imageView.setContentDescription(storeName);
    }
}
