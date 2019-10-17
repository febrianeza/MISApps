package com.kelompok3.misapps.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.kelompok3.misapps.R;
import android.util.DisplayMetrics;

import butterknife.ButterKnife;
import android.widget.MediaController;

/**
 * A simple {@link Fragment} subclass.
 */
public class Galery_Video extends Fragment {
    MediaController media_Controller;
    DisplayMetrics dm;
    WebView webViewSaya;
    public Galery_Video() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_galeri_video, container, false);
        webViewSaya = (WebView) rootView.findViewById(R.id.webViewSaya);
        // inisialisasi

        // setting
        webViewSaya.setWebViewClient(new WebViewClient());
        webViewSaya.setWebChromeClient(new WebChromeClient());
        webViewSaya.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webViewSaya.getSettings().setJavaScriptEnabled(true);
        webViewSaya.getSettings().setPluginState(WebSettings.PluginState.ON);
        webViewSaya.getSettings().setDefaultFontSize(18);

        final Button button = (Button) rootView.findViewById(R.id.btnVideo1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                muatVideo("MN0Adv1nfz4");
            }
        });

       final Button button2 = (Button) rootView.findViewById(R.id.btnVideo2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                muatVideo("BuLI2JfH8iQ");
            }
        });

        final Button button3 = (Button) rootView.findViewById(R.id.btnVideo3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                muatVideo("-v88fGdqYHE");
            }
        });

        final Button button4 = (Button) rootView.findViewById(R.id.btnVideo4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                muatVideo("x9ROVUpPeQY");
            }
        });

        final Button button5 = (Button) rootView.findViewById(R.id.btnVideo5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                muatVideo("ucyl9MRh2Us");
            }
        });

        final Button button6 = (Button) rootView.findViewById(R.id.btnVideo6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                muatVideo("mc6r74urADY");
            }
        });

        final Button button7 = (Button) rootView.findViewById(R.id.btnVideo7);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                muatVideo("eqXh8Vjepx4");
            }
        });

        final Button button8 = (Button) rootView.findViewById(R.id.btnVideo8);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                muatVideo("0PJbZm4LABQ");
            }
        });

        final Button button9 = (Button) rootView.findViewById(R.id.btnVideo9);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                muatVideo("fPYfAyZraFk");
            }
        });

        return rootView;
    }




    private void muatVideo (String kode_youtube){
        String kodeHTML = "<head></head><body>" +
                "<iframe width=\"355\" height=\"261\" src=\"https://www.youtube.com/embed/" +
                kode_youtube +
                "\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>" +
                "</body>";
        webViewSaya.loadData(kodeHTML, "text/html; charset=utf-8", null);
    }


        @Override
        public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);
            ButterKnife.bind(this, view);



        }


        // cari id di layout



    }

