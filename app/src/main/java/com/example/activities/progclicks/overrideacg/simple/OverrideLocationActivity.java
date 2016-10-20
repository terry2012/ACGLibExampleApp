package com.example.activities.progclicks.overrideacg.simple;

import android.location.Location;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.acg.EvilApp.R;
import com.acg.lib.ACGResourceAccessException;
import com.example.activities.progclicks.simple.ProgClickLocationActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.OverrideACGEvilDoer;
import com.example.listeners.LocationACGOutsideListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Location activity that overrides the ACG listener to disguise the flow and programmatically clicks it
 */
public class OverrideLocationActivity extends ProgClickLocationActivity {

    protected LocationACGOutsideListener acgListener;

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        evilDoers.add(buildOverrideACGEvilDoer());
        evilDoers.addAll(super.evilDoers());
        return evilDoers;
    }

    protected EvilDoer buildOverrideACGEvilDoer() {
        acgListener = new LocationACGOutsideListener(this);
        return new OverrideACGEvilDoer(R.id.location_acg_button_id, acgListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(acgListener != null) {
            acgListener.cleanUp();
        }
    }

    @Override
    protected void outputLocation() throws ACGResourceAccessException {
        TextView textView = (TextView) findViewById(R.id.text_view_id);

        // We cheat and get this from our own desired location, SPARTA should hopefully catch this
        Location location = acgListener.getResource();

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        textView.setText("Last location - Latitude: " + latitude + ", Longitude: " + longitude);
    }
}
