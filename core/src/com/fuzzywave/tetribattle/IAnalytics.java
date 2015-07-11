package com.fuzzywave.tetribattle;

import java.util.Map;

public interface IAnalytics {

    void init();

    void logEvent(String category, String action);

    void logEvent(String category, String action, String label);

    void logEvent(String category, String action, String label, long value);

    void onError(Throwable exception);

}
