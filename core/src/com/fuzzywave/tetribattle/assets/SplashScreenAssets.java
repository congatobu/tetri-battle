package com.fuzzywave.tetribattle.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.I18NBundle;
import com.fuzzywave.tetribattle.TetriBattle;

import java.util.Locale;

public class SplashScreenAssets {

    public static BitmapFont splashLogoFont;
    public static BitmapFont splashLoadingFont;
    public static BitmapFont splashWaveFont;

    public static Color splashColor1 = Color.valueOf("fe8c00");
    public static Color splashColor2 = Color.valueOf("f83600");
    public static I18NBundle splashBundle;

    public static DistanceFieldShader distanceFieldShader = new DistanceFieldShader();

    private static String splashLogoFontFileName = "fonts/FUZZYWAVE.fnt";
    private static String splashLogoFontGlyphFileName = "fonts/FUZZYWAVE.png";

    private static String splashLoadingFontFileName = "fonts/LOADING.fnt";
    private static String splashLoadingFontGlyphFileName = "fonts/LOADING.png";

    private static String splashWaveFontFileName = "fonts/GRAPH.fnt";
    private static String splashWaveFontGlyphFileName = "fonts/GRAPH.png";

    public static void load(Locale locale) {

        TetriBattle.analytics.logEvent("SPLASH_SCREEN_ASSETS_LOAD", true);

        if (splashBundle == null) {
            if (locale == null) {
                locale = Locale.getDefault();
            }
            splashBundle = I18NBundle.createBundle(Gdx.files.internal("local/splash"), locale);
        } else {
            if (locale != null) {
                if (!splashBundle.getLocale().equals(locale)) {
                    splashBundle = I18NBundle.createBundle(Gdx.files.internal("local/splash"),
                            locale);
                }
            }
        }

        if (splashWaveFont != null) {
            splashWaveFont.dispose();
        }
        splashWaveFont = Assets.loadFont(splashWaveFontFileName, splashWaveFontGlyphFileName);

        if (splashLogoFont != null) {
            splashLogoFont.dispose();
        }
        splashLogoFont = Assets.loadFont(splashLogoFontFileName, splashLogoFontGlyphFileName);

        if (splashLoadingFont != null) {
            splashLoadingFont.dispose();
        }
        splashLoadingFont = Assets.loadFont(splashLoadingFontFileName, splashLoadingFontGlyphFileName);

        TetriBattle.analytics.endTimedEvent("SPLASH_SCREEN_ASSETS_LOAD");
    }

    public static class DistanceFieldShader extends ShaderProgram {
        public DistanceFieldShader() {
            super(Gdx.files.internal("shaders/distancefield.vert"), Gdx.files.internal("shaders/distancefield.frag"));
            if (!isCompiled()) {
                throw new RuntimeException("Shader compilation failed:\n" + getLog());
            }
        }

        /**
         * @param smoothing a value between 0 and 1
         */
        public void setSmoothing(float smoothing) {
            float delta = 0.5f * MathUtils.clamp(smoothing, 0, 1);
            setUniformf("u_lower", 0.5f - delta);
            setUniformf("u_upper", 0.5f + delta);
        }
    }

}
