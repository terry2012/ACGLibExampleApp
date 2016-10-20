package com.example.activities.progclicks.complex;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.EvilLocationActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.complex.CleverClickEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Lots of choices for programmatic clicks
 */
public class CleverClickLocationActivity extends EvilLocationActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        evilDoers.add(new CleverClickEvilDoer(R.id.location_acg_button_id, R.id.malicious_disguised_button_id, R.id.spinner_id));
        return evilDoers;
    }

    @Override
    public int contentView() {
        return R.layout.activity_location_clever;
    }
}
