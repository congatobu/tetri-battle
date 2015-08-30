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
        splashLoadingFont = Assets.loadFont(splashLoadingFontFileName,
                splashLoadingFontGlyphFileName);

        // TODO timed event.
        TetriBattle.analytics.logEvent("SPLASH_ASSETS", "Splash Assets Load");
    }

}
