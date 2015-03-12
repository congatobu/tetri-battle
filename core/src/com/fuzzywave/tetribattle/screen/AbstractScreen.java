package com.fuzzywave.tetribattle.screen;

import com.badlogic.gdx.Screen;
import com.fuzzywave.core.CoreLogger;
import com.fuzzywave.tetribattle.TetriBattleGame;


public class AbstractScreen implements Screen {

    protected TetriBattleGame game;

    public AbstractScreen(TetriBattleGame game) {

        this.game = game;
    }

    @Override
    public void show() {
        CoreLogger.logDebug(getName() + " show()");
    }

    @Override
    public void render(float delta) {
        CoreLogger.logDebug(getName() + " render(" + delta + ")");
    }

    @Override
    public void resize(int width, int height) {
        CoreLogger.logDebug(getName() + " resize(" + width + ", " + height + ")");
    }

    @Override
    public void pause() {
        CoreLogger.logDebug(getName() + " pause()");
    }

    @Override
    public void resume() {
        CoreLogger.logDebug(getName() + " resume()");
    }

    @Override
    public void hide() {
        CoreLogger.logDebug(getName() + " hide()");
    }

    @Override
    public void dispose() {
        CoreLogger.logDebug(getName() + " dispose()");
    }

    public String getName() {
        return ((Object) this).getClass().getSimpleName();
    }
}
