package com.hervieu.antoine.rssi3;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * Created by Antoine on 08/06/2017.
 */

class GPSMonitor {

    private MainActivity activity;
    private LocationManager mLocationManager;
    private Location loc;
    public GPSMonitor(MainActivity activity, TextView mTextView) {
        this.activity = activity;
        mLocationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

    }

    public void refresh() {

        if (activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED || activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0x12345);        }
        //Altitude is not supported on my phone
        this.loc =  mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    }

    public double getLongitude(){
        return this.loc.getLongitude();
    }

    public double getLatitude(){
        return this.loc.getLatitude();
    }



}
