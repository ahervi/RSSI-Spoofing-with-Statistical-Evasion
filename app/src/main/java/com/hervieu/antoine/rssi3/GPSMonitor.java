package com.hervieu.antoine.rssi3;


import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Antoine on 08/06/2017.
 */

class GPSMonitor {
    private GoogleApiClient mGoogleApiClient;
    private MainActivity activity;
    private Location mLastLocation;
    public static double LAT;
    public static double LON;
    public GPSMonitor(MainActivity activity) {
        this.activity = activity;
    }


}
