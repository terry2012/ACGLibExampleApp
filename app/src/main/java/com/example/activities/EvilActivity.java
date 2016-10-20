package com.example.activities;

import android.support.annotation.NonNull;
import com.example.evildoers.EvilDoer;

import java.util.Collection;

/**
 * Evil activity
 */
public interface EvilActivity {
    @NonNull Collection<EvilDoer> evilDoers();
}
