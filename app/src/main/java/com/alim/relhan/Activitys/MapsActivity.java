package com.alim.relhan.Activitys;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.alim.relhan.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;

    private ImageView img_back;

    private String title;
    private double cor1;
    private double cor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(this);

        Intent intent = getIntent();

        title = intent.getStringExtra("Title");
        cor1 = Double.valueOf(intent.getStringExtra("COR1"));
        cor2 = Double.valueOf(intent.getStringExtra("COR2"));

        Log.d("Testlog","COrd1: "+String.valueOf(cor1));
        Log.d("Testlog","COrd2: "+String.valueOf(cor2));

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(cor1 ,cor2);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(title);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 30.0f));
        mMap.addMarker(markerOptions).showInfoWindow();
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.img_back)
        {
            onBackPressed();
        }
    }
}
