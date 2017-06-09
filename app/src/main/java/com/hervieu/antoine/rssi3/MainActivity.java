package com.hervieu.antoine.rssi3;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0x12345) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mTextView = new TextView(this.getApplicationContext());
        mTextView.setMovementMethod(new ScrollingMovementMethod());

        AirMonitor mAirMonitor = new AirMonitor(this, mTextView);
        mAirMonitor.getWifi();

        GPSMonitor mGPSMonitor = new GPSMonitor(this, mTextView);
        mGPSMonitor.refresh();
        mTextView.setText(mTextView.getText() + "\nLocation : LA " + mGPSMonitor.getLatitude() + ", LO "+ mGPSMonitor.getLongitude());

        this.setContentView(mTextView);
    }
}
