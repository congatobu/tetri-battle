package com.fuzzywave.core;

import java.util.Map;

public interface IAnalytics {

    public void init();

    public void logEvent(String eventId);

    public void logEvent(String eventId, Map<String, String> parameters);

    public void logEvent(String eventId, Map<String, String> parameters, boolean timed);

    public void logEvent(String eventId, boolean timed);

    public void endTimedEvent(String eventId);

    public void endTimedEvent(String eventId, Map<String, String> parameters);

    public void onError(String errorId, String message, Throwable exception);

}
