package com.fuzzywave.tetribattle.screen;


import com.fuzzywave.tetribattle.gameplay.GameInstance;

public class GameScreen extends AbstractScreen {



    private GameInstance playerGameInstance;

    public GameScreen() {
        playerGameInstance = new GameInstance();
    }

    @Override
    public void render(float delta) {

        playerGameInstance.update(delta);
    }

}
