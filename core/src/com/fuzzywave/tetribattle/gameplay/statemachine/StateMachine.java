package com.fuzzywave.tetribattle.gameplay.statemachine;

import com.fuzzywave.tetribattle.gameplay.GameInstance;

public class StateMachine {

    private GameInstance gameInstance;
    private State currentState;

    public State pieceDropState;

    public StateMachine(GameInstance gameInstance) {

        this.gameInstance = gameInstance;

        this.pieceDropState = new PieceDropState();
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
