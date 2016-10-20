package com.example.activities.clickjacking.disguise;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.EvilAudioActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.clickjacking.ModifyToggleButtonEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Clickjacking activity which modifies the text of the audio ACG button to look harmless
 */
public class DisguiseButtonAudioActivity extends EvilAudioActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        EvilDoer evilDoer = new ModifyToggleButtonEvilDoer(R.id.audio_acg_button_id, "Do Nothing");
        evilDoers.add(evilDoer);
        return evilDoers;
    }
}
