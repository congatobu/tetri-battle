package com.fuzzywave.tetribattle.gameplay.statemachine;


import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.gameplay.Block;
import com.fuzzywave.tetribattle.gameplay.BlockType;
import com.fuzzywave.tetribattle.gameplay.GameInstance;
import com.fuzzywave.tetribattle.gameplay.Piece;

public class PieceDropState implements State {

    @Override
    public void enter(GameInstance gameInstance) {
        if (checkGameEnd(gameInstance)) {
            createRandomPiece(gameInstance);
        } else {
            // TODO game end.
            TetriBattle.analytics.logEvent("GAME_END");
        }
    }

    @Override
    public void update(GameInstance gameInstance, float delta) {
        Piece currentPiece = gameInstance.getCurrentPiece();
        currentPiece.setNextDropTime(currentPiece.getNextDropTime() - delta);

        if (currentPiece.getNextDropTime() <= 0) {
            dropPiece(gameInstance);
        }

        checkUserInput(gameInstance);

        if (currentPiece.isMovementDone()) {
            // TODO switch to automatic drop state.
            StateMachine stateMachine = gameInstance.getStateMachine();
            stateMachine.changeState(stateMachine.pieceDropState);
        }
    }

    private void checkUserInput(GameInstance gameInstance) {
        if (gameInstance.isMoveLeft()) {
            moveLeft(gameInstance);
            gameInstance.setMoveLeft(false);
        }

        if (gameInstance.isMoveRight()) {
            moveRight(gameInstance);
            gameInstance.setMoveRight(false);
        }

        if (gameInstance.isFastDrop()) {
            // TODO fast drop
            gameInstance.setFastDrop(false);
        }

        if (gameInstance.isRotate()) {
            // TODO rotate
            gameInstance.setRotate(false);
        }
    }

    private void moveRight(GameInstance gameInstance) {
        Piece currentPiece = gameInstance.getCurrentPiece();
        if (!currentPiece.isMovementDone()) {
            if (!gameInstance.isColliding(currentPiece, 1, 0)) {
                currentPiece.moveRight();
            }
        }
    }

    private void moveLeft(GameInstance gameInstance) {
        Piece currentPiece = gameInstance.getCurrentPiece();
        if (!currentPiece.isMovementDone()) {
            if (!gameInstance.isColliding(currentPiece, -1, 0)) {
                currentPiece.moveLeft();
            }
        }
    }


    @Override
    public void exit(GameInstance gameInstance) {

    }

    public boolean checkGameEnd(GameInstance gameInstance) {
        Block block = gameInstance.getBlock(TetriBattle.BLOCK_SPAWN_X, TetriBattle.BLOCK_SPAWN_Y);
        return (block.getBlockType().equals(BlockType.EMPTY));
    }

    private void createRandomPiece(GameInstance gameInstance) {

        BlockType firstBlockType = BlockType.getRandomBlockType(gameInstance.getRandom());
        BlockType secondBlockType = BlockType.getRandomBlockType(gameInstance.getRandom());

        gameInstance.getCurrentPiece().initialize(firstBlockType, secondBlockType);
    }

    private void dropPiece(GameInstance gameInstance) {
        final int horizontalMovement = 0;
        final int verticalMovement = -1;

        Piece currentPiece = gameInstance.getCurrentPiece();
        if (!gameInstance.isColliding(currentPiece, horizontalMovement, verticalMovement)) {
            currentPiece.moveDown();
        } else {
            currentPiece.setMovementDone(true);
            gameInstance.attach(currentPiece);
        }

        currentPiece.setNextDropTime(TetriBattle.PIECE_DROP_TIMEOUT - currentPiece.getNextDropTime());
    }

}
