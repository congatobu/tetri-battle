package com.fuzzywave.tetribattle;


import com.badlogic.gdx.input.GestureDetector;
import com.fuzzywave.tetribattle.screen.GameScreen;

public class GameGestureAdapter extends GestureDetector.GestureAdapter {

    private GameScreen gameScreen;

    public GameGestureAdapter(GameScreen gameScreen) {

        this.gameScreen = gameScreen;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        TetriBattle.logger.debug("GameGestureAdapter tap()");
        gameScreen.tap(x, y);
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        TetriBattle.logger.debug("GameGestureAdapter fling:" + velocityX + ", " + velocityY);

        // TODO Set the MaxFlingDelay
        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
                this.gameScreen.flingRight();

            } else if (velocityX < 0) {
                this.gameScreen.flingLeft();

            }
        } else {
            if (velocityY > 0) {
                this.gameScreen.flingDown();
            }

        }

        return true;
    }
}
