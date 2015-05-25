package com.fuzzywave.tetribattle.gameplay;


public class Piece {

    private Block firstBlock;
    private Block secondBlock;

    private int posX;
    private int posY;

    private int rotation = 0;

    private float nextDropTime;

    public static final int ROTATION_N = 0;
    public static final int ROTATION_E = 1;
    public static final int ROTATION_S = 2;
    public static final int ROTATION_W = 3;

    public Piece() {
        this.firstBlock = new Block(BlockType.EMPTY);
        this.secondBlock = new Block(BlockType.EMPTY);
    }

    public void initialize(BlockType firstBlockType, BlockType secondBlockType) {
        this.firstBlock.setBlockType(firstBlockType);
        this.secondBlock.setBlockType(secondBlockType);

        // TODO where to?
        this.rotation = ROTATION_N;
    }

    public void rotate() {
        rotation++;
        rotation %= 4;
    }

    public Block getFirstBlock() {
        return firstBlock;
    }

    public Block getSecondBlock() {
        return secondBlock;
    }

    public void setNextDropTime(float nextDropTime) {
        this.nextDropTime = nextDropTime;
    }

    public float getNextDropTime() {
        return this.nextDropTime;
    }
}
