package com.example.activities.progclicks.simple.events;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.clickjacking.timing.TimingButtonLocationActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.event.CopyEventEvilDoer;

import java.util.Collection;
import java.util.List;

/**
 * Activity which copies an event into the location of the Location ACG and plays it back later
 */
public class CopyEventLocationActivity extends TimingButtonLocationActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = (List<EvilDoer>) super.evilDoers();
        EvilDoer evilDoer = new CopyEventEvilDoer(R.id.location_acg_button_id, R.id.malicious_disguised_button_id, 5000);
        evilDoers.add(evilDoer);
        return evilDoers;
    }
}
