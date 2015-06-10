package com.fuzzywave.tetribattle.gameplay.statemachine;


import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.gameplay.BlockType;
import com.fuzzywave.tetribattle.gameplay.GameInstance;
import com.fuzzywave.tetribattle.util.IntegerPoint;
import com.fuzzywave.tetribattle.util.IntegerRectangle;

public class GemConstructionState implements State {

    private int[][] heights;
    private int[][] widths;
    private int[][] cornerPointX;

    private IntegerPoint maxHistogramPoint;
    private IntegerRectangle maxRectangle;

    public GemConstructionState() {
        heights = new int[TetriBattle.BLOCKS_WIDTH][TetriBattle.BLOCKS_HEIGHT];
        widths = new int[TetriBattle.BLOCKS_WIDTH][TetriBattle.BLOCKS_HEIGHT];
        cornerPointX = new int[TetriBattle.BLOCKS_WIDTH][TetriBattle.BLOCKS_HEIGHT];

        maxHistogramPoint = new IntegerPoint(-1, -1);
        maxRectangle = new IntegerRectangle(-1, -1, -1, -1);
    }

    @Override
    public void enter(GameInstance gameInstance) {
        constructGems(gameInstance, BlockType.BLUE);
    }

    private void constructGems(GameInstance gameInstance, BlockType blockType) {
        initializeHeights(gameInstance, blockType);

        boolean continueWhile = true;
        while (continueWhile) {
            initializeWidths();
            boolean hasRectangle = setMaxRectangle();
            if (hasRectangle) {
                attachGem(maxRectangle);
                updateHeights();
                continueWhile = true;
            } else {
                continueWhile = false;
            }
        }
    }

    /**
     * Calculates the histogram of the grid.
     *
     * @param gameInstance
     * @param blockType
     */
    private void initializeHeights(GameInstance gameInstance, BlockType blockType) {
        for (int x = 0; x < TetriBattle.BLOCKS_WIDTH; x++) {
            for (int y = TetriBattle.BLOCKS_HEIGHT -1; y >= 0; y--) {
                if (gameInstance.getBlock(x, y).getBlockType() == blockType) {
                    int previousValue = (y + 1 >= TetriBattle.BLOCKS_HEIGHT) ? 0 : heights[x][y + 1];
                    heights[x][y] = previousValue + 1;
                } else {
                    heights[x][y] = 0;
                }
            }
        }
    }

    private void updateHeights() {
        for (int i = 0; i < maxRectangle.width; i++) {
            for (int j = 0; j < maxRectangle.height; j++) {
                int ii = maxRectangle.x + i;
                int jj = maxRectangle.y + j;
                heights[ii][jj] = 0;
            }
        }
    }

    /**
     * Find the largest area of rectangle in the histogram.
     */
    private void initializeWidths() {
        //
        for (int j = 0; j < TetriBattle.BLOCKS_HEIGHT; j++) {
            for (int i = 0; i < TetriBattle.BLOCKS_WIDTH; i++) {

                int height = heights[i][j];

                if (height != 0) {
                    int counter = 1;

                    // left
                    int li = i - 1;
                    boolean continueWhile = true;
                    while (continueWhile && li >= 0) {
                        if (height <= heights[li][j]) {
                            counter++;
                            li--;
                        } else {
                            continueWhile = false;
                        }
                    }

                    // right
                    int ri = i + 1;
                    continueWhile = true;
                    while (continueWhile && ri < TetriBattle.BLOCKS_WIDTH) {
                        if (height <= heights[ri][j]) {
                            counter++;
                            ri++;
                        } else {
                            continueWhile = false;
                        }
                    }

                    widths[i][j] = counter;
                    cornerPointX[i][j] = li + 1;
                } else {
                    widths[i][j] = 0;
                    cornerPointX[i][j] = -1;
                }
            }
        }
    }

    private boolean setMaxRectangle() {


        maxRectangle.set(-1, -1, -1, -1);
        int max = 0;
        boolean hasRectangle = false;

        for (int i = 0; i < TetriBattle.BLOCKS_WIDTH; i++) {
            for (int j = 0; j < TetriBattle.BLOCKS_HEIGHT; j++) {

                int w = widths[i][j];
                int h = heights[i][j];
                int area = w * h;
                if ((area > 0) && (w >= 2 && h >= 2)) {
                    if (area > max) {
                        maxRectangle.set(cornerPointX[i][j], j, w, h);
                        maxHistogramPoint.set(i, j);
                        max = area;
                        hasRectangle = true;
                    } else if (area == max) {
                        if (h > maxRectangle.height) { // yukseklige oncelik veriyoruz.
                            maxRectangle.set(cornerPointX[i][j], j, w, h);
                            maxHistogramPoint.set(i, j);
                            max = area;
                            hasRectangle = true;
                        }
                    }
                }
            }
        }
        // hic gem yoksa rectangele -1-1-1-1 olarak kalmis olabilir!
        return hasRectangle;
    }

    @Override
    public void update(GameInstance gameInstance, float delta) {

    }

    @Override
    public void exit(GameInstance gameInstance) {

    }
}
