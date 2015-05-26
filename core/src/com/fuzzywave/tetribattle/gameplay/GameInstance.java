package com.fuzzywave.tetribattle.gameplay;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.gameplay.statemachine.StateMachine;

import java.util.Random;

public class GameInstance {

    private StateMachine stateMachine;
    private Array<Block> blocks;
    private Piece currentPiece;
    private Random random;

    private Rectangle drawingRectangle;
    private float blockToPixelWidth;
    private float blockToPixelHeight;

    public GameInstance(Rectangle drawingRectangle) {

        this.drawingRectangle = drawingRectangle;
        this.blockToPixelWidth = drawingRectangle.width / TetriBattle.BLOCKS_WIDTH;
        this.blockToPixelHeight = drawingRectangle.height / TetriBattle.BLOCKS_HEIGHT;

        random = new Random();

        blocks = new Array<Block>(TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT);
        for (int i = 0; i < TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT; i++) {
            blocks.add(new Block(BlockType.EMPTY));
        }

        currentPiece = new Piece();

        stateMachine = new StateMachine(this);

        // XXX butun board'i blocklarla doldurma denemesi
        for(int x = 0; x < TetriBattle.BLOCKS_WIDTH; x++) {
            for (int y = 0; y < TetriBattle.BLOCKS_HEIGHT; y++) {
                Block block = getBlock(x, y);
                block.setBlockType(BlockType.getRandomBlockType(this.random));
            }
        }
    }

    public void update(float delta) {
        stateMachine.update(delta);

        drawBoard(delta);
    }

    private void drawBoard(float delta) {

        TetriBattle.spriteBatch.begin();
        for(int x = 0; x < TetriBattle.BLOCKS_WIDTH; x++){
            for(int y = 0; y < TetriBattle.BLOCKS_HEIGHT; y++){
                Block block = getBlock(x, y);
                if(block.getBlockType() != BlockType.EMPTY) {
                    drawBlock(block, x, y);
                }
            }
        }
        TetriBattle.spriteBatch.end();
    }

    private void drawBlock(Block block, int x, int y) {
        TextureRegion textureRegion = block.getBlockType().getTextureRegion();
        float xPixel = this.drawingRectangle.x + (x * blockToPixelWidth);// + (blockToPixelWidth / 2);
        float yPixel = this.drawingRectangle.y + (y * blockToPixelHeight);// + (blockToPixelHeight / 2);

        TetriBattle.spriteBatch.draw(textureRegion, xPixel, yPixel, blockToPixelWidth, blockToPixelHeight);
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
