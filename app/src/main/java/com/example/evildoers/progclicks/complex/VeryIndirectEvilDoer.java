package com.example.evildoers.progclicks.complex;

import android.app.Activity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.IndirectClickEvilDoer;

import java.util.ArrayList;
import java.util.List;

/**
 * Programmatically click a button indirectly, through a listener to another button, which listens to another button, and so on
 */
public class VeryIndirectEvilDoer implements EvilDoer {

    protected List<EvilDoer> evilDoers;

    public VeryIndirectEvilDoer(int ... buttonIdPath) {
        this.evilDoers = new ArrayList<>();

        // Build an indirect evil doer for each step along the path
        for (int i = 1; i < buttonIdPath.length; i++) {
            evilDoers.add(new IndirectClickEvilDoer(buttonIdPath[i], buttonIdPath[i - 1]));
        }
    }

    @Override
    public void doEvil(Activity activity) {
        for (EvilDoer evilDoer : evilDoers) {
            evilDoer.doEvil(activity);
        }
    }
}
