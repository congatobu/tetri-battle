package com.fuzzywave.core;

import com.badlogic.gdx.Gdx;

public class CoreLogger {
    public static String tag = "fuzzywave";
    public static ILogger logger;

    public static void logInfo(String str) {
        try {
            if (CoreLogger.logger == null) {
                Gdx.app.log(tag, str);
            } else {
                logger.logInfo(tag, str);
            }
        } catch (Exception ex) {

        }
    }

    public static void logError(String str) {
        try {
            if (CoreLogger.logger == null) {
                Gdx.app.error(tag, str);
            } else {
                logger.logError(tag, str);
            }
        } catch (Exception ex) {

        }
    }

    public static void logDebug(String str) {
        try {
            if (CoreLogger.logger == null) {
                Gdx.app.debug(tag, str);
            } else {
                logger.logDebug(tag, str);
            }
        } catch (Exception ex) {

        }
    }

    public static void logException(Exception e) {
        try {
            if (CoreLogger.logger == null) {
                Gdx.app.log(tag, "Caught Exception: ", e);
            } else {
                logger.logException(tag, e);
            }
        } catch (Exception ex) {

        }
    }

    public static void logException(Throwable t) {
        Exception e = new Exception(t);
        CoreLogger.logException(e);
    }
}