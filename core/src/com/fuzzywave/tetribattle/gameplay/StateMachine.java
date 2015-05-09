package com.fuzzywave.tetribattle.gameplay;

public class StateMachine {

    private GameInstance gameInstance;
    private State currentState;

    public StateMachine(GameInstance gameInstance) {

        this.gameInstance = gameInstance;
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
