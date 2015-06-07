package com.fuzzywave.tetribattle.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.assets.SplashScreenAssets;

public class SplashScreen extends AbstractScreen {

    private static final float SPLASH_MIN_TIME = 2.0f;
    private static final float SPLASH_MAX_TIME = 3.0f;
    private static final float LOADING_TIME = 1.0f;
    private final float timeStep = 1.0f / 60.0f;
    private final int timeStepMillis = Math.round(timeStep * 1000);
    private GlyphLayout layout = new GlyphLayout();
    private Viewport viewport;
    private Camera camera;
    private String splashLogoString;
    private String splashWaveString;
    private String splashWaveString2;
    private String splashLoadingString;
    private float splashTimer;
    private float loadingTimer;
    private float accumulator;


    public SplashScreen() {

        // TODO read default locale from user conf.
        SplashScreenAssets.load(null);

        this.camera = new OrthographicCamera();
        this.viewport = new ExtendViewport(TetriBattle.WORLD_WIDTH_PIXEL,
                                           TetriBattle.WORLD_HEIGHT_PIXEL,
                                           this.camera);
    }

    @Override
    public void show() {
        super.show();

        TetriBattle.spriteBatch.setProjectionMatrix(this.camera.combined);
        TetriBattle.shapeRenderer.setProjectionMatrix(this.camera.combined);

        TetriBattle.assets.loadAssets();

        this.splashLogoString = SplashScreenAssets.splashBundle.get("SPLASH_LOGO_STRING");
        this.splashWaveString = SplashScreenAssets.splashBundle.get("SPLASH_WAVE_STRING");
        this.splashWaveString2 = SplashScreenAssets.splashBundle.get("SPLASH_WAVE_STRING_2");
        this.splashLoadingString = SplashScreenAssets.splashBundle.get("SPLASH_LOADING_STRING");

        this.splashTimer = .0f;
        this.loadingTimer = .0f;
        this.accumulator = .0f;

        TetriBattle.analytics.logEvent("SPLASH_SCREEN_SHOWED");
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.viewport.update(width, height);
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // bunu neden her render call'inda yapiyoruz yaw?
        TetriBattle.spriteBatch.setProjectionMatrix(camera.combined);
        TetriBattle.shapeRenderer.setProjectionMatrix(camera.combined);


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
        String splashWaveText = splashWaveString.substring(0, interpolatedStringLength);
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


        // background gradient.
        TetriBattle.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        TetriBattle.shapeRenderer.rect(-this.viewport.getWorldWidth() / 2,
                                       -this.viewport.getWorldHeight() / 2,
                                       this.viewport.getWorldWidth(),
                                       this.viewport.getWorldHeight(),
                                       SplashScreenAssets.splashColor1,
                                       SplashScreenAssets.splashColor2,
                                       SplashScreenAssets.splashColor1,
                                       SplashScreenAssets.splashColor2);

        TetriBattle.shapeRenderer.end();


        TetriBattle.spriteBatch.begin();


        TetriBattle.spriteBatch.setShader(SplashScreenAssets.distanceFieldShader);

        float logoSmoothing = 1 / 8f;
        float logoScale = 3.0f;
        float logoBaseLineShift = -8;

        SplashScreenAssets.splashLogoFont.getData().setScale(logoScale);
        layout.setText(SplashScreenAssets.splashLogoFont, splashLogoString, 0,
                       splashLogoString.length(),
                       SplashScreenAssets.splashLogoFont.getColor(), 0, Align.center, false, null);
        SplashScreenAssets.distanceFieldShader.setSmoothing(logoSmoothing / logoScale);
        SplashScreenAssets.splashLogoFont.draw(TetriBattle.spriteBatch, layout,
                                               0,
                                               this.viewport.getScreenHeight() / 2 + logoScale * logoBaseLineShift);


        float waveSmoothing = 1 / 8f;
        float waveScale = 3.1f;
        float waveBaseLineShift = -8;
        SplashScreenAssets.splashWaveFont.getData().setScale(waveScale);
        SplashScreenAssets.distanceFieldShader.setSmoothing(waveSmoothing / waveScale);

        layout.setText(SplashScreenAssets.splashWaveFont, splashWaveText, 0,
                       splashWaveText.length(),
                       SplashScreenAssets.splashWaveFont.getColor(), 0, Align.left, false, null);
        SplashScreenAssets.splashWaveFont.draw(TetriBattle.spriteBatch, layout,
                                               -TetriBattle.WORLD_WIDTH_PIXEL / 2,
                                               0);

        float h = layout.height;
        layout.setText(SplashScreenAssets.splashWaveFont, splashWaveText2, 0,
                       splashWaveText2.length(),
                       SplashScreenAssets.splashWaveFont.getColor(), 0, Align.left, false, null);
        SplashScreenAssets.splashWaveFont.draw(TetriBattle.spriteBatch, layout,
                                               -TetriBattle.WORLD_WIDTH_PIXEL / 2,
                                               (-h + waveBaseLineShift - 2) * waveScale);


        float loadingSmoothing = 1 / 8f;
        float loadingScale = 3.1f;
        float loadingBaseLineShift = -8;
        SplashScreenAssets.splashLoadingFont.getData().setScale(loadingScale);
        SplashScreenAssets.distanceFieldShader.setSmoothing(loadingSmoothing / loadingScale);

        layout.setText(SplashScreenAssets.splashLoadingFont, splashLoadingText, 0,
                       splashLoadingText.length(),
                       SplashScreenAssets.splashLoadingFont.getColor(), 0, Align.left, false, null);
        SplashScreenAssets.splashLoadingFont.draw(TetriBattle.spriteBatch, layout,
                                                  -layout.width,
                                                  -TetriBattle.WORLD_HEIGHT_PIXEL / 3);

        //TetriBattle.spriteBatch.flush();
        TetriBattle.spriteBatch.setShader(null);
        TetriBattle.spriteBatch.end();

        // asset loader
        boolean done = false;
        while (accumulator >= timeStep) {

            done = TetriBattle.assets.update(timeStepMillis);
            accumulator -= timeStep;
        }

        if (done && (this.splashTimer >= SPLASH_MAX_TIME)) {
            TetriBattle.assets.getAssets();
            TetriBattle.analytics.logEvent("SPLASH_SCREEN_DONE");
            TetriBattle.game.setScreen(new GameScreen());
        }
    }
}
