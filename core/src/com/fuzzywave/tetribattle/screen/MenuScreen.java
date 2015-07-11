package com.fuzzywave.tetribattle.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fuzzywave.tetribattle.TetriBattle;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class MenuScreen extends AbstractScreen {

    private Stage stage;

    public MenuScreen() {

        VisUI.load(); // bunlari create ve dispose'a alabiliriz.

        this.stage = new Stage(new FitViewport(TetriBattle.WORLD_WIDTH_PIXEL,
                TetriBattle.WORLD_HEIGHT_PIXEL), TetriBattle.spriteBatch);

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        TetriBattle.analytics.logEvent("UX", "MAIN_MENU_SHOWED");

        createUI();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        VisUI.dispose(); // bunlari create ve dispose'a alabiliriz.
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(TetriBattle.assets.backgroundColor.r,
                TetriBattle.assets.backgroundColor.g,
                TetriBattle.assets.backgroundColor.b,
                TetriBattle.assets.backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    private void createUI() {
        // TODO create menu buttons and everything?

        VisTable root = new VisTable(true);
        root.setFillParent(true);
        stage.addActor(root);

        VisTextButton startGameButton = new VisTextButton("Start Game"); // TODO localization
        startGameButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                TetriBattle.analytics.logEvent("UX", "click", "Start Game");
                TetriBattle.game.setScreen(new GameScreen());
            }
        });

        root.add(startGameButton).expandX().fillX().padLeft(2).padRight(2).padTop(3).row();
    }

}
