package com.example.activities.progclicks.complex.events;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.EvilAudioActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.complex.event.CleverEventEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Lots of choices for programmatic events
 */
public class CleverEventAudioActivity extends EvilAudioActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        evilDoers.add(new CleverEventEvilDoer(R.id.audio_acg_button_id, R.id.malicious_disguised_button_id, R.id.spinner_id));
        return evilDoers;
    }

    @Override
    public int contentView() {
        return R.layout.activity_audio_clever;
    }
}
