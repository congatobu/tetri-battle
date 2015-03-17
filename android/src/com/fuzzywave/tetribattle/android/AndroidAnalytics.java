package com.fuzzywave.tetribattle.android;

import com.flurry.android.FlurryAgent;
import com.fuzzywave.core.IAnalytics;

import java.util.Map;


public class AndroidAnalytics implements IAnalytics {

    private final android.content.Context context;

    public AndroidAnalytics(android.content.Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        // configure Flurry
        FlurryAgent.setLogEnabled(true);
        FlurryAgent.setCaptureUncaughtExceptions(true);
        FlurryAgent.setVersionName(BuildConfig.VERSION_NAME);

        // init Flurry
        FlurryAgent.init(this.context, BuildConfig.FLURRY_API_KEY);
    }

    @Override
    public void logEvent(String eventId) {
        FlurryAgent.logEvent(eventId);
    }

    @Override
    public void logEvent(String eventId, Map<String, String> parameters) {
        FlurryAgent.logEvent(eventId, parameters);
    }

    @Override
    public void logEvent(String eventId, Map<String, String> parameters, boolean timed) {
        FlurryAgent.logEvent(eventId, parameters, timed);
    }

    @Override
    public void logEvent(String eventId, boolean timed) {
        FlurryAgent.logEvent(eventId, timed);
    }

    @Override
    public void endTimedEvent(String eventId) {
        FlurryAgent.endTimedEvent(eventId);
    }

    @Override
    public void endTimedEvent(String eventId, Map<String, String> parameters) {
        FlurryAgent.endTimedEvent(eventId, parameters);
    }

    @Override
    public void onError(String errorId, String message, Throwable exception) {
        FlurryAgent.onError(errorId, message, exception);
    }
}
