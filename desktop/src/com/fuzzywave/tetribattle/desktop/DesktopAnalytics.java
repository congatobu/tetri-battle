package com.fuzzywave.tetribattle.desktop;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;
import com.fuzzywave.core.IAnalytics;

import java.util.Map;

public class DesktopAnalytics implements IAnalytics {

    private Logger logger;

    public DesktopAnalytics() {
        logger = new Logger("TetriBattleDesktopAnalytics", Logger.DEBUG);
    }

    @Override
    public void init() {
        logger.debug("DesktopAnalytics.init()");
    }

    @Override
    public void logEvent(String eventId) {
        logger.debug("DesktopAnalytics.logEvent( " + eventId + " )");
    }

    @Override
    public void logEvent(String eventId, Map<String, String> parameters) {
        logger.debug("DesktopAnalytics.logEvent( " + eventId + " )");
    }

    @Override
    public void logEvent(String eventId, Map<String, String> parameters, boolean timed) {
        logger.debug("DesktopAnalytics.logEvent( " + eventId + " )");
    }

    @Override
    public void logEvent(String eventId, boolean timed) {
        logger.debug("DesktopAnalytics.logEvent( " + eventId + " )");
    }

    @Override
    public void endTimedEvent(String eventId) {
        logger.debug("DesktopAnalytics.endTimedEvent( " + eventId + " )");
    }

    @Override
    public void endTimedEvent(String eventId, Map<String, String> parameters) {
        logger.debug("DesktopAnalytics.endTimedEvent( " + eventId + " )");
    }

    @Override
    public void onError(String errorId, String message, Throwable exception) {
        logger.debug(
                "DesktopAnalytics.onError( " + errorId + "," + message + "," + exception.getMessage() + " )");
    }
}
