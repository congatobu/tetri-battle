package com.fuzzywave.tetribattle;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.fuzzywave.core.IAnalytics;
import com.fuzzywave.core.ILogger;
import com.fuzzywave.tetribattle.assets.Assets;
import com.fuzzywave.tetribattle.screen.SplashScreen;

public class TetriBattle implements ApplicationListener {

    // create a new game and set the initial screen
    public static Game game = new Game() {
        @Override
        public void create() {
            setScreen(new SplashScreen());
        }
    };

    public static SpriteBatch spriteBatch;

    public static IAnalytics analytics;

    public static ILogger logger;

    public static Assets assets;

    public TetriBattle(ILogger logger, IAnalytics analytics) {
        TetriBattle.logger = logger;
        TetriBattle.analytics = analytics;
    }

    @Override
    public void create() {
        try {
            logger.setLevel(Logger.DEBUG);

            analytics.init();

            spriteBatch = new SpriteBatch();

            assets = new Assets();

            game.create();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
        try {
            game.resize(width, height);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            Gdx.app.exit();
        }
    }

    @Override
    public void render() {
        try {
            game.render();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            Gdx.app.exit();
        }
    }

    @Override
    public void pause() {
        try {
            game.pause();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            Gdx.app.exit();
        }
    }

    @Override
    public void resume() {
        try {
            game.resume();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            Gdx.app.exit();
        }
    }

    @Override
    public void dispose() {
        game.dispose();
        assets.dispose();

        if (spriteBatch != null) {
            spriteBatch.dispose();
        }
    }
}
