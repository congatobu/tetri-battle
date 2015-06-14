package com.fuzzywave.tetribattle.gameplay;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.gameplay.statemachine.StateMachine;

import java.util.Arrays;
import java.util.Random;

public class GameInstance {


    private StateMachine stateMachine;
    private Array<Block> blocks;
    private Piece currentPiece;
    private Random random;

    private Rectangle drawingRectangle;
    private float blockToPixelWidth;
    private float blockToPixelHeight;

    private boolean moveLeft;
    private boolean moveRight;
    private boolean fastDrop;
    private boolean rotate;

    private float nextDropTime;
    private float destructionInterpolation;
    private int[] blocksVisitor;
    private boolean destructionMarker;

    // TODO G E M s
    private GemPool gemPool;

    public GameInstance(Rectangle drawingRectangle) {

        this.drawingRectangle = drawingRectangle;
        this.blockToPixelWidth = drawingRectangle.width / TetriBattle.BLOCKS_WIDTH;
        this.blockToPixelHeight = drawingRectangle.height / TetriBattle.BLOCKS_HEIGHT;

        random = new Random();

        blocks = new Array<Block>(TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT);
        for (int i = 0; i < TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT; i++) {
            blocks.add(new Block(BlockType.EMPTY));
        }

        gemPool = new GemPool();

        blocksVisitor = new int[TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT];

        currentPiece = new Piece();

        stateMachine = new StateMachine(this);

        stateMachine.changeState(stateMachine.pieceDropState);


    }

    public void update(float delta) {
        stateMachine.update(delta);

        drawBackground(delta);

        drawBoard(delta);

        drawPiece(delta);
    }

