package com.example.mapp;

import androidx.fragment.app.FragmentActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Marker previousMarker = null;
    ImageView img;

    private GoogleMap mMap;
    MediaPlayer mp = new MediaPlayer();
    //private GoogleMap mMap;
    ArrayList<LatLng> arrayList = new ArrayList<LatLng>();
    LatLng TechPark = new LatLng(31.484532, 74.282059);
    //LatLng TechPark = new LatLng(31.484532, 74.282059);
    LatLng G1Market = new LatLng(31.475998336260865, 74.28054189746386);
    LatLng FoodStreet = new LatLng(31.586964, 74.311172);
    LatLng AvianChowk = new LatLng(31.52665803074557, 74.30243250298791);
    LatLng BadshahiMosque = new LatLng(31.58826797508561, 74.3107565128122);
    LatLng MughalEAzam = new LatLng(31.499894494899436, 74.31718826862824);
    LatLng SafariPark = new LatLng(31.38085052945682, 74.2182716078697);
//31.38085052945682, 74.2182716078697
    ArrayList<String> title = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mp = MediaPlayer.create(this, R.raw.mughaleazam);
        img= findViewById(R.id.imageView);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        arrayList.add(TechPark);
        arrayList.add(G1Market);
        arrayList.add(AvianChowk);
        arrayList.add(BadshahiMosque);
        arrayList.add(MughalEAzam);
        arrayList.add(SafariPark);
        title.add("Tech Park");
        title.add("G1Market");
        title.add("AvianChowk");
        title.add("BadshahiMosque");
        title.add("MughalEAzam");
        title.add("SafariPark");

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

        for(int i=0;i<arrayList.size();i++)
        {

            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));


        }

        int i=0;
        for(int j=0;j<title.size();j++)
        {
            mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(title.get(j))));
            i++;

        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String markertitle= marker.getTitle();
                Log.d("marker", "************************onMarkerClick: "+ markertitle);

                //if(markertitle == "Tech Park")
                //{
                  //  mp.start();
                //}

                if(markertitle.equals("MughalEAzam"))
                {
                    img.setImageResource(R.mipmap.mughlaazam);
                    mp.start();
                }
                else
                {
                    img.setVisibility(View.INVISIBLE);

                }


                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15));

                String locAddress = marker.getTitle();
                //fillTextViews(locAddress);
                if (previousMarker != null) {
                    previousMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                previousMarker = marker;

                return true;







               // return false;
            }
        });



    }
}