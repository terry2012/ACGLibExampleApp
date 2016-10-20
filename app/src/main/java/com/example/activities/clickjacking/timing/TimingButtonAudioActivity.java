package com.example.activities.clickjacking.timing;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.EvilAudioActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.clickjacking.BaitAndSwitchEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Activity that puts the location on the bottom , but then switches it on top just in time for a user click
 * AKA a bait-and-switch attack using a button
 * (Bait-and-switch using toasts need to be handled separately)
 */
public class TimingButtonAudioActivity extends EvilAudioActivity {

    @Override
    public @NonNull
    Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        EvilDoer evilDoer = new BaitAndSwitchEvilDoer(R.id.malicious_fragment_id, R.id.audio_acg_fragment_id, 2000, 1000);
        evilDoers.add(evilDoer);
        return evilDoers;
    }

    protected int contentView() {
        return R.layout.activity_audio_bottom;
    }
}
