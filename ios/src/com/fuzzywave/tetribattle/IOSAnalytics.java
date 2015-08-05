package com.fuzzywave.tetribattle;

import com.badlogic.gdx.utils.Logger;


public class IOSAnalytics implements IAnalytics{

    private Logger logger;

    public IOSAnalytics() {
        logger = new Logger("TetriBattleIOSAnalytics", Logger.DEBUG);
    }

    @Override
    public void init() {
        logger.debug("IOSAnalytics.init()");
    }

    @Override
    public void logEvent(String category, String action) {
        logger.debug("IOSAnalytics.logEvent( " + category + " - " + action + " )");
    }

    @Override
    public void logEvent(String category, String action, String label) {
        logger.debug(
                "IOSAnalytics.logEvent( " + category + " - " + action + " - " + label + " )");
    }

    @Override
    public void logEvent(String category, String action, String label, long value) {
        logger.debug(
                "IOSAnalytics.logEvent( " + category + " - " + action + " - " + label + " - " + value + " )");
    }


    @Override
    public void onError(Throwable exception) {
        logger.debug("IOSAnalytics.onError( " + exception.getMessage() + " )");
    }
}
