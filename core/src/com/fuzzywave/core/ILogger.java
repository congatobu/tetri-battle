package com.fuzzywave.core;

public interface ILogger {

    void setLevel(int level);

    void info(String msg);

    void error(String msg);

    void error(String msg, Exception e);

    void debug(String msg);
}
