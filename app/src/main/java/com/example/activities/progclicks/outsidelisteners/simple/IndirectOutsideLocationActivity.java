package com.example.activities.progclicks.outsidelisteners.simple;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.progclicks.simple.IndirectProgClickLocationActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.IndirectOutsideEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Activity that responds to user input by programmatically clicking a button whose listener clicks the location ACG button
 * The listener is in an external class
 */
public class IndirectOutsideLocationActivity extends IndirectProgClickLocationActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        EvilDoer evilDoer = new IndirectOutsideEvilDoer(R.id.location_acg_button_id, R.id.malicious_disguised_button_id);
        evilDoers.add(evilDoer);
        return evilDoers;
    }
}
