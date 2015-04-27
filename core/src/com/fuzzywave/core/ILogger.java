package com.fuzzywave.core;

public interface ILogger {

    public void setLevel(int level);

    public void info(String msg);

    public void error(String msg);

    public void error(String msg, Exception e);

    public void debug(String msg);
}
