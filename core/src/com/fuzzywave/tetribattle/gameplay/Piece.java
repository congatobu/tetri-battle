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

    private IntArray tempFirstBlockPosition;
    private IntArray tempSecondBlockPosition;

    private int rotation = 0;
    private boolean movementDone;
    private boolean fastDrop;

    public Piece() {
        this.firstBlock = new Block(BlockType.EMPTY);
        this.secondBlock = new Block(BlockType.EMPTY);

        this.firstBlockPosition = new IntArray(2);
        this.firstBlockPosition.add(0);
        this.firstBlockPosition.add(0);

        this.secondBlockPosition = new IntArray(2);
        this.secondBlockPosition.add(0);
        this.secondBlockPosition.add(0);

        this.tempFirstBlockPosition = new IntArray(firstBlockPosition);
        this.tempSecondBlockPosition = new IntArray(secondBlockPosition);
    }

    public Block getFirstBlock() {
        return firstBlock;
    }

    public Block getSecondBlock() {
        return secondBlock;
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

        this.movementDone = false;
        this.fastDrop = false;
    }

    public void tryToRotate(GameInstance gameInstance) {

        if (!isMovementDone()) {

            tempFirstBlockPosition.set(0, firstBlockPosition.get(0));
            tempFirstBlockPosition.set(1, firstBlockPosition.get(1));
            tempSecondBlockPosition.set(0, secondBlockPosition.get(0));
            tempSecondBlockPosition.set(1, secondBlockPosition.get(1));

            IntArray tempFirstBlockPosition = new IntArray(firstBlockPosition);
            IntArray tempSecondBlockPosition = new IntArray(secondBlockPosition);
            int tempRotation = 0;
            int wallKickX = 0;
            int wallKickY = 0;

            switch (rotation) {
                case ROTATION_N:
                    // 0 2 0   0 0 0
                    // 0 1 0 > 0 1 2
                    // 0 0 0   0 0 0
                    tempSecondBlockPosition.set(0, tempFirstBlockPosition.get(0) + 1);
                    tempSecondBlockPosition.set(1, tempFirstBlockPosition.get(1));
                    wallKickX = -1;
                    wallKickY = 0;
                    tempRotation = ROTATION_E;
                    break;
                case ROTATION_E:
                    // 0 0 0   0 0 0
                    // 0 1 2 > 0 1 0
                    // 0 0 0   0 2 0
                    tempSecondBlockPosition.set(0, tempFirstBlockPosition.get(0));
                    tempSecondBlockPosition.set(1, tempFirstBlockPosition.get(1) - 1);
                    wallKickX = 0;
                    wallKickY = -1;
                    tempRotation = ROTATION_S;
                    break;
                case ROTATION_S:
                    // 0 0 0   0 0 0
                    // 0 1 0 > 0 0 0
                    // 0 2 0   0 2 1
                    tempFirstBlockPosition.set(0, tempSecondBlockPosition.get(0) + 1);
                    tempFirstBlockPosition.set(1, tempSecondBlockPosition.get(1));
                    wallKickX = -1;
                    wallKickY = 0;
                    tempRotation = ROTATION_W;
                    break;
                case ROTATION_W:
                    // 0 0 0   0 0 0
                    // 0 2 1 > 0 2 0
                    // 0 0 0   0 1 0
                    tempFirstBlockPosition.set(0, tempSecondBlockPosition.get(0));
                    tempFirstBlockPosition.set(1, tempSecondBlockPosition.get(1) - 1);
                    wallKickX = 0;
                    wallKickY = -1;
                    tempRotation = ROTATION_N;
                    break;
                default:
                    throw new RuntimeException("Undefined rotation index: " + rotation);
            }

            if (!gameInstance.isColliding(tempFirstBlockPosition, tempSecondBlockPosition, 0, 0)) {
                firstBlockPosition.set(0, tempFirstBlockPosition.get(0));
                firstBlockPosition.set(1, tempFirstBlockPosition.get(1));
                secondBlockPosition.set(0, tempSecondBlockPosition.get(0));
                secondBlockPosition.set(1, tempSecondBlockPosition.get(1));
                rotation = tempRotation;
            } else if (!gameInstance.isColliding(tempFirstBlockPosition, tempSecondBlockPosition, wallKickX, wallKickY)) { // try again with the wall-kick.
                firstBlockPosition.set(0, tempFirstBlockPosition.get(0) + wallKickX);
                firstBlockPosition.set(1, tempFirstBlockPosition.get(1) + wallKickY);
                secondBlockPosition.set(0, tempSecondBlockPosition.get(0) + wallKickX);
                secondBlockPosition.set(1, tempSecondBlockPosition.get(1) + wallKickY);
                rotation = tempRotation;
            }
        }
    }

    public boolean tryToMoveDown(GameInstance gameInstance) {
        if (!isMovementDone()) {
            if (!gameInstance.isColliding(this, 0, -1)) {
                moveDown();
                return true;
            }
        }
        return false;
    }

    public boolean tryToMoveLeft(GameInstance gameInstance) {
        if (!isMovementDone()) {
            if (!gameInstance.isColliding(this, -1, 0)) {
                moveLeft();
                return true;
            }
        }
        return false;
    }

    public boolean tryToMoveRight(GameInstance gameInstance) {
        if (!isMovementDone()) {
            if (!gameInstance.isColliding(this, 1, 0)) {
                moveRight();
                return true;
            }
        }
        return false;
    }


    public void moveDown() {
        firstBlockPosition.incr(1, -1);
        secondBlockPosition.incr(1, -1);
    }

    public void moveLeft() {
        firstBlockPosition.incr(0, -1);
        secondBlockPosition.incr(0, -1);
    }

    public void moveRight() {
        firstBlockPosition.incr(0, 1);
        secondBlockPosition.incr(0, 1);
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

    public void setFastDrop(boolean fastDrop) {
        this.fastDrop = fastDrop;
    }

    public boolean isFastDrop() {
        return fastDrop;
    }
}
