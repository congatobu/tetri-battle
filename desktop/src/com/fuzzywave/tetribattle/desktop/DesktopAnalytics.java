package com.fuzzywave.tetribattle.desktop;


import com.fuzzywave.core.CoreLogger;
import com.fuzzywave.core.IAnalytics;

import java.util.Map;

public class DesktopAnalytics implements IAnalytics {

    @Override
    public void init() {
        CoreLogger.logDebug("DesktopAnalytics.init()");
    }

    @Override
    public void logEvent(String eventId) {
        CoreLogger.logDebug("DesktopAnalytics.logEvent( " + eventId + " )");
    }

    @Override
    public void logEvent(String eventId, Map<String, String> parameters) {
        CoreLogger.logDebug("DesktopAnalytics.logEvent( " + eventId + " )");
    }

    @Override
    public void logEvent(String eventId, Map<String, String> parameters, boolean timed) {
        CoreLogger.logDebug("DesktopAnalytics.logEvent( " + eventId + " )");
    }

    @Override
    public void logEvent(String eventId, boolean timed) {
        CoreLogger.logDebug("DesktopAnalytics.logEvent( " + eventId + " )");
    }

    @Override
    public void endTimedEvent(String eventId) {
        CoreLogger.logDebug("DesktopAnalytics.endTimedEvent( " + eventId + " )");
    }

    @Override
    public void endTimedEvent(String eventId, Map<String, String> parameters) {
        CoreLogger.logDebug("DesktopAnalytics.endTimedEvent( " + eventId + " )");
    }

    @Override
    public void onError(String errorId, String message, Throwable exception) {
        CoreLogger.logDebug(
                "DesktopAnalytics.onError( " + errorId + "," + message + "," + exception.getMessage() + " )");
    }
}
