package com.fuzzywave.tetribattle.gameplay;


public class Piece {

    private Block firstBlock;
    private Block secondBlock;

    public Piece() {
        this.firstBlock = new Block(BlockType.EMPTY);
        this.secondBlock = new Block(BlockType.EMPTY);
    }

    public void initialize(BlockType firstBlockType, BlockType secondBlockType) {
        this.firstBlock.setBlockType(firstBlockType);
        this.secondBlock.setBlockType(secondBlockType);

        // TODO where to?
    }

    public Block getFirstBlock() {
        return firstBlock;
    }

    public Block getSecondBlock() {
        return secondBlock;
    }
}
