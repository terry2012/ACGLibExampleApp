package com.example.activities.progclicks.simple.events;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.progclicks.simple.IndirectProgClickLocationActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.event.ModifyEventEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Activity which modifies an event that goes into a different button and passes it instead into the ACG
 */
public class ModifyEventLocationActivity extends IndirectProgClickLocationActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        EvilDoer evilDoer = new ModifyEventEvilDoer(R.id.location_acg_button_id, R.id.malicious_disguised_button_id);
        evilDoers.add(evilDoer);
        return evilDoers;
    }
}
