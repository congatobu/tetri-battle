package com.fuzzywave.tetribattle.gameplay.statemachine;


import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.gameplay.Block;
import com.fuzzywave.tetribattle.gameplay.BlockType;
import com.fuzzywave.tetribattle.gameplay.GameInstance;

/**
 * Fills the empty spaces in the game board by dropping the Blocks above.
 */
public class BlockDropState implements State{


    @Override
    public void enter(GameInstance gameInstance) {
        gameInstance.setNextDropTime(TetriBattle.BLOCK_FAST_DROP_TIMEOUT);
    }

    @Override
    public void update(GameInstance gameInstance, float delta) {
        // TODO gem'lerin alti tamamen bos olmadigi surece asagiya dusmeyecek.

        gameInstance.setNextDropTime(gameInstance.getNextDropTime() - delta);

        if(gameInstance.getNextDropTime() <= 0) {
            boolean blocksMoved = dropBlocks(gameInstance);

            if(!blocksMoved){
                StateMachine stateMachine = gameInstance.getStateMachine();
                stateMachine.changeState(stateMachine.gemConstructionState);
            }
        }
    }

    private boolean dropBlocks(GameInstance gameInstance){
        boolean dropped = false;
        for (int x = 0; x < TetriBattle.BLOCKS_WIDTH; ++x) {
            for (int y = 1; y < TetriBattle.BLOCKS_HEIGHT; ++y) {
                Block currentBlock = gameInstance.getBlock(x, y);
                Block bottomBlock = gameInstance.getBlock(x, y - 1);
                if ((currentBlock.getBlockType() != BlockType.EMPTY) && (bottomBlock.getBlockType() == BlockType.EMPTY)) {
                    bottomBlock.setBlockType(currentBlock.getBlockType());
                    currentBlock.setBlockType(BlockType.EMPTY);
                    dropped = true;
                }
            }
        }
        gameInstance.setNextDropTime(TetriBattle.BLOCK_FAST_DROP_TIMEOUT - gameInstance.getNextDropTime());
        return dropped;
    }

    @Override
    public void exit(GameInstance gameInstance) {

    }
}
