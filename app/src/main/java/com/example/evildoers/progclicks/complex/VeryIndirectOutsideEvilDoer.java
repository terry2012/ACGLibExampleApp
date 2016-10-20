package com.example.evildoers.progclicks.complex;

import com.example.evildoers.progclicks.simple.IndirectOutsideEvilDoer;

import java.util.ArrayList;

/**
 * Programmatically click a button indirectly, through a listener to another button, which listens to another button, and so on
 * The listeners here are outside of the actual class, to make the flow slightly more complex.
 */
public class VeryIndirectOutsideEvilDoer extends VeryIndirectEvilDoer {

    public VeryIndirectOutsideEvilDoer(int ... buttonIdPath) {
        this.evilDoers = new ArrayList<>();

        // Build an indirect evil doer for each step along the path
        for (int i = 1; i < buttonIdPath.length; i++) {
            evilDoers.add(new IndirectOutsideEvilDoer(buttonIdPath[i], buttonIdPath[i - 1]));
        }
    }
}
