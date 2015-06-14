package com.fuzzywave.tetribattle.gameplay;


public class Block {
    private BlockType blockType;
    private int gemId;

    public Block(BlockType blockType) {
        this.blockType = blockType;
        this.gemId = -1;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }

    public void setGemId(int gemId) {
        this.gemId = gemId;
    }

    public int getGemId() {
        return gemId;
    }
}
