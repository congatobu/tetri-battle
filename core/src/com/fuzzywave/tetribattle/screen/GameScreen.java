package com.fuzzywave.tetribattle.screen;


import com.fuzzywave.tetribattle.gameplay.GameInstance;

public class GameScreen extends AbstractScreen{

    public static final float WORLD_WIDTH = 720;
    public static final float WORLD_HEIGHT = 1280;

    private GameInstance playerGameInstance;

    public GameScreen(){
        playerGameInstance = new GameInstance();
    }

    @Override
    public void render(float delta) {

        playerGameInstance.update(delta);
    }

}
