package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import com.acg.EvilApp.R;
import com.example.activities.clickjacking.cover.CoverButtonAudioActivity;
import com.example.activities.clickjacking.cover.CoverButtonLocationActivity;
import com.example.activities.clickjacking.disguise.*;
import com.example.activities.clickjacking.timing.TimingButtonAudioActivity;
import com.example.activities.clickjacking.timing.TimingButtonLocationActivity;
import com.example.activities.clickjacking.transparent.TransparentButtonAudioActivity;
import com.example.activities.clickjacking.transparent.TransparentButtonLocationActivity;
import com.example.activities.composition.ComposeAudioActivity;
import com.example.activities.composition.ComposePlayAudioActivity;
import com.example.activities.good.AudioActivity;
import com.example.activities.good.LocationActivity;
import com.example.activities.progclicks.complex.*;
import com.example.activities.progclicks.complex.events.CleverEventAudioActivity;
import com.example.activities.progclicks.complex.events.CleverEventLocationActivity;
import com.example.activities.progclicks.complex.events.IndirectCaptureEventAudioActivity;
import com.example.activities.progclicks.complex.events.IndirectCaptureEventLocationActivity;
import com.example.activities.progclicks.outsidelisteners.complex.SuperCheatingOutsideAudioActivity;
import com.example.activities.progclicks.outsidelisteners.complex.SuperCheatingOutsideLocationActivity;
import com.example.activities.progclicks.outsidelisteners.complex.VeryIndirectOutsideAudioActivity;
import com.example.activities.progclicks.outsidelisteners.complex.VeryIndirectOutsideLocationActivity;
import com.example.activities.progclicks.outsidelisteners.simple.CheatingOutsideAudioActivity;
import com.example.activities.progclicks.outsidelisteners.simple.CheatingOutsideLocationActivity;
import com.example.activities.progclicks.outsidelisteners.simple.IndirectOutsideAudioActivity;
import com.example.activities.progclicks.outsidelisteners.simple.IndirectOutsideLocationActivity;
import com.example.activities.progclicks.overrideacg.complex.SuperCheatingOverrideAudioActivity;
import com.example.activities.progclicks.overrideacg.complex.SuperCheatingOverrideLocationActivity;
import com.example.activities.progclicks.overrideacg.complex.VeryIndirectOverrideAudioActivity;
import com.example.activities.progclicks.overrideacg.complex.VeryIndirectOverrideLocationActivity;
import com.example.activities.progclicks.overrideacg.simple.*;
import com.example.activities.progclicks.simple.*;
import com.example.activities.progclicks.simple.events.*;
import com.example.activities.sparta.CallLocationServiceActivity;
import com.example.activities.sparta.CallMediaRecorderActivity;
import com.example.activities.sparta.WrapCallLocationServiceActivity;

import static com.acg.lib.validation.ValidationParameters.RANDOM_CHECK_INTERVAL_PARAMETER;
import static com.acg.lib.validation.ValidationParameters.RANDOM_CHECK_INVALIDATION_PARAMETER;

