package com.example.activities.progclicks.outsidelisteners.complex;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.progclicks.complex.VeryIndirectLocationActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.complex.VeryIndirectOutsideEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Activity that responds to user input by programmatically clicking a button whose listener clicks several buttons,
 * eventually triggering the location ACG button
 * The listeners are in an external class
 */
public class VeryIndirectOutsideLocationActivity extends VeryIndirectLocationActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();

        EvilDoer evilDoer = new VeryIndirectOutsideEvilDoer(
                R.id.malicious_disguised_button_id_5,
                R.id.malicious_disguised_button_id_4,
                R.id.malicious_disguised_button_id_3,
                R.id.malicious_disguised_button_id_2,
                R.id.malicious_disguised_button_id,
                R.id.location_acg_button_id
        );

        evilDoers.add(evilDoer);
        return evilDoers;
    }
}
