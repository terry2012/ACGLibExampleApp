package com.example.activities.progclicks.complex.events;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.clickjacking.timing.TimingButtonAudioActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.complex.event.IndirectCaptureEventEvilDoer;

import java.util.Collection;
import java.util.List;

/**
 * Activity which captrures an event into the location of the Audio ACG and plays it back whenever a different button is pressed
 * This should pass static validation and be caught at runtime
 */
public class IndirectCaptureEventAudioActivity extends TimingButtonAudioActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = (List<EvilDoer>) super.evilDoers();
        EvilDoer evilDoer = new IndirectCaptureEventEvilDoer(R.id.audio_acg_button_id, R.id.malicious_disguised_button_id, R.id.malicious_disguised_button_id_2);
        evilDoers.add(evilDoer);
        return evilDoers;
    }

    protected int contentView() {
        return R.layout.activity_audio_bottom_indirect;
    }
}
