package com.kelompok3.misapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kelompok3.misapps.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import butterknife.BindView;

public class DetailOfficeActivity extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mMapView;
    double latitude;
    double longitude;
    HashMap<String, String> hashMap = new HashMap<>();
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_office);

        //Menampilkan Map
        initGoogleMap(savedInstanceState);

        //Membuat Toolbar
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Office");

        //Mendeklarasikan Textview dan sebagainya
        Intent intent = getIntent();
        hashMap = (HashMap<String, String>) getIntent().getExtras().get("data");
        ImageView img_kantor = (ImageView) findViewById(R.id.img_kantor);
        TextView office_name = (TextView) findViewById(R.id.title);
        TextView description = (TextView) findViewById(R.id.description);
        Button cellphone = (Button) findViewById(R.id.button_phone);
        Button email = (Button) findViewById(R.id.button_email);
        TextView address = (TextView) findViewById(R.id.address);

        //Melakukan SetText untuk ditempatkan pada tempat yang sudah dideklarasikan
        Picasso.get().load(hashMap.get("base_url")).resize(500,500).into(img_kantor);
        office_name.setText(hashMap.get("office_name"));
        description.setText(hashMap.get("office_description"));
        cellphone.setText(hashMap.get("cell_phone"));
        email.setText(hashMap.get("email"));
        address.setText(hashMap.get("office_address"));

        //Memisahkan String location_gps
        String[] latlong =  hashMap.get("location_gps").split(",");
        latitude = Double.parseDouble(latlong[0]);
        longitude = Double.parseDouble(latlong[1]);

        //Menampilkan nomor hp pada layar calldial
        cellphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + hashMap.get("cell_phone")));
                startActivity(intent);
            }
        });

        //Menampilkan email pada layar aplikasi email seperti gmail
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + hashMap.get("email")));
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(intent);
            }
        });
    }

    private void initGoogleMap(Bundle savedInstanceState){
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView = (MapView) findViewById(R.id.user_list_map);
        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Marker"));

        //Melakukan zoom
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude),15));
        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
