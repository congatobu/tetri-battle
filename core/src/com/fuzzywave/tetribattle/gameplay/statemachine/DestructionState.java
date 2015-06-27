package com.fuzzywave.tetribattle.gameplay.statemachine;

import com.badlogic.gdx.math.Interpolation;
import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.gameplay.Block;
import com.fuzzywave.tetribattle.gameplay.BlockType;
import com.fuzzywave.tetribattle.gameplay.GameInstance;

/**
 * Eliminates all adjacent same-color blocks of breaker blocks.
 */
public class DestructionState implements State {

    private float timer;

    @Override
    public void enter(GameInstance gameInstance) {

        timer = 0f;
        gameInstance.setDestructionInterpolation(0f); // TODO 0 or 1 ?

        markForDestruction(gameInstance);
    }


    /**
     * Marks blocks for destruction.
     */
    private void markForDestruction(GameInstance gameInstance) {
        gameInstance.clearBlockVisitor();
        boolean marker = false;
        for (int x = 0; x < TetriBattle.BLOCKS_WIDTH; x++) {
            for (int y = 0; y < TetriBattle.BLOCKS_HEIGHT; y++) {

                Block block = gameInstance.getBlock(x, y);
                if ((block.getBlockType().isBreaker) && (gameInstance.getBlocksVisitor(x,
                                                                                       y) == 0)) {
                    if (allNeighbourSearch(gameInstance, block.getBlockType().color, x, y)) {
                        marker = true;
                    }
                }
            }
        }

        gameInstance.setDestructionMarker(marker);
    }

    /**
     * @param gameInstance
     * @param color
     * @param x
     * @param y
     * @return true, if there is at least one block that's going to be destroyed.
     */
    private boolean allNeighbourSearch(GameInstance gameInstance, int color, int x, int y) {

        boolean hasNeighbour = false;
        gameInstance.setBlockVisitor(x, y, 1); // mark visited.

        // left
        if (neighbourSearch(gameInstance, color, x - 1, y)) {
            hasNeighbour = true;
        }

        // right
        if (neighbourSearch(gameInstance, color, x + 1, y)) {
            hasNeighbour = true;
        }

        // top
        if (neighbourSearch(gameInstance, color, x, y + 1)) {
            hasNeighbour = true;
        }

        // down
        if (neighbourSearch(gameInstance, color, x, y - 1)) {
            hasNeighbour = true;
        }

        if (hasNeighbour) {
            gameInstance.setBlockVisitor(x, y, 2); // mark for destruction
        }

        return hasNeighbour;
    }

    private boolean neighbourSearch(GameInstance gameInstance, int color, int x, int y) {
        if ((x >= 0) && (x < TetriBattle.BLOCKS_WIDTH) && (y >= 0) && (y < TetriBattle.BLOCKS_HEIGHT)) {

            Block neighbourBlock = gameInstance.getBlock(x, y);
            if (neighbourBlock.getBlockType().color == color) {
                if (gameInstance.getBlocksVisitor(x, y) == 0) {
                    allNeighbourSearch(gameInstance, color, x, y);
                }
                return true;
            }

        }

        return false;
    }

    @Override
    public void update(GameInstance gameInstance, float delta) {
        if (gameInstance.getDestructionMarker()) {
            // there is at least one block that has been destroyed.
            timer += delta;
            gameInstance.setDestructionInterpolation(
                    Interpolation.linear.apply(timer / TetriBattle.DESTRUCTION_TIMEOUT));

            if (timer >= TetriBattle.DESTRUCTION_TIMEOUT) {
                StateMachine stateMachine = gameInstance.getStateMachine();
                stateMachine.changeState(stateMachine.blockDropState);
            }
        } else {
            StateMachine stateMachine = gameInstance.getStateMachine();
            stateMachine.changeState(stateMachine.newPieceState);
        }

    }

    @Override
    public void exit(GameInstance gameInstance) {
        gameInstance.destroyBlocks();
    }


}
