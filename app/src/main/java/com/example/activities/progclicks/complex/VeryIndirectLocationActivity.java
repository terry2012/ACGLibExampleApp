package com.example.activities.progclicks.complex;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.EvilLocationActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.complex.VeryIndirectEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Activity that responds to user input by programmatically clicking a button whose listener clicks several buttons,
 * eventually triggering the location ACG button
 */
public class VeryIndirectLocationActivity extends EvilLocationActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();

        EvilDoer evilDoer = new VeryIndirectEvilDoer(
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

    @Override
    public int contentView() {
        return R.layout.activity_location_very_indirect;
    }
}
