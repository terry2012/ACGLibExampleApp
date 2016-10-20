package com.example.activities.sparta;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.acg.EvilApp.R;
import com.acg.lib.model.Location;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Simple bad activity that calls the location service directly instead of using the ACG at all.
 * This tries to obfuscate the call by wrapping it in the Location wrapper.
 * The point of this activity is to make sure that SPARTA is detecting the correct flows.
 */
public class WrapCallLocationServiceActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient googleApiClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Setup main view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_direct);

        // Build the Google API client
        buildGoogleApiClient(this);

        // Create button listener
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    googleApiClient.connect();
                } else {
                    googleApiClient.disconnect();
                }
            }
        };
        ToggleButton locationNowButton = (ToggleButton) findViewById(R.id.location_direct_button_id);
        locationNowButton.setOnCheckedChangeListener(listener);

        // Set the location later button
        createLocationLaterButton();
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

    /**
     *  Set up the button to display the location later
     */
    protected void createLocationLaterButton() {
        final Button locationLaterButton = (Button) findViewById(R.id.location_later_button_id);
        locationLaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outputLocation();
            }
        });
    }

    protected void outputLocation()  {
        TextView textView = (TextView) findViewById(R.id.text_view_id);

        if (!googleApiClient.isConnected()) {
            throw new RuntimeException();
        }

        android.location.Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (location == null) {
            throw new RuntimeException();
        }

        Location wrappedLocation = new Location(location);
        double latitude = wrappedLocation.getLatitude();
        double longitude = wrappedLocation.getLongitude();

        textView.setText("Last location - Latitude: " + latitude + ", Longitude: " + longitude);
    }

    protected int contentView() {
        return R.layout.activity_location;
    }

    @Override
    public void onConnected(Bundle bundle) {
        outputLocation();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        TextView textView = (TextView) findViewById(R.id.text_view_id);
        String locationOffString = "Location is Off";
        textView.setText(locationOffString);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        throw new RuntimeException("Connection failed");
    }
}
