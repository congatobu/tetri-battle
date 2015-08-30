package com.fuzzywave.tetribattle.gameplay.statemachine;

import com.badlogic.gdx.utils.Array;
import com.fuzzywave.tetribattle.gameplay.GameInstance;
import com.fuzzywave.tetribattle.gameplay.Piece;


public class NewPieceState implements State {

    @Override
    public void enter(GameInstance gameInstance) {
        createRandomPiece(gameInstance);
        gameInstance.clearDestructionTrackers();
    }

    @Override
    public void update(GameInstance gameInstance, float delta) {
        StateMachine stateMachine = gameInstance.getStateMachine();
        stateMachine.changeState(stateMachine.pieceDropState);
    }

    @Override
    public void exit(GameInstance gameInstance) {

    }

    private void createRandomPiece(GameInstance gameInstance) {

        Array<Piece> nextPieces = gameInstance.getNextPieces();
        for (int i = 1; i < nextPieces.size; i++) {
            nextPieces.get(i - 1).initialize(nextPieces.get(i).getFirstBlock().getBlockType(),
                    nextPieces.get(i).getSecondBlock().getBlockType());
        }

        gameInstance.createRandomPiece(nextPieces.get(nextPieces.size - 1));
    }
}
