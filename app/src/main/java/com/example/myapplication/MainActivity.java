package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



public class MainActivity extends Activity {

    ImageView imageView;
    Button button;
    Button button2;
    TextView tvLocationLong;
    TextView tvLocationLat;
    private  DBHelper dbHelper;
    private Location location;
    int count=0;
    int currid = 0;


    private LocationManager locationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageview);
        button = findViewById(R.id.button);
        tvLocationLong = (TextView) findViewById(R.id.tvLocationLong);
        tvLocationLat = (TextView) findViewById(R.id.tvLocationLat);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.CAMERA
                }, 100);

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);

            }
        });

       /* button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper myDB = new DBHelper(MainActivity.this);
                com.example.myapplication.Location location = new com.example.myapplication.Location(Float.parseFloat(String.valueOf(tvLocationLat.getText())),Float.parseFloat(String.valueOf(tvLocationLong.getText())));
                myDB.addLocation(location);
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 100);

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000 * 10, 10, locationListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000 * 10, 10,
                locationListener);

    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            showLocationLong(location);
           // showLocationLat(location);
        }


        @Override
        public void onProviderEnabled(String provider) {

            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 100);
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, 100);

            }
            //showLocationLat(locationManager.getLastKnownLocation(provider));
            showLocationLong(locationManager.getLastKnownLocation(provider));
        }


    };

    /*private void showLocationLat(Location location) {
        if (location == null)
            return;
        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
            tvLocationLat.setText(formatLocationLat(location));
            //tvLocationLong.setText(formatLocationLong(location));
        }*/




    private void showLocationLong(Location location) {
        button2 = findViewById(R.id.button2);
        if (location == null)
            return;
        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
            tvLocationLat.setText(formatLocationLat(location));
            tvLocationLong.setText(formatLocationLong(location));
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbHelper = new DBHelper(MainActivity.this);
                    com.example.myapplication.Location location1 = new com.example.myapplication.Location(Float.parseFloat(String.valueOf(formatLocationLat(location))),Float.parseFloat(formatLocationLong(location)));
                    dbHelper.addLocation(location1);

//записать в строку
                }
            });
        }

    }

    private String formatLocationLat(Location location) {
        if (location == null)
            return "";
        return String.format(
                "%1$.4f",
                location.getLatitude());
    }


    private String formatLocationLong(Location location) {
        if (location == null)
            return "";
        return String.format(
                "%1$.4f",
                location.getLongitude());
    }





}