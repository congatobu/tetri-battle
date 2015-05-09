package com.fuzzywave.tetribattle.gameplay;

public class GameInstance {

    // TODO grid, tetris board
    // current piece vs.

    private StateMachine stateMachine;

    public GameInstance(){
        stateMachine = new StateMachine(this);
    }

    public void update(float delta){
        stateMachine.update(delta);
    }

}
