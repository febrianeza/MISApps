package com.kelompok3.misapps;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.kelompok3.misapps.Activity.LoginActivity;
import com.wang.avi.AVLoadingIndicatorView;

public class splashscreen extends AppCompatActivity {

    //pembuatan variabel untuk linearlayout
    private LinearLayout lv_loading;
    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        lv_loading = (LinearLayout) findViewById(R.id.lv_loading);

        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator("BallClipRotateMultipleIndicator");

        //membuat sebuah proses yang ter delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //mendefenisikan Intent activity
                Intent i = new Intent(splashscreen.this, LoginActivity.class);
                startActivity(i);

                //finish berguna untuk mengakhiri activity
                //disini saya menggunakan finish,supaya ketika menekan tombol back
                //tidak kembali pada activity SplashScreen nya
                finish();
            }
            //disini maksud 3000 nya itu adalah lama screen ini terdelay 3 detik,dalam satuan mili second
        }, 5000);
    }


}