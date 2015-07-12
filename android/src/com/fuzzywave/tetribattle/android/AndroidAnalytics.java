package com.fuzzywave.tetribattle.android;

import com.fuzzywave.tetribattle.IAnalytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;


public class AndroidAnalytics implements IAnalytics {

    private final android.content.Context context;

    private Tracker appTracker;

    public AndroidAnalytics(android.content.Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
        appTracker = analytics.newTracker(R.xml.app_tracker);
        // Enable Advertising Features.
        appTracker.enableAdvertisingIdCollection(true);
        // set default screen name.
        appTracker.setScreenName("Tetri Battle Android");

        appTracker.send(new HitBuilders.ScreenViewBuilder().setNewSession().build());
    }

    @Override
    public void logEvent(String category, String action) {
        appTracker.send(
                new HitBuilders.EventBuilder().setCategory(category).setAction(action).build());
    }

    @Override
    public void logEvent(String category, String action, String label) {
        appTracker.send(
                new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(
                        label).build());
    }

    @Override
    public void logEvent(String category, String action, String label, long value) {
        appTracker.send(
                new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(
                        label).setValue(value).build());
    }

    @Override
    public void onError(Throwable exception) {

        appTracker.send(new HitBuilders.ExceptionBuilder().setDescription(
                new StandardExceptionParser(context, null).getDescription(
                        Thread.currentThread().getName(), exception)).setFatal(false).build());
    }
}
