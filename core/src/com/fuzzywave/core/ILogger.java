package com.fuzzywave.core;

public interface ILogger {

    public void logInfo(String tag, String msg);

    public void logError(String tag, String msg);

    public void logDebug(String tag, String msg);

    public void logException(String tag, Exception e);
}
