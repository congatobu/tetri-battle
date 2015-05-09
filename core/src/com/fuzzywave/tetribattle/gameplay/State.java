package com.fuzzywave.tetribattle.gameplay;

/**
 * All state objects implement this interface.
 */
public interface State {

    /**
     * Will execute when the state is entered
     */
    void enter(GameInstance gameInstance);

    /**
     * Is called on the current state of the FSM on each update step.
     *
     * @param delta update step frame timer.
     */
    void update(GameInstance gameInstance, float delta);


    /**
     * Will execute when the state is exited.
     */
    void exit(GameInstance gameInstance);

}
