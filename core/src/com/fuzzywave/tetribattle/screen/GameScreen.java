package com.fuzzywave.tetribattle.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fuzzywave.tetribattle.GameGestureAdapter;
import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.gameplay.GameInstance;

public class GameScreen extends AbstractScreen {

    private Viewport viewport;
    private Camera camera;
    private Vector3 screenCoords;

    private GameInstance playerGameInstance;
    private Rectangle playerGameBounds;

    public GameScreen() {

        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(TetriBattle.WORLD_WIDTH_PIXEL,
                TetriBattle.WORLD_HEIGHT_PIXEL,
                this.camera);
        TetriBattle.spriteBatch.setProjectionMatrix(camera.combined);

        this.playerGameBounds = new Rectangle(-240, -520, 480, 1040);
        this.playerGameInstance = new GameInstance(playerGameBounds);
        this.screenCoords = new Vector3(0, 0, 0);
    }

    @Override
    public void show() {
        super.show();

        TetriBattle.logger.debug("Setting Input Processor...");
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new GestureDetector(new GameGestureAdapter(this)));
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void hide() {

        super.hide();
        TetriBattle.logger.debug("Setting Input Processor to NULL");
        Gdx.input.setInputProcessor(null);
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

    public void tap(float x, float y) {
        camera.unproject(screenCoords.set(x, y, 0));
        if (playerGameBounds.contains(screenCoords.x, screenCoords.y)) {
            playerGameInstance.setRotate(true);
        }
    }

    public void flingRight() {
        playerGameInstance.setMoveRight(true);
    }

    public void flingLeft() {
        playerGameInstance.setMoveLeft(true);
    }

    public void flingDown() {
        playerGameInstance.setFastDrop(true);
    }
}
