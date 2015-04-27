package com.fuzzywave.tetribattle.screen;

import com.badlogic.gdx.Screen;
import com.fuzzywave.tetribattle.TetriBattle;


public class AbstractScreen implements Screen {

    @Override
    public void show() {
        TetriBattle.logger.debug(getName() + " show()");
    }

    @Override
    public void render(float delta) {
        TetriBattle.logger.debug(getName() + " render(" + delta + ")");
    }

    @Override
    public void resize(int width, int height) {
        TetriBattle.logger.debug(getName() + " resize(" + width + ", " + height + ")");
    }

    @Override
    public void pause() {
        TetriBattle.logger.debug(getName() + " pause()");
    }

    @Override
    public void resume() {
        TetriBattle.logger.debug(getName() + " resume()");
    }

    @Override
    public void hide() {
        TetriBattle.logger.debug(getName() + " hide()");
    }

    @Override
    public void dispose() {
        TetriBattle.logger.debug(getName() + " dispose()");
    }

    public String getName() {
        return ((Object) this).getClass().getSimpleName();
    }
}
