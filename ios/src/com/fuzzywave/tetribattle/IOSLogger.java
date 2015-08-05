package com.fuzzywave.tetribattle;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

public class IOSLogger implements ILogger {

    private Logger logger;

    public IOSLogger(String tag) {
        logger = new Logger(tag);
    }

    @Override
    public void setLevel(int level) {
        logger.setLevel(level);
        Gdx.app.setLogLevel(level);
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    @Override
    public void error(String msg, Exception e) {
        logger.error(msg, e);
    }

    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }
}
