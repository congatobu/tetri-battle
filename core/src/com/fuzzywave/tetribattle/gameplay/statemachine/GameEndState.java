package com.fuzzywave.tetribattle.gameplay.statemachine;


import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.gameplay.GameInstance;
import com.fuzzywave.tetribattle.screen.MenuScreen;

public class GameEndState implements State {
    @Override
    public void enter(GameInstance gameInstance) {
    }

    @Override
    public void update(GameInstance gameInstance, float delta) {
        TetriBattle.game.setScreen(new MenuScreen());
    }

    @Override
    public void exit(GameInstance gameInstance) {
    }
}
