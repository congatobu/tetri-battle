package com.fuzzywave.tetribattle.gameplay;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
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

        stateMachine.changeState(stateMachine.pieceDropState);
        // XXX butun board'i blocklarla doldurma denemesi
        /*
        for(int x = 0; x < TetriBattle.BLOCKS_WIDTH; x++) {
            for (int y = 0; y < TetriBattle.BLOCKS_HEIGHT; y++) {
                Block block = getBlock(x, y);
                block.setBlockType(BlockType.getRandomBlockType(this.random));
            }
        }
        */
    }

    public void update(float delta) {
        stateMachine.update(delta);

        drawBoard(delta);

        drawPiece(delta);
    }

    private void drawPiece(float delta) {
        if (!currentPiece.isMovementDone()) {
            Block firstBlock = currentPiece.getFirstBlock();
            IntArray firstBlockPosition = currentPiece.getFirstBlockPosition();


            Block secondBlockBlock = currentPiece.getSecondBlock();
            IntArray secondBlockPosition = currentPiece.getSecondBlockPosition();

            TetriBattle.spriteBatch.begin();
            drawBlock(firstBlock, firstBlockPosition.get(0), firstBlockPosition.get(1));
            drawBlock(secondBlockBlock, secondBlockPosition.get(0), secondBlockPosition.get(1));
            TetriBattle.spriteBatch.end();
        }
    }

    private void drawBoard(float delta) {

        TetriBattle.spriteBatch.begin();
        for (int x = 0; x < TetriBattle.BLOCKS_WIDTH; x++) {
            for (int y = 0; y < TetriBattle.BLOCKS_HEIGHT; y++) {
                Block block = getBlock(x, y);
                if (block.getBlockType() != BlockType.EMPTY) {
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

    public Piece getCurrentPiece() {
        return currentPiece;
    }


    /**
     * Gets the Random Number Generator used by this GameInstance.
     */
    public Random getRandom() {
        return random;
    }


    /**
     * Checks to see if the current piece is colliding with anything.
     *
     * @param horizontalMovement parameter to check the next frame collisions in the given direction.
     * @param verticalMovement   parameter to check the next frame collisions in the given direction.
     */
    public boolean isColliding(Piece currentPiece, int horizontalMovement, int verticalMovement) {
        // TODO collision check.
        return false;
    }
}
