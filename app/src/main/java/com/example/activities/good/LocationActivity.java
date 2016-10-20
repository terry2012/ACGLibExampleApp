package com.example.activities.good;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.acg.EvilApp.R;
import com.acg.lib.ACGResourceAccessException;
import com.acg.lib.impl.LocationACG;
import com.acg.lib.listeners.ACGActivity;
import com.acg.lib.listeners.ACGListeners;
import com.acg.lib.listeners.ResourceAvailabilityListener;
import com.acg.lib.model.Location;

/**
 * Good location activity, for use as a reference
 */
public class LocationActivity extends Activity implements ACGActivity {
    public LocationACG locationACG; // Public to make malicious activities easier to implement

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Setup main view
        super.onCreate(savedInstanceState);
        setContentView(contentView());

        // Get the location ACG
        locationACG = (LocationACG) getFragmentManager().findFragmentById(R.id.location_acg_fragment_id);

        // Set the location later button
        createLocationLaterButton();
    }

    /**
     *  Set up the button to display the location later
     */
    protected void createLocationLaterButton() {
        final Button locationLaterButton = (Button) findViewById(R.id.location_later_button_id);
        locationLaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    outputLocation();
                } catch (ACGResourceAccessException e) {
                    TextView textView = (TextView) findViewById(R.id.text_view_id);
                    textView.setText("Error: Please turn location on");
                }
            }
        });
    }

    protected void outputLocation() throws ACGResourceAccessException {
        TextView textView = (TextView) findViewById(R.id.text_view_id);

        Location location = locationACG.getResource();

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        String locationString = "Last location - Latitude: " + latitude + ", Longitude: " + longitude;

        textView.setText(locationString);
    }

    protected int contentView() {
        return R.layout.activity_location;
    }

    @Override
    public ACGListeners buildACGListeners() {
        return new ACGListeners.Builder().withResourceReadyListener(locationACG, new ResourceAvailabilityListener() {
            @Override
            public void onResourceUnavailable() {
                TextView textView = (TextView) findViewById(R.id.text_view_id);
                String locationOffString = "Location is Off";
                textView.setText(locationOffString);
            }

            @Override
            public void onResourceReady() {
                try {
                    outputLocation();
                } catch (ACGResourceAccessException e) {
                    //catastrophic failure that should never happen
                    throw new RuntimeException(e);
                }
            }
        }).build();
    }
}
