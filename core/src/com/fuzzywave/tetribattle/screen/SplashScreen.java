package com.fuzzywave.tetribattle.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.assets.SplashScreenAssets;

public class SplashScreen extends AbstractScreen {

    private static final float SPLASH_MIN_TIME = 2.0f;
    private static final float SPLASH_MAX_TIME = 3.0f;
    private static final float LOADING_TIME = 1.0f;
    private final float timeStep = 1.0f / 60.0f;
    private final int timeStepMillis = Math.round(timeStep * 1000);
    private float initialWidth;
    private float initialHeight;
    private Viewport viewport;
    private Camera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private String splashLogoString;
    private String splashWaveString;
    private String splashWaveString2;
    private String splashLoadingString;
    private float splashTimer;
    private float loadingTimer;
    private float accumulator;

    public SplashScreen() {

        this.initialWidth = Gdx.graphics.getWidth();
        this.initialHeight = Gdx.graphics.getHeight();

        // TODO read default locale from user conf.
        SplashScreenAssets.load((int) this.initialWidth, (int) this.initialHeight, null);

        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(this.initialWidth, this.initialHeight,
                                        this.camera);

        this.batch = new SpriteBatch();
        this.batch.setProjectionMatrix(this.camera.combined);

        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setProjectionMatrix(this.camera.combined);
    }

    @Override
    public void show() {
        super.show();

        TetriBattle.assets.loadAssets();

        splashLogoString = SplashScreenAssets.splashBundle.get("SPLASH_LOGO_STRING");
        splashWaveString = SplashScreenAssets.splashBundle.get("SPLASH_WAVE_STRING");
        splashWaveString2 = SplashScreenAssets.splashBundle.get("SPLASH_WAVE_STRING_2");
        splashLoadingString = SplashScreenAssets.splashBundle.get("SPLASH_LOADING_STRING");

        this.splashTimer = .0f;
        this.loadingTimer = .0f;
        this.accumulator = .0f;

        GlyphLayout layout = new GlyphLayout();
        layout.setText(SplashScreenAssets.splashLogoFont, splashLogoString);
        logoX = -(int) (layout.width / 2);
        logoY = (int) this.initialHeight / 4;

        layout.setText(SplashScreenAssets.splashWaveFont, splashWaveString);
        waveX = -(int) (layout.width / 2);
        waveY = 0;

         waveX2 = waveX;
         waveY2 = waveY - (int) SplashScreenAssets.splashWaveFont.getLineHeight();

        layout.setText(SplashScreenAssets.splashLoadingFont, splashLoadingString);
        loadingX = -(int) (layout.width / 2);
        loadingY = -(int) this.initialHeight / 4;

        TetriBattle.analytics.logEvent("SPLASH_SCREEN_SHOWED");
    }

    private int logoX;
    private int logoY;

    private int waveX;
    private int waveY;

    private int waveX2;
    private int waveY2;

    private int loadingX;
    private int loadingY;


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.viewport.update(width, height);
        SplashScreenAssets.load(width, height, null);
    }


    @Override
    public void render(float delta) {

        if (delta > 0.25f) {
            delta = 0.25f;
        }

        accumulator += delta;

        splashTimer += delta;
        loadingTimer += delta;

        float splashTimerPercent = splashTimer / SPLASH_MIN_TIME;
        if (this.splashTimer >= SPLASH_MIN_TIME) {
            splashTimerPercent = 1.0f;
        }


        int interpolatedStringLength = (int) Interpolation.linear.apply(.0f,
                                                                        splashWaveString.length(),
                                                                        splashTimerPercent);
        String splashWaveText = splashWaveString.substring(0,
                                                           interpolatedStringLength);

        String splashWaveText2 = splashWaveString2.substring(0, interpolatedStringLength);




        float loadingTimerPercent = loadingTimer / LOADING_TIME;
        if (this.loadingTimer > LOADING_TIME) {
            loadingTimerPercent = 1.0f;
            loadingTimer = .0f;
        }

        int interpolatedLoadingStringLength = Math.round(Interpolation.linear.apply(.0f,
                                                                                    //SPLASH_LOADING_STRING.length(),
                                                                                    3f,
                                                                                    loadingTimerPercent));
        String splashLoadingText = splashLoadingString.substring(0,
                                                                 splashLoadingString.length() - 3 + interpolatedLoadingStringLength);


        // render
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(-initialWidth / 2, -initialHeight / 2,
                           initialWidth, initialHeight,
                           SplashScreenAssets.splashColor1,
                           SplashScreenAssets.splashColor2,
                           SplashScreenAssets.splashColor1,
                           SplashScreenAssets.splashColor2);

        shapeRenderer.end();


        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        SplashScreenAssets.splashLogoFont.draw(batch, splashLogoString, logoX, logoY);

        SplashScreenAssets.splashWaveFont.draw(batch, splashWaveText, waveX, waveY);
        SplashScreenAssets.splashWaveFont.draw(batch, splashWaveText2, waveX2, waveY2);

        SplashScreenAssets.splashLoadingFont.draw(batch, splashLoadingText, loadingX, loadingY);

        batch.end();

        // asset loader
        boolean done = false;
        while (accumulator >= timeStep) {

            done = TetriBattle.assets.update(timeStepMillis);
            accumulator -= timeStep;
        }

        if (done && (this.splashTimer >= SPLASH_MAX_TIME)) {
            // FIXME: don't call them a million time.
            // Assets.getInstance().getAssets();
            // TetriBattleGame.analytics.logEvent("SPLASH_SCREEN_DONE");
            // this.game.setScreen(new MainMenuScreen(this.game));
        }
    }


}
