package com.project.garvit.navigation;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

/**
 * Created by garvit on 15/10/14.
 */
public class LocationCallbacksListner implements GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener {

    Context context;
    LocationClient locationClient;
    Activity activity;

    LocationCallbacksListner(Activity activity, Context context, LocationClient locationClient) {
        this.context = context;
        this.activity = (MyActivity)activity;
        this.locationClient = locationClient;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
        if(locationClient!= null) {
            Location location = locationClient.getLastLocation();
            String locationString = "" + location.getLatitude() + "," + location.getLongitude();
            TextView textView = (TextView)activity.findViewById(R.id.location);
            textView.setText(locationString);
        }
    }

    @Override
    public void onDisconnected() {
        Toast.makeText(context, "Disconnected. Please re-connect.",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
