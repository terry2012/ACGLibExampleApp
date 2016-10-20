package com.example.activities.progclicks.overrideacg.complex;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.progclicks.overrideacg.simple.OverrideAudioActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.complex.VeryIndirectOutsideEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Activity that responds to user input by programmatically clicking a button whose listener clicks several buttons,
 * eventually triggering the overridden audio ACG button
 */
public class VeryIndirectOverrideAudioActivity extends OverrideAudioActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        evilDoers.add(buildOverrideACGEvilDoer());
        evilDoers.add(new VeryIndirectOutsideEvilDoer(
                R.id.malicious_disguised_button_id_5,
                R.id.malicious_disguised_button_id_4,
                R.id.malicious_disguised_button_id_3,
                R.id.malicious_disguised_button_id_2,
                R.id.malicious_disguised_button_id,
                R.id.audio_acg_button_id
        ));
        return evilDoers;
    }

    @Override
    public int contentView() {
        return R.layout.activity_audio_very_indirect;
    }
}
