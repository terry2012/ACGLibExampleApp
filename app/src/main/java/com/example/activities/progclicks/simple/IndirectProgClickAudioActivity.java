package com.example.activities.progclicks.simple;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.EvilAudioActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.IndirectClickEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Click a button whose listener clicks the audio ACG button instead of waiting for user input
 */
public class IndirectProgClickAudioActivity extends EvilAudioActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        EvilDoer evilDoer = new IndirectClickEvilDoer(R.id.audio_acg_button_id, R.id.malicious_disguised_button_id);
        evilDoers.add(evilDoer);
        return evilDoers;
    }

    @Override
    public int contentView() {
        return R.layout.activity_audio_indirect;
    }
}
