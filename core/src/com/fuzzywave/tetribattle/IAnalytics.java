package com.fuzzywave.tetribattle;

import java.util.Map;

public interface IAnalytics {

    void init();

    void logEvent(String eventId);

    void logEvent(String eventId, Map<String, String> parameters);

    void logEvent(String eventId, Map<String, String> parameters, boolean timed);

    void logEvent(String eventId, boolean timed);

    void endTimedEvent(String eventId);

    void endTimedEvent(String eventId, Map<String, String> parameters);

    void onError(String errorId, String message, Throwable exception);

}
