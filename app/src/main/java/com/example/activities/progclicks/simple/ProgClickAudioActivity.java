package com.example.activities.progclicks.simple;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.EvilAudioActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.ProgClickEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Simple activity that programmatically clicks the audio ACG button instead of waiting for user input
 */
public class ProgClickAudioActivity extends EvilAudioActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        EvilDoer evilDoer = new ProgClickEvilDoer(R.id.audio_acg_button_id);
        evilDoers.add(evilDoer);
        return evilDoers;
    }

    @Override
    public int contentView() {
        return R.layout.activity_audio;
    }
}
