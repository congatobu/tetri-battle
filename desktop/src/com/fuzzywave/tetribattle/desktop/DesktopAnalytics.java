package com.fuzzywave.tetribattle.desktop;


import com.badlogic.gdx.utils.Logger;
import com.fuzzywave.tetribattle.IAnalytics;

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
    public void logEvent(String category, String action) {
        logger.debug("DesktopAnalytics.logEvent( " + category + " - " + action + " )");
    }

    @Override
    public void logEvent(String category, String action, String label) {
        logger.debug(
                "DesktopAnalytics.logEvent( " + category + " - " + action + " - " + label + " )");
    }

    @Override
    public void logEvent(String category, String action, String label, long value) {
        logger.debug(
                "DesktopAnalytics.logEvent( " + category + " - " + action + " - " + label + " - " + value + " )");
    }


    @Override
    public void onError(Throwable exception) {
        logger.debug("DesktopAnalytics.onError( " + exception.getMessage() + " )");
    }
}
