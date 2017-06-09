package com.hervieu.antoine.rssi3;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Antoine on 08/06/2017.
 */

public class AirMonitor {

    private WifiManager mWifiManager;
    private MainActivity activity;
    private TextView mTextView;
    public AirMonitor(MainActivity activity, TextView mTextView) {
        this.activity = activity;
        this.mTextView = mTextView;
    }

    // call this method only if you are on 6.0 and up, otherwise call doGetWifi()
    public void getWifi() {
        if (activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0x12345);
        } else {
            mWifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
            activity.registerReceiver(mWifiScanReceiver,
                    new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
            mWifiManager.startScan();
        }
    }

    private final BroadcastReceiver mWifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {

            if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                activity.unregisterReceiver(mWifiScanReceiver); // Remove this line to keep scanning wifi

                List<ScanResult> mScanResults = mWifiManager.getScanResults();
                String actualText = "";
                List<ScanResult> mScanResultsSorted = new ArrayList<ScanResult>();
                List<ScanResult> mScanResultsCopy = new ArrayList<ScanResult>();

                for (ScanResult s : mScanResults) {
                    mScanResultsCopy.add(s);
                }

                while(mScanResults.size()!= mScanResultsSorted.size()) {

                    int indexMin = 0;
                    int sMin = 9999;
                    for (ScanResult s : mScanResultsCopy) {
                        if(s.level < sMin) {
                            sMin = s.level;
                            indexMin = mScanResultsCopy.indexOf(s);
                        }
                    }
                    mScanResultsSorted.add(mScanResultsCopy.get(indexMin));
                    mScanResultsCopy.remove(indexMin);


                }
                Collections.reverse(mScanResultsSorted);
                for (ScanResult s : mScanResultsSorted) {
                    actualText += s.SSID + " " + s.BSSID + " " + s.level + "\n";
                }
                mTextView.setText(mTextView.getText() + "\n" + actualText);


            }
        }
    };

}
