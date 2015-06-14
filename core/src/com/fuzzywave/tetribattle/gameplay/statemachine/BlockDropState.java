package com.fuzzywave.tetribattle.gameplay.statemachine;


import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.gameplay.Block;
import com.fuzzywave.tetribattle.gameplay.BlockType;
import com.fuzzywave.tetribattle.gameplay.GameInstance;
import com.fuzzywave.tetribattle.gameplay.Gem;
import com.fuzzywave.tetribattle.util.Util;

import java.util.Arrays;

/**
 * Fills the empty spaces in the game board by dropping the Blocks above.
 */
public class BlockDropState implements State {


    boolean[] blockCheckedArray;

    public BlockDropState() {
        blockCheckedArray = new boolean[TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT];
    }

    @Override
    public void enter(GameInstance gameInstance) {
        gameInstance.setNextDropTime(TetriBattle.BLOCK_FAST_DROP_TIMEOUT);
    }

    @Override
    public void update(GameInstance gameInstance, float delta) {

        gameInstance.setNextDropTime(gameInstance.getNextDropTime() - delta);

        if (gameInstance.getNextDropTime() <= 0) {
            boolean blocksMoved = dropBlocks(gameInstance);

            if (!blocksMoved) {
                StateMachine stateMachine = gameInstance.getStateMachine();
                stateMachine.changeState(stateMachine.gemConstructionState);
            }
        }
    }

    public boolean dropBlocks(GameInstance gameInstance) {
        boolean dropped = false;
        Arrays.fill(blockCheckedArray, false);

        for (int i = 0; i < TetriBattle.BLOCKS_WIDTH; ++i) {
            for (int j = 1; j < TetriBattle.BLOCKS_HEIGHT; ++j) {
                Block currentBlock = gameInstance.getBlock(i, j);
                boolean blockChecked = blockCheckedArray[Util.getPosition(i, j)];

                if ((currentBlock.getBlockType() != BlockType.EMPTY) && !blockChecked) {

                    int gemId = currentBlock.getGemId();
                    if (gemId == -1) {

                        Block bottomBlock = gameInstance.getBlock(i, j - 1);
                        if (bottomBlock.getBlockType() == BlockType.EMPTY) {
                            bottomBlock.setBlockType(currentBlock.getBlockType());
                            bottomBlock.setGemId(currentBlock.getGemId());
                            currentBlock.setBlockType(BlockType.EMPTY);
                            currentBlock.setGemId(-1);
                            dropped = true;
                        }

                    } else {
                        Gem gem = gameInstance.getGem(gemId);

                        boolean shouldDrop = false;
                        if (gem.y >= 1) {
                            shouldDrop = true;
                            for (int x = gem.x; x < gem.x + gem.width; x++) {
                                Block bottomBlock = gameInstance.getBlock(x,
                                                                          gem.y - 1); // ekranin disina tasabilir assagidan.
                                if (bottomBlock.getBlockType() != BlockType.EMPTY) {
                                    shouldDrop = false;
                                }
                            }
                        }
                        if (shouldDrop) {
                            for (int x = gem.x; x < gem.x + gem.width; x++) {
                                for (int y = gem.y; y < gem.y + gem.height; y++) {
                                    Block block = gameInstance.getBlock(x,
                                                                        y);
                                    Block bottomBlock = gameInstance.getBlock(x,
                                                                              y - 1);
                                    bottomBlock.setBlockType(
                                            block.getBlockType()); // move these to a function.
                                    bottomBlock.setGemId(block.getGemId());
                                    block.setBlockType(BlockType.EMPTY);
                                    block.setGemId(-1);
                                }
                            }

                            gem.y = gem.y - 1;
                            dropped = true;
                        } // should drop

                        for (int x = gem.x; x < gem.x + gem.width; x++) {
                            for (int y = gem.y; y < gem.y + gem.height; y++) {
                                blockCheckedArray[Util.getPosition(x, y)] = true;
                            }
                        }

                    } // gemId
                }
            }
        }
        gameInstance.setNextDropTime(
                TetriBattle.BLOCK_FAST_DROP_TIMEOUT - gameInstance.getNextDropTime());
        return dropped;
    }

    @Override
    public void exit(GameInstance gameInstance) {

    }
}
