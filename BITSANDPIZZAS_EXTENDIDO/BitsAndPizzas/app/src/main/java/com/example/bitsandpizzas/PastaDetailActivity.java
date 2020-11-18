package com.example.bitsandpizzas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PastaDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PASTA_ID = "pastaId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasta_detail);

        //Set the toolbar as the activity's app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Display details of the pizza
        int pastaId = (Integer)getIntent().getExtras().get(EXTRA_PASTA_ID);
        String pastaName = Pasta.pastas[pastaId].getName();
        TextView textView = (TextView)findViewById(R.id.pasta_text);
        textView.setText(pastaName);
        int pastaImage = Pasta.pastas[pastaId].getImageResourceId();
        ImageView imageView = (ImageView)findViewById(R.id.pasta_image);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, pastaImage));
        imageView.setContentDescription(pastaName);
    }
}
