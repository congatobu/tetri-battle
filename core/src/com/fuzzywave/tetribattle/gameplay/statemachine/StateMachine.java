package com.fuzzywave.tetribattle.gameplay.statemachine;

import com.fuzzywave.tetribattle.gameplay.GameInstance;

public class StateMachine {

    private GameInstance gameInstance;
    private State currentState;

    public State newPieceState;
    public State pieceDropState;
    public State blockDropState;
    public State destructionState;
    public State gemConstructionState;
    public State gameEndState;

    public StateMachine(GameInstance gameInstance) {

        this.gameInstance = gameInstance;

        this.newPieceState = new NewPieceState();
        this.pieceDropState = new PieceDropState();
        this.blockDropState = new BlockDropState();
        this.destructionState = new DestructionState();
        this.gemConstructionState = new GemConstructionState();
        this.gameEndState = new GameEndState();
    }

    public void update(float delta) {
        if (currentState != null) {
            currentState.update(gameInstance, delta);
        }
    }

    public void changeState(State newState) {

        if (currentState != null) {
            currentState.exit(gameInstance);
        }

        currentState = newState;

        if (currentState != null) {
            currentState.enter(gameInstance);
        }
    }

}
