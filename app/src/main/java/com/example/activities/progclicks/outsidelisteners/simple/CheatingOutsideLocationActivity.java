package com.example.activities.progclicks.outsidelisteners.simple;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.ProgClickEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Programmatically click a button whose listener invokes an ACG
 * The listener is in an external class
 */
public class CheatingOutsideLocationActivity extends IndirectOutsideLocationActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>(super.evilDoers());
        evilDoers.add(new ProgClickEvilDoer(R.id.malicious_disguised_button_id));
        return evilDoers;
    }
}
