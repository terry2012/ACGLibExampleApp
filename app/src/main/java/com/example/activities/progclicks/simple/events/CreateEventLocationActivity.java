package com.example.activities.progclicks.simple.events;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.progclicks.simple.ProgClickLocationActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.event.CreateEventEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Activity which creates an event to pass to the location ACG
 */
public class CreateEventLocationActivity extends ProgClickLocationActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        EvilDoer evilDoer = new CreateEventEvilDoer(R.id.location_acg_button_id);
        evilDoers.add(evilDoer);
        return evilDoers;
    }
}
