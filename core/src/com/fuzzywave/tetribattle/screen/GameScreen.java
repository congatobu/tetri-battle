package com.fuzzywave.tetribattle.screen;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.gameplay.GameInstance;

public class GameScreen extends AbstractScreen {

    private Viewport viewport;
    private Camera camera;

    private GameInstance playerGameInstance;

    public GameScreen() {

        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(TetriBattle.WORLD_WIDTH_PIXEL,
                TetriBattle.WORLD_HEIGHT_PIXEL,
                this.camera);
        TetriBattle.spriteBatch.setProjectionMatrix(camera.combined);

        playerGameInstance = new GameInstance(new Rectangle(-240, -520, 480, 1040));
    }

    @Override
    public void render(float delta) {

        TetriBattle.spriteBatch.setProjectionMatrix(camera.combined);

        // TODO implement fixed time step for game loop.

        playerGameInstance.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.viewport.update(width, height);
    }

}
