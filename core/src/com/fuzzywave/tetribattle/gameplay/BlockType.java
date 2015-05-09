package com.fuzzywave.tetribattle.gameplay;


import com.fuzzywave.tetribattle.TetriBattle;

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
}
