package com.stecon.patipan_on.steconqr;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.config.LocationAccuracy;
import io.nlopez.smartlocation.location.config.LocationParams;
import io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesWithFallbackProvider;
import io.nlopez.smartlocation.location.providers.LocationManagerProvider;


public class MyGPS extends AppCompatActivity implements OnLocationUpdatedListener {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gps);
        textView = (TextView) findViewById(R.id.tvGPS);
        if(SmartLocation.with(this).location().state().locationServicesEnabled()){
            Log.d("locationServicesEnabled => " , "true");
        }else{
            Log.d("locationServicesEnabled => " , "else");
        }

        if(SmartLocation.with(this).location().state().isAnyProviderAvailable()){
            Log.d("isAnyProviderAvailable => " , "true");
        }else{
            Log.d("isAnyProviderAvailable => " , "else");
        }

        if(SmartLocation.with(this).location().state().isGpsAvailable()){
            Log.d("isGpsAvailable => " , "true");
        }else{
            Log.d("isGpsAvailable => " , "else");
        }

        if(SmartLocation.with(this).location().state().isNetworkAvailable()){
            Log.d("isNetworkAvailable => " , "true");
        }else{
            Log.d("isNetworkAvailable => " , "else");
        }

        if(SmartLocation.with(this).location().state().isPassiveAvailable()){
            Log.d("isPassiveAvailable => " , "true");
        }else{
            Log.d("isPassiveAvailable => " , "else");
        }

        if(SmartLocation.with(this).location().state().isMockSettingEnabled()){
            Log.d("isMockSettingEnabled => " , "true");
        }else{
            Log.d("isMockSettingEnabled => " , "else");
        }
    }
        @Override
        protected void onStart() {
            super.onStart();
            if(SmartLocation.with(this).location().state().locationServicesEnabled()) {
                Log.d("process => " , "true" );
                LocationParams params = new LocationParams.Builder()
                        .setAccuracy(LocationAccuracy.HIGH)
                        .setInterval(5000)
                        .build();

                SmartLocation.with(this)
                        .location(new LocationGooglePlayServicesWithFallbackProvider(this))
                        .config(params)
                        .start(this);

            } else {
                Log.d("process => " , "false" );
                locationServiceUnavailabled();
            }
        }



    @Override
        protected void onStop() {
            super.onStop();
            Log.d("process => " , "onStop" );
            SmartLocation.with(this)
                    .location()
                    .stop();
        }

        @Override
        public void onLocationUpdated(Location location) {
            Log.d("process => " , "onLocationUpdated" );
            // TODO Do something when location was updated
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            float accuracy = location.getAccuracy();
            float bearing = location.getBearing();
            String provider = location.getProvider();
            textView.setText(latitude + " \n" + longitude);
        }

    private void locationServiceUnavailabled() {
        // TODO Do something when location service is unavailable
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}