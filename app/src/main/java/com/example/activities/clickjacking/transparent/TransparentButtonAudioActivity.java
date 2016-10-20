package com.example.activities.clickjacking.transparent;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.example.activities.EvilAudioActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.clickjacking.TransparentViewEvilDoer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Clickjacking activity which sets the audio ACG button to invisible and instead shows a harmless-looking button
 */
public class TransparentButtonAudioActivity extends EvilAudioActivity {

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        EvilDoer evilDoer = new TransparentViewEvilDoer(R.id.audio_acg_button_id);
        evilDoers.add(evilDoer);
        return evilDoers;
    }

    protected int contentView() {
        return R.layout.activity_audio_hidden;
    }
}
