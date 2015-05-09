package com.fuzzywave.tetribattle.gameplay.statemachine;


import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.gameplay.Block;
import com.fuzzywave.tetribattle.gameplay.BlockType;
import com.fuzzywave.tetribattle.gameplay.GameInstance;

import java.util.Random;

public class PieceDropState implements State {

    @Override
    public void enter(GameInstance gameInstance) {
        if (checkGameEnd(gameInstance)) {
            createRandomPiece(gameInstance);
        } else {
            // TODO game end.
        }
    }

    @Override
    public void update(GameInstance gameInstance, float delta) {

    }

    @Override
    public void exit(GameInstance gameInstance) {

    }

    public boolean checkGameEnd(GameInstance gameInstance) {
        Block block = gameInstance.getBlock(TetriBattle.BLOCK_SPAWN_X, TetriBattle.BLOCK_SPAWN_Y);
        return (block.getBlockType().equals(BlockType.EMPTY));
    }

    private void createRandomPiece(GameInstance gameInstance) {

        BlockType firstBlockType = getRandomBlockType(gameInstance.getRandom());
        BlockType secondBlockType = getRandomBlockType(gameInstance.getRandom());

        gameInstance.getCurrentPiece().initialize(firstBlockType, secondBlockType);
    }

    private BlockType getRandomBlockType(Random random) {
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