/**
 * Main activity to switch between different evil activities
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Setup main view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup range for random validation interval picker
        NumberPicker validationIntervalPicker = (NumberPicker) findViewById(R.id.validation_interval_picker_id);
        validationIntervalPicker.setMinValue(1000);
        validationIntervalPicker.setMaxValue(5000);

        // Setup range for invalidation length picker
        NumberPicker invalidationLengthPicker = (NumberPicker) findViewById(R.id.invalidation_length_picker_id);
        invalidationLengthPicker.setMinValue(1000);
        invalidationLengthPicker.setMaxValue(5000);

    }

    @Override
    public void startActivity(Intent intent) {
        // Pass in validation options for easy debugging
        Bundle bundle = new Bundle();
        bundle.putInt(RANDOM_CHECK_INTERVAL_PARAMETER, ((NumberPicker) findViewById(R.id.validation_interval_picker_id)).getValue());
        bundle.putInt(RANDOM_CHECK_INVALIDATION_PARAMETER, ((NumberPicker) findViewById(R.id.invalidation_length_picker_id)).getValue());
        intent.putExtras(bundle);

        super.startActivity(intent);
    }

    /**
     * Good reference location ACG
     */
    public void location(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    /**
     * Good reference audio ACG
     */
    public void audio(View view) {
        Intent intent = new Intent(this, AudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the location ACG
     */
    public void progClickLocation(View view) {
        Intent intent = new Intent(this, ProgClickLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the audio record ACG
     */
    public void progClickAudio(View view) {
        Intent intent = new Intent(this, ProgClickAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the location ACG indirectly through another button which responds to user input
     */
    public void progClickIndirectLocation(View view) {
        Intent intent = new Intent(this, IndirectProgClickLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the audio record ACG indirectly through another button which responds to user input
     */
    public void progClickIndirectAudio(View view) {
        Intent intent = new Intent(this, IndirectProgClickAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking a button which programmatically clicks the location ACG
     */
    public void progClickCheatingLocation(View view) {
        Intent intent = new Intent(this, CheatingLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking a button which programmatically clicks the audio ACG
     */
    public void progClickCheatingAudio(View view) {
        Intent intent = new Intent(this, CheatingAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the location ACG indirectly through several buttons, the first of which responds to user input
     */
    public void veryIndirectLocation(View view) {
        Intent intent = new Intent(this, VeryIndirectLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the audio record ACG indirectly through several buttons, the first of which responds to user input
     */
    public void veryIndirectAudio(View view) {
        Intent intent = new Intent(this, VeryIndirectAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the location ACG indirectly through several buttons
     */
    public void superCheatingLocation(View view) {
        Intent intent = new Intent(this, SuperCheatingLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the audio record ACG indirectly through several buttons
     */
    public void superCheatingAudio(View view) {
        Intent intent = new Intent(this, SuperCheatingAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the location ACG indirectly through another button which responds to user input
     * with an outside listener
     */
    public void indirectOutsideLocation(View view) {
        Intent intent = new Intent(this, IndirectOutsideLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the audio ACG indirectly through another button which responds to user input
     * with an outside listener
     */
    public void indirectOutsideAudio(View view) {
        Intent intent = new Intent(this, IndirectOutsideAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the location ACG indirectly through another button with an outside listener
     */
    public void cheatingOutsideLocation(View view) {
        Intent intent = new Intent(this, CheatingOutsideLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the audio ACG indirectly through another button with an outside listener
     */
    public void cheatingOutsideAudio(View view) {
        Intent intent = new Intent(this, CheatingOutsideAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the location ACG indirectly through several buttons with outside listeners,
     * the first of which responds to user input
     */
    public void veryIndirectOutsideLocation(View view) {
        Intent intent = new Intent(this, VeryIndirectOutsideLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the audio ACG indirectly through several buttons with outside listeners,
     * the first of which responds to user input
     */
    public void veryIndirectOutsideAudio(View view) {
        Intent intent = new Intent(this, VeryIndirectOutsideAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the location ACG indirectly through several buttons with outside listeners
     */
    public void superCheatingOutsideLocation(View view) {
        Intent intent = new Intent(this, SuperCheatingOutsideLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the audio record ACG indirectly through several buttons with outside listeners
     */
    public void superCheatingOutsideAudio(View view) {
        Intent intent = new Intent(this, SuperCheatingOutsideAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking a location ACG with an overridden listener
     */
    public void overrideLocation(View view) {
        Intent intent = new Intent(this, OverrideLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking an audio ACG with an overridden listener
     */
    public void overrideAudio(View view) {
        Intent intent = new Intent(this, OverrideAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking a location ACG with an overridden listener indirectly through another button with an outside listener
     * which responds to user input
     */
    public void indirectOverrideLocation(View view) {
        Intent intent = new Intent(this, IndirectOverrideLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking an audio ACG with an overridden listener indirectly through another button with an outside listener
     * which responds to user input
     */
    public void indirectOverrideAudio(View view) {
        Intent intent = new Intent(this, IndirectOverrideAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking a location ACG with an overridden listener indirectly through another button with an outside listener
     */
    public void cheatingOverrideLocation(View view) {
        Intent intent = new Intent(this, CheatingOverrideLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking an audio ACG with an overridden listener indirectly through another button with an outside listener
     */
    public void cheatingOverrideAudio(View view) {
        Intent intent = new Intent(this, CheatingOverrideAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the location ACG with an overridden listener indirectly through several buttons with
     * outside listeners, the first of which responds to user input
     */
    public void veryIndirectOverrideLocation(View view) {
        Intent intent = new Intent(this, VeryIndirectOverrideLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the audio ACG with an overridden listener indirectly through several buttons with
     * outside listeners, the first of which responds to user input
     */
    public void veryIndirectOverrideAudio(View view) {
        Intent intent = new Intent(this, VeryIndirectOverrideAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the location ACG with an overridden listener indirectly through several buttons with
     * outside listeners
     */
    public void superCheatingOverrideLocation(View view) {
        Intent intent = new Intent(this, SuperCheatingOverrideLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Programmatically clicking the audio ACG with an overridden listener indirectly through several buttons with
     * outside listeners
     */
    public void superCheatingOverrideAudio(View view) {
        Intent intent = new Intent(this, SuperCheatingOverrideAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Clickjacking with a transparent location ACG button
     */
    public void transparentButtonLocation(View view) {
        Intent intent = new Intent(this, TransparentButtonLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Clickjacking with a transparent audio ACG button
     */
    public void transparentButtonAudio(View view) {
        Intent intent = new Intent(this, TransparentButtonAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Clickjacking with a disguised location ACG button
     */
    public void disguiseButtonLocation(View view) {
        Intent intent = new Intent(this, DisguiseButtonLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Clickjacking with a disguised audio ACG button
     */
    public void disguiseButtonAudio(View view) {
        Intent intent = new Intent(this, DisguiseButtonAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Clickjacking with a small location ACG button
     */
    public void smallButtonLocation(View view) {
        Intent intent = new Intent(this, SmallButtonLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Clickjacking with a small audio ACG button
     */
    public void smallButtonAudio(View view) {
        Intent intent = new Intent(this, SmallButtonAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Clickjacking with a location ACG button underneath a small harmless-looking button
     */
    public void bottomButtonLocation(View view) {
        Intent intent = new Intent(this, BottomButtonLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Clickjacking with an audio ACG button underneath a small harmless-looking button
     */
    public void bottomButtonAudio(View view) {
        Intent intent = new Intent(this, BottomButtonAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Tapjacking with a Toast on top of the location button
     */
    public void coverButtonLocation(View view) {
        Intent intent = new Intent(this, CoverButtonLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Tapjacking with a Toast on top of the audio button
     */
    public void coverButtonAudio(View view) {
        Intent intent = new Intent(this, CoverButtonAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Bait-and-switch with a button on top of the location button
     */
    public void timingButtonLocation(View view) {
        Intent intent = new Intent(this, TimingButtonLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Bait-and-switch with a button on top of the audio button
     */
    public void timingButtonAudio(View view) {
        Intent intent = new Intent(this, TimingButtonAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which calls the location service on its own rather than bothering with an ACG
     */
    public void locationDirect(View view) {
        Intent intent = new Intent(this, CallLocationServiceActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which calls the location service on its own rather than bothering with an ACG,
     * and tries to obfuscate the result to avoid SPARTA.
     */
    public void wrappedLocationDirect(View view) {
        Intent intent = new Intent(this, WrapCallLocationServiceActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which calls the media recorder on its own rather than bothering with an ACG
     */
    public void audioDirect(View view) {
        Intent intent = new Intent(this, CallMediaRecorderActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which composes the AudioACG with the MediaPlayer
     */
    public void audioCompose(View view) {
        Intent intent = new Intent(this, ComposeAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which composes the MediaRecorder with the PlayAudioACG
     */
    public void playAudioCompose(View view) {
        Intent intent = new Intent(this, ComposePlayAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which captures input into an AudioACG and plays it back later
     */
    public void captureEventAudio(View view) {
        Intent intent = new Intent(this, CaptureEventAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which captures input into a LocationACG and plays it back later
     */
    public void captureEventLocation(View view) {
        Intent intent = new Intent(this, CaptureEventLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which copies input into an AudioACG and plays it back later
     */
    public void copyEventAudio(View view) {
        Intent intent = new Intent(this, CopyEventAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which copies input into a LocationACG and plays it back later
     */
    public void copyEventLocation(View view) {
        Intent intent = new Intent(this, CopyEventLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which creates input into an AudioACG
     */
    public void createEventAudio(View view) {
        Intent intent = new Intent(this, CreateEventAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which creates input into an LocationACG
     */
    public void createEventLocation(View view) {
        Intent intent = new Intent(this, CreateEventLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which modifies an event into another button to play it into the AudioACG
     */
    public void modifyEventAudio(View view) {
        Intent intent = new Intent(this, ModifyEventAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which modifies an event into another button to play it into the LocationACG
     */
    public void modifyEventLocation(View view) {
        Intent intent = new Intent(this, ModifyEventLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which captures input into an AudioACG and plays it back whenever another button is pressed
     */
    public void indirectCaptureEventAudio(View view) {
        Intent intent = new Intent(this, IndirectCaptureEventAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which captures input into a LocationACG and plays it back whenever another button is pressed
     */
    public void indirectCaptureEventLocation(View view) {
        Intent intent = new Intent(this, IndirectCaptureEventLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which provides lots of clever ways to hack events into the AudioACG
     */
    public void cleverEventAudio(View view) {
        Intent intent = new Intent(this, CleverEventAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which provides lots of clever ways to hack events into the LocationACG
     */
    public void cleverEventLocation(View view) {
        Intent intent = new Intent(this, CleverEventLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which provides lots of clever ways to hack clicks into the AudioACG
     */
    public void cleverClickAudio(View view) {
        Intent intent = new Intent(this, CleverClickAudioActivity.class);
        startActivity(intent);
    }

    /**
     * Activity which provides lots of clever ways to hack clicks into the LocationACG
     */
    public void cleverClickLocation(View view) {
        Intent intent = new Intent(this, CleverClickLocationActivity.class);
        startActivity(intent);
    }
}
