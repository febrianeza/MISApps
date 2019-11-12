package com.kelompok3.misapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.kelompok3.misapps.Adapter.ImageAdapter;
import com.kelompok3.misapps.R;

public class singleimage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleimage);

        int imId = this.getIntent().getExtras().getInt("posisi");
        ImageView iv = (ImageView) findViewById(R.id.singleimage);
        int image = ImageAdapter.mThumbIds[imId];
        iv.setImageResource(image);
    }
}
