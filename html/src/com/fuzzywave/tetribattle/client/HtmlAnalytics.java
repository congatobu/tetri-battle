package com.fuzzywave.tetribattle.client;


import com.badlogic.gdx.utils.Logger;
import com.fuzzywave.core.IAnalytics;

import java.util.Map;

public class HtmlAnalytics implements IAnalytics {
    private Logger logger;

    public HtmlAnalytics() {
        logger = new Logger("TetriBattleHtmlAnalytics", Logger.DEBUG);
    }

    @Override
    public void init() {
        logger.debug("HtmlAnalytics.init()");
    }

    @Override
    public void logEvent(String eventId) {
        logger.debug("HtmlAnalytics.logEvent( " + eventId + " )");
    }

    @Override
    public void logEvent(String eventId, Map<String, String> parameters) {
        logger.debug("HtmlAnalytics.logEvent( " + eventId + " )");
    }

    @Override
    public void logEvent(String eventId, Map<String, String> parameters, boolean timed) {
        logger.debug("HtmlAnalytics.logEvent( " + eventId + " )");
    }

    @Override
    public void logEvent(String eventId, boolean timed) {
        logger.debug("HtmlAnalytics.logEvent( " + eventId + " )");
    }

    @Override
    public void endTimedEvent(String eventId) {
        logger.debug("HtmlAnalytics.endTimedEvent( " + eventId + " )");
    }

    @Override
    public void endTimedEvent(String eventId, Map<String, String> parameters) {
        logger.debug("HtmlAnalytics.endTimedEvent( " + eventId + " )");
    }

    @Override
    public void onError(String errorId, String message, Throwable exception) {
        logger.debug(
                "HtmlAnalytics.onError( " + errorId + "," + message + "," + exception.getMessage() + " )");
    }
}
