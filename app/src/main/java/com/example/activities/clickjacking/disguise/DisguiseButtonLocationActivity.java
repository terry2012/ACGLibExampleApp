package com.example.activities.clickjacking.disguise;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.EvilLocationActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.clickjacking.ModifyToggleButtonEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Clickjacking activity which modifies the text of the location ACG button to look harmless
 */
public class DisguiseButtonLocationActivity extends EvilLocationActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        EvilDoer evilDoer = new ModifyToggleButtonEvilDoer(R.id.location_acg_button_id, "Do Nothing");
        evilDoers.add(evilDoer);
        return evilDoers;
    }
}
