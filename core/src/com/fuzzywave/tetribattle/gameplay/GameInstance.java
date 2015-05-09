package com.fuzzywave.tetribattle.gameplay;

import com.badlogic.gdx.utils.Array;
import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.gameplay.statemachine.StateMachine;

import java.util.Random;

public class GameInstance {

    // TODO grid, tetris board
    // current piece vs.

    private StateMachine stateMachine;
    private Array<Block> blocks;
    private Piece currentPiece;
    private Random random;

    public GameInstance() {

        random = new Random();

        blocks = new Array<Block>(TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT);
        for (int i = 0; i < TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT; i++) {
            blocks.add(new Block(BlockType.EMPTY));
        }

        currentPiece = new Piece();

        stateMachine = new StateMachine(this);
    }

    public void update(float delta) {
        stateMachine.update(delta);
    }

    public Block getBlock(int x, int y) {
        return blocks.get(x + y * TetriBattle.BLOCKS_WIDTH);
    }

    public Piece getCurrentPiece(){
        return currentPiece;
    }

    public Random getRandom() {
        return random;
    }

}
