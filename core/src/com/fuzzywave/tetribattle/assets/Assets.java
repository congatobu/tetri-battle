package com.fuzzywave.tetribattle.assets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.fuzzywave.tetribattle.TetriBattle;

public class Assets {

    private AssetManager assetManager;

    public Assets() {

        TetriBattle.logger.debug("Creating new Assets instance.");
        TetriBattle.analytics.logEvent("ASSETS_NEW_INSTANCE", true);

        ResolutionFileResolver.Resolution _240x320 = new ResolutionFileResolver.Resolution(240, 320,
                "240x320");
        ResolutionFileResolver.Resolution _320x480 = new ResolutionFileResolver.Resolution(320, 480,
                "320x480");
        ResolutionFileResolver.Resolution _480x800 = new ResolutionFileResolver.Resolution(480, 800,
                "480x800");
        ResolutionFileResolver.Resolution _720x1280 = new ResolutionFileResolver.Resolution(720,
                1280,
                "720x1280");
        ResolutionFileResolver.Resolution _1080x1920 = new ResolutionFileResolver.Resolution(1080,
                1920,
                "1080x1920");

        ResolutionFileResolver resolver = new ResolutionFileResolver(
                new InternalFileHandleResolver(), _240x320, _320x480, _480x800, _720x1280,
                _1080x1920);

        this.assetManager = new AssetManager();
        this.assetManager.setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));

        TetriBattle.analytics.endTimedEvent("ASSETS_NEW_INSTANCE");
    }


    public void dispose() {
        TetriBattle.logger.info("Disposing Assets instance.");
        TetriBattle.analytics.logEvent("ASSETS_DISPOSE");
        assetManager.dispose();
    }

    public static BitmapFont loadFont(String fileName, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fileName));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    public boolean update(int millis) {

        boolean result = this.assetManager.update(millis);
        return result;
    }

    public float getProgress() {
        return this.assetManager.getProgress();
    }

    public void loadAssets() {
        TetriBattle.analytics.logEvent("ASSETS_LOAD", true);
        // TODO loadAssets
        TetriBattle.analytics.endTimedEvent("ASSETS_LOAD");
    }

    public void getAssets() {
        TetriBattle.analytics.logEvent("ASSETS_GET", true);
        // TODO getAssets
        TetriBattle.analytics.endTimedEvent("ASSETS_GET");
    }

    public void unloadAssets() {
        TetriBattle.analytics.logEvent("ASSETS_UNLOAD", true);
        // TODO unloadAssets
        TetriBattle.analytics.endTimedEvent("ASSETS_UNLOAD");
    }


}
