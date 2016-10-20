package com.example.activities.progclicks.overrideacg.simple;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.IndirectOutsideEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Activity that responds to user input by programmatically clicking a button whose listener clicks the
 * overridden location ACG button
 */
public class IndirectOverrideLocationActivity extends OverrideLocationActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        evilDoers.add(buildOverrideACGEvilDoer());
        evilDoers.add(new IndirectOutsideEvilDoer(R.id.location_acg_button_id, R.id.malicious_disguised_button_id));
        return evilDoers;
    }

    @Override
    public int contentView() {
        return R.layout.activity_location_indirect;
    }
}
