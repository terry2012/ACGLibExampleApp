package com.example.listeners;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import com.acg.lib.listeners.ResourceAvailabilityListener;
import com.example.activities.EvilLocationActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Outside listener for a location ACG
 */
public class LocationACGOutsideListener implements CompoundButton.OnCheckedChangeListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    protected EvilLocationActivity evilLocationActivity;
    protected GoogleApiClient googleApiClient;

    public LocationACGOutsideListener(EvilLocationActivity evilLocationActivity) {
        this.evilLocationActivity = evilLocationActivity;
        buildGoogleApiClient(evilLocationActivity);
    }

    /**
     * Create an instance of the Google API Client
     */
    private synchronized void buildGoogleApiClient(Context context) {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("LOCATION_ACG_OUTSIDE", Log.getStackTraceString(new Throwable()));

        if (isChecked) {
            googleApiClient.connect();
        } else {
            googleApiClient.disconnect();
        }
    }

    /**
     * Connection callbacks for the underlying location API, circumventing the ACG callbacks
     */
    @Override
    public void onConnected(Bundle bundle) {
        evilLocationActivity.buildACGListeners().getResourceListenerForACG(evilLocationActivity.locationACG).onResourceReady();
    }

    @Override
    public void onConnectionSuspended(int i) {
        ((ResourceAvailabilityListener) evilLocationActivity.buildACGListeners().getResourceListenerForACG(evilLocationActivity.locationACG)).onResourceUnavailable();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        throw new RuntimeException("Connection failed");
    }

    public Location getResource() {
        if (!googleApiClient.isConnected()) {
            throw new RuntimeException("You can't get the location unless the user says you can");
        }

        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if(location == null) {
            throw new RuntimeException("Whoever designed the android location API decided all exceptions -> null which is awful");
        }

        return location;
    }

    /**
     * Even evil activities should clean up after themselves
     */
    public void cleanUp() {
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }
}
