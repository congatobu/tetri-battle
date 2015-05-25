package com.fuzzywave.tetribattle.gameplay;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fuzzywave.tetribattle.TetriBattle;

import java.util.Random;

public enum BlockType {
    BLUE(TetriBattle.BLOCK_SPAWN_PROBABILITY),
    GREEN(TetriBattle.BLOCK_SPAWN_PROBABILITY),
    RED(TetriBattle.BLOCK_SPAWN_PROBABILITY),
    YELLOW(TetriBattle.BLOCK_SPAWN_PROBABILITY),
    BLUE_BREAKER(TetriBattle.BREAKER_SPAWN_PROBABILITY),
    GREEN_BREAKER(TetriBattle.BREAKER_SPAWN_PROBABILITY),
    RED_BREAKER(TetriBattle.BREAKER_SPAWN_PROBABILITY),
    YELLOW_BREAKER(TetriBattle.BREAKER_SPAWN_PROBABILITY),
    EMPTY(0f);

    public float probability;

    BlockType(float probability) {
        this.probability = probability;
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
