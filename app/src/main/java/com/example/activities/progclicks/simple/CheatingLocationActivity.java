package com.example.activities.progclicks.simple;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.ProgClickEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Programmatically click a button whose listener invokes an ACG
 */
public class CheatingLocationActivity extends IndirectProgClickLocationActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>(super.evilDoers());
        evilDoers.add(new ProgClickEvilDoer(R.id.malicious_disguised_button_id));
        return evilDoers;
    }
}