    private void drawBackground(float delta) {
        TetriBattle.spriteBatch.begin();

        TetriBattle.assets.glassBackgroundNinePatch.draw(TetriBattle.spriteBatch,
                                                         drawingRectangle.x - TetriBattle.BOARD_FRAME_PADDING,
                                                         drawingRectangle.y - TetriBattle.BOARD_FRAME_PADDING,
                                                         drawingRectangle.width + TetriBattle.BOARD_FRAME_PADDING * 2,
                                                         drawingRectangle.height + TetriBattle.BOARD_FRAME_PADDING * 2);
        TetriBattle.spriteBatch.end();
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
        TextureRegion textureRegion = block.getBlockType().getTextureRegion(block.getGemId() != -1);
        float xPixel = this.drawingRectangle.x + (x * blockToPixelWidth);// + (blockToPixelWidth / 2);
        float yPixel = this.drawingRectangle.y + (y * blockToPixelHeight);// + (blockToPixelHeight / 2);

        if (this.destructionMarker && getBlocksVisitor(x, y) == 2) {
            TetriBattle.spriteBatch.setColor(
                    TetriBattle.spriteBatch.getColor().lerp(TetriBattle.batchAlphaColor,
                                                            this.destructionInterpolation));

            TetriBattle.spriteBatch.draw(textureRegion, xPixel, yPixel, blockToPixelWidth,
                                         blockToPixelHeight);

            TetriBattle.spriteBatch.setColor(TetriBattle.batchColor);
        } else {

            TetriBattle.spriteBatch.draw(textureRegion, xPixel, yPixel, blockToPixelWidth,
                                         blockToPixelHeight);
        }
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
     * @return true, if there is a collision.
     */
    public boolean isColliding(Piece currentPiece, int horizontalMovement, int verticalMovement) {
        return isColliding(currentPiece.getFirstBlockPosition(),
                           currentPiece.getSecondBlockPosition(), horizontalMovement,
                           verticalMovement);
    }


    public boolean isColliding(IntArray firstPos, IntArray secondPos, int horizontalMovement,
                               int verticalMovement) {
        return isColliding(firstPos.get(0) + horizontalMovement,
                           firstPos.get(1) + verticalMovement) ||
                isColliding(secondPos.get(0) + horizontalMovement,
                            secondPos.get(1) + verticalMovement);
    }

    /**
     * @param positionX
     * @param positionY
     * @return true, if there is a collision.
     */
    public boolean isColliding(int positionX, int positionY) {

        if ((positionX < 0) || (positionX >= TetriBattle.BLOCKS_WIDTH)) {
            return true;
        }

        if ((positionY < 0) /*|| (positionY >= TetriBattle.BLOCKS_HEIGHT)*/) {
            return true;
        }
        if (positionY < TetriBattle.BLOCKS_HEIGHT) {
            if (getBlock(positionX, positionY).getBlockType() != BlockType.EMPTY) {
                return true;
            }
        }

        return false; // no collision.
    }

    public void attach(Piece currentPiece) {
        Block firstBlock = currentPiece.getFirstBlock();
        IntArray firstBlockPosition = currentPiece.getFirstBlockPosition();
        attach(firstBlock, firstBlockPosition.get(0), firstBlockPosition.get(1));

        Block secondBlock = currentPiece.getSecondBlock();
        IntArray secondBlockPosition = currentPiece.getSecondBlockPosition();
        attach(secondBlock, secondBlockPosition.get(0), secondBlockPosition.get(1));
    }

    private void attach(Block block, int positionX, int positionY) {
        // FIXME sadece 1 block'luk bosluk olan yerde piece spawn olup dusmeye basladiginda, ne yapilacak?
        if (positionY < TetriBattle.BLOCKS_HEIGHT) {
            getBlock(positionX, positionY).setBlockType(block.getBlockType());
        }
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }

    public boolean isRotate() {
        return rotate;
    }

    public void setRotate(boolean v) {
        this.rotate = v;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean v) {
        this.moveLeft = v;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean v) {
        this.moveRight = v;
    }

    public boolean isFastDrop() {
        return fastDrop;
    }

    public void setFastDrop(boolean v) {
        this.fastDrop = v;
    }


    public float getNextDropTime() {
        return nextDropTime;
    }

    public void setNextDropTime(float nextDropTime) {
        this.nextDropTime = nextDropTime;
    }

    public void setDestructionInterpolation(float destructionInterpolation) {
        this.destructionInterpolation = destructionInterpolation;
    }

    public int getBlocksVisitor(int x, int y) {
        return blocksVisitor[x + y * TetriBattle.BLOCKS_WIDTH];
    }

    public void setBlockVisitor(int x, int y, int value) {
        blocksVisitor[x + y * TetriBattle.BLOCKS_WIDTH] = value;
    }

    public void clearBlockVisitor() {
        Arrays.fill(blocksVisitor, 0);
    }

    public void setDestructionMarker(boolean destructionMarker) {
        this.destructionMarker = destructionMarker;
    }

    public boolean getDestructionMarker() {
        return destructionMarker;
    }

    public boolean tryToCreateGem(int x, int y, int width, int height) {
        int gemId = gemPool.tryToCreateGem(x, y, width, height);
        if (gemId != -1) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Block block = getBlock(x + i, y + j);
                    block.setGemId(gemId);


                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void destroyBlocks() {
        if (destructionMarker) {
            for (int x = 0; x < TetriBattle.BLOCKS_WIDTH; x++) {
                for (int y = 0; y < TetriBattle.BLOCKS_HEIGHT; y++) {
                    if (getBlocksVisitor(x, y) == 2) {
                        Block block = getBlock(x, y);
                        int gemId = block.getGemId();
                        if (gemId != -1) {
                            // TODO gem destuction counter.
                            gemPool.setDestructionMarker(gemId);
                        }
                        block.setBlockType(BlockType.EMPTY);
                        block.setGemId(-1);
                    }
                }
            }

            gemPool.destroyGems();

            this.destructionMarker = false;
        }
    }


    public void clear() {
        for (int x = 0; x < TetriBattle.BLOCKS_WIDTH; x++) {
            for (int y = 0; y < TetriBattle.BLOCKS_HEIGHT; y++) {

                Block block = getBlock(x, y);
                block.setBlockType(BlockType.EMPTY);

                gemPool.setDestructionMarker((block.getGemId() != -1 ? block.getGemId() : 0));
                block.setGemId(-1);
            }
        }

        gemPool.destroyGems();
    }

    public Gem getGem(int gemId) {
        return gemPool.getGem(gemId);
    }
}
