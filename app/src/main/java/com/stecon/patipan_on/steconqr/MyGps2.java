package com.stecon.patipan_on.steconqr;

import android.content.Context;
import android.content.Intent;
import android.location.Location;

import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MyGps2 extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener , com.google.android.gms.location.LocationListener{

    private GoogleApiClient googleApiClient;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gps2);

        textView = (TextView) findViewById(R.id.tvGps2);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
            Log.d("MyGPS 2 => ", "onStop => TRUE");
        }else{
            Log.d("MyGPS 2 => ", "onStop => ELSE");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MyGPS 2 => ", "onStart");
        googleApiClient.connect();

    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationAvailability locationAvailability = LocationServices.FusedLocationApi.getLocationAvailability(googleApiClient);
        if (locationAvailability.isLocationAvailable()) {
            Log.d("MyGPS 2 => ", "onConnected => TRUE");
            Toast.makeText(this, "Avliable", Toast.LENGTH_SHORT).show();
            // Call Location Services
            LocationRequest locationRequest = new LocationRequest()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(5000);
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

        } else {
            Log.d("MyGPS 2 => ", "onConnected => ELSE");
            Toast.makeText(this, "UnAviable", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            startActivity(intent);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("MyGPS 2 => ", "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("MyGPS 2 => ", "onConnectionFailed");

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("MyGPS 2 => ", "onLocationChanged");
        double latitude = location.getLatitude();
        double longtitude = location.getLongitude();
        textView.setText(latitude + " \n" + longtitude);
        Log.d("Latitude => ", location.getLatitude() + " ");
    }
}
