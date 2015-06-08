package com.fuzzywave.tetribattle.gameplay;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fuzzywave.tetribattle.TetriBattle;

import java.util.Random;

public enum BlockType {
    BLUE(0, TetriBattle.BLOCK_SPAWN_PROBABILITY, false),
    GREEN(1, TetriBattle.BLOCK_SPAWN_PROBABILITY, false),
    RED(2, TetriBattle.BLOCK_SPAWN_PROBABILITY, false),
    YELLOW(3, TetriBattle.BLOCK_SPAWN_PROBABILITY, false),
    BLUE_BREAKER(0, TetriBattle.BREAKER_SPAWN_PROBABILITY, true),
    GREEN_BREAKER(1, TetriBattle.BREAKER_SPAWN_PROBABILITY, true),
    RED_BREAKER(2, TetriBattle.BREAKER_SPAWN_PROBABILITY, true),
    YELLOW_BREAKER(3, TetriBattle.BREAKER_SPAWN_PROBABILITY, true),
    EMPTY(-1, 0f, false);

    public int color;
    public float probability;
    public boolean isBreaker;


    BlockType(int color, float probability, boolean isBreaker) {
        this.color = color;
        this.probability = probability;
        this.isBreaker = isBreaker;
    }

    public TextureRegion getTextureRegion() {

        switch (this) {
            case BLUE:
                return TetriBattle.assets.tileBlueTextureRegion;
            case GREEN:
                return TetriBattle.assets.tileGreenTextureRegion;
            case RED:
                return TetriBattle.assets.tileRedTextureRegion;
            case YELLOW:
                return TetriBattle.assets.tileYellowTextureRegion;
            case BLUE_BREAKER:
                return TetriBattle.assets.tileBlueBreakerTextureRegion;
            case GREEN_BREAKER:
                return TetriBattle.assets.tileGreenBreakerTextureRegion;
            case RED_BREAKER:
                return TetriBattle.assets.tileRedBreakerTextureRegion;
            case YELLOW_BREAKER:
                return TetriBattle.assets.tileYellowBreakerTextureRegion;
            case EMPTY:
                throw new RuntimeException("There is no texture for empty blocks!");

        }
        return null;
    }

    public static BlockType getRandomBlockType(Random random) {
        float nextFloat = random.nextFloat();
        for (BlockType blockType : BlockType.values()) {
            if (nextFloat < blockType.probability) {
                return blockType;
            } else {
                nextFloat -= blockType.probability;
            }
        }
        throw new RuntimeException(
                "Sorry, but, I couldn't decide on a proper BlockType. @getRandomBlockType");
        // return BlockType.EMPTY;
    }
}
