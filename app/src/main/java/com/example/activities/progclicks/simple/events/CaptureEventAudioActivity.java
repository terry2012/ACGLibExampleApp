package com.example.activities.progclicks.simple.events;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.clickjacking.timing.TimingButtonAudioActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.event.CaptureEventEvilDoer;

import java.util.Collection;
import java.util.List;

/**
 * Activity which captrures an event into the the location of the Audio ACG and plays it back later
 * This should pass static validation and be caught at runtime
 */
public class CaptureEventAudioActivity extends TimingButtonAudioActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = (List<EvilDoer>) super.evilDoers();
        EvilDoer evilDoer = new CaptureEventEvilDoer(R.id.audio_acg_button_id, R.id.malicious_disguised_button_id, 5000);
        evilDoers.add(evilDoer);
        return evilDoers;
    }
}
