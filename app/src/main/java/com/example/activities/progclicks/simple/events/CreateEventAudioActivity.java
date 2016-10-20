package com.example.activities.progclicks.simple.events;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.progclicks.simple.ProgClickAudioActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.event.CreateEventEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Activity which creates an event to pass to the audio ACG
 */
public class CreateEventAudioActivity extends ProgClickAudioActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        EvilDoer evilDoer = new CreateEventEvilDoer(R.id.audio_acg_button_id);
        evilDoers.add(evilDoer);
        return evilDoers;
    }
}
