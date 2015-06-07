package com.fuzzywave.tetribattle.assets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fuzzywave.tetribattle.TetriBattle;

public class Assets {

    public TextureRegion tileBlueTextureRegion;
    public TextureRegion tileGreenTextureRegion;
    public TextureRegion tileRedTextureRegion;
    public TextureRegion tileYellowTextureRegion;
    public TextureRegion tileBlueBreakerTextureRegion;
    public TextureRegion tileGreenBreakerTextureRegion;
    public TextureRegion tileRedBreakerTextureRegion;
    public TextureRegion tileYellowBreakerTextureRegion;

    public NinePatch glassBackgroundNinePatch;
    public NinePatch metalBackgroundNinePatch;

    public Color backgroundColor = Color.valueOf("3F7CB6");

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

    public static BitmapFont loadFont(String fntName, String glypName) {
        Texture texture = new Texture(Gdx.files.internal(glypName), true);
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        return new BitmapFont(Gdx.files.internal(fntName), new TextureRegion(texture), false);
    }

    public void dispose() {
        TetriBattle.logger.info("Disposing Assets instance.");
        TetriBattle.analytics.logEvent("ASSETS_DISPOSE");
        assetManager.dispose();
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

        this.assetManager.load("game/game.atlas", TextureAtlas.class);

        TetriBattle.analytics.endTimedEvent("ASSETS_LOAD");
    }

    public void getAssets() {
        TetriBattle.analytics.logEvent("ASSETS_GET", true);

        TextureAtlas textureAtlas = assetManager.get("game/game.atlas", TextureAtlas.class);

        tileBlueTextureRegion = textureAtlas.findRegion("tileBlue");
        tileGreenTextureRegion = textureAtlas.findRegion("tileGreen");
        tileRedTextureRegion = textureAtlas.findRegion("tileRed");
        tileYellowTextureRegion = textureAtlas.findRegion("tileYellow");

        tileBlueBreakerTextureRegion = textureAtlas.findRegion("tileBlueBreaker");
        tileGreenBreakerTextureRegion = textureAtlas.findRegion("tileGreenBreaker");
        tileRedBreakerTextureRegion = textureAtlas.findRegion("tileRedBreaker");
        tileYellowBreakerTextureRegion = textureAtlas.findRegion("tileYellowBreaker");

        glassBackgroundNinePatch = textureAtlas.createPatch("glass_background");
        metalBackgroundNinePatch = textureAtlas.createPatch("metal_background");

        TetriBattle.analytics.endTimedEvent("ASSETS_GET");
    }

    public void unloadAssets() {
        TetriBattle.analytics.logEvent("ASSETS_UNLOAD", true);

        assetManager.unload("game/game.atlas");

        TetriBattle.analytics.endTimedEvent("ASSETS_UNLOAD");
    }


}
