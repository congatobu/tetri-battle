package com.fuzzywave.tetribattle.gameplay;


import com.badlogic.gdx.utils.IntArray;
import com.fuzzywave.tetribattle.TetriBattle;

public class Piece {

    public static final int ROTATION_N = 0;
    public static final int ROTATION_E = 1;
    public static final int ROTATION_S = 2;
    public static final int ROTATION_W = 3;
    private Block firstBlock;
    private Block secondBlock;
    private IntArray firstBlockPosition;
    private IntArray secondBlockPosition;

    private int rotation = 0;
    private float nextDropTime;
    private boolean movementDone;

    public Piece() {
        this.firstBlock = new Block(BlockType.EMPTY);
        this.secondBlock = new Block(BlockType.EMPTY);

        this.firstBlockPosition = new IntArray(2);
        this.firstBlockPosition.add(0);
        this.firstBlockPosition.add(0);

        this.secondBlockPosition = new IntArray(2);
        this.secondBlockPosition.add(0);
        this.secondBlockPosition.add(0);
    }

    public Block getFirstBlock() {
        return firstBlock;
    }

    public Block getSecondBlock() {
        return secondBlock;
    }

    public float getNextDropTime() {
        return this.nextDropTime;
    }

    public void setNextDropTime(float nextDropTime) {
        this.nextDropTime = nextDropTime;
    }

    public void initialize(BlockType firstBlockType, BlockType secondBlockType) {
        this.firstBlock.setBlockType(firstBlockType);
        this.secondBlock.setBlockType(secondBlockType);

        // TODO where to?
        this.rotation = ROTATION_N;

        this.firstBlockPosition.set(0, TetriBattle.BLOCK_SPAWN_X); // x
        this.firstBlockPosition.set(1, TetriBattle.BLOCK_SPAWN_Y + 1); // y

        this.secondBlockPosition.set(0, TetriBattle.BLOCK_SPAWN_X); // x
        this.secondBlockPosition.set(1, TetriBattle.BLOCK_SPAWN_Y + 2); // y

        this.nextDropTime = TetriBattle.PIECE_DROP_TIMEOUT;
        this.movementDone = false;
    }

    public void tryTorotate(GameInstance gameInstance) {

        if (!isMovementDone()) {
            switch (rotation) {
                case ROTATION_N:
                    // 0 2 0   0 0 0
                    // 0 1 0 > 0 1 2
                    // 0 0 0   0 0 0
                    if (!gameInstance.isColliding(firstBlockPosition.get(0) + 1, firstBlockPosition.get(1))) {
                        secondBlockPosition.set(0, firstBlockPosition.get(0) + 1);
                        secondBlockPosition.set(1, firstBlockPosition.get(1));
                        rotation = ROTATION_E;
                    }
                    break;
                case ROTATION_E:
                    // 0 0 0   0 0 0
                    // 0 1 2 > 0 1 0
                    // 0 0 0   0 2 0
                    if (!gameInstance.isColliding(firstBlockPosition.get(0), firstBlockPosition.get(1) - 1)) {
                        secondBlockPosition.set(0, firstBlockPosition.get(0));
                        secondBlockPosition.set(1, firstBlockPosition.get(1) - 1);
                        rotation = ROTATION_S;
                    }
                    break;
                case ROTATION_S:
                    // 0 0 0   0 0 0
                    // 0 1 0 > 0 0 0
                    // 0 2 0   0 2 1
                    if (!gameInstance.isColliding(secondBlockPosition.get(0) + 1, secondBlockPosition.get(1))) {
                        firstBlockPosition.set(0, secondBlockPosition.get(0) + 1);
                        firstBlockPosition.set(1, secondBlockPosition.get(1));
                        rotation = ROTATION_W;
                    }
                    break;
                case ROTATION_W:
                    // 0 0 0   0 0 0
                    // 0 2 1 > 0 2 0
                    // 0 0 0   0 1 0
                    if (!gameInstance.isColliding(secondBlockPosition.get(0), secondBlockPosition.get(1) - 1)) {
                        firstBlockPosition.set(0, secondBlockPosition.get(0));
                        firstBlockPosition.set(1, secondBlockPosition.get(1) - 1);
                        rotation = ROTATION_N;
                    }
                    break;
                default:
                    throw new RuntimeException("Undefined rotation index: " + rotation);
            }
        }
    }

    public void moveDown() {
        firstBlockPosition.incr(1, -1);
        secondBlockPosition.incr(1, -1);
    }

    public void tryToMoveLeft(GameInstance gameInstance) {
        if (!isMovementDone()) {
            if (!gameInstance.isColliding(this, -1, 0)) {
                moveLeft();
            }
        }
    }

    public void tryToMoveRight(GameInstance gameInstance) {
        if (!isMovementDone()) {
            if (!gameInstance.isColliding(this, 1, 0)) {
                moveRight();
            }
        }
    }

    public void moveLeft() {
        firstBlockPosition.incr(0, -1);
        secondBlockPosition.incr(0, -1);
    }

    public void moveRight() {
        firstBlockPosition.incr(0, 1);
        secondBlockPosition.incr(0, 1);
    }

    public void moveUp() {
        firstBlockPosition.incr(1, 1);
        secondBlockPosition.incr(1, 1);
    }


    public boolean isMovementDone() {
        return movementDone;
    }

    public void setMovementDone(boolean movementDone) {
        this.movementDone = movementDone;
    }

    public IntArray getFirstBlockPosition() {
        return this.firstBlockPosition;
    }

    public IntArray getSecondBlockPosition() {
        return this.secondBlockPosition;
    }
}
