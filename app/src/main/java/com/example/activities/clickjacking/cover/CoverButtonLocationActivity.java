package com.example.activities.clickjacking.cover;

import android.support.annotation.NonNull;
import com.example.activities.EvilLocationActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.clickjacking.MakeToastEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Cover a location ACG with a transient view which passes events down underneath it
 */
public class CoverButtonLocationActivity extends EvilLocationActivity {

    @Override
    public @NonNull Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        EvilDoer evilDoer = new MakeToastEvilDoer("Do Nothing");
        evilDoers.add(evilDoer);
        return evilDoers;
    }
}
