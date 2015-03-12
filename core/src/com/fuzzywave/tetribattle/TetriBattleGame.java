package com.fuzzywave.tetribattle;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.fuzzywave.tetribattle.assets.Assets;
import com.fuzzywave.tetribattle.screen.SplashScreen;

public class TetriBattleGame extends Game {

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        SplashScreen splashScreen = new SplashScreen(this);
        this.setScreen(splashScreen);
    }

    @Override
    public void render() {

        //Gdx.gl.glClearColor(1, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.graphics.getGL20().glClearColor(1, 0, 0, 1);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.getInstance().unloadAssets();
        Assets.dispose();
    }
}
