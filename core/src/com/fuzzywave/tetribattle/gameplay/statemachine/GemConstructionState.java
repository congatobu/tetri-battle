package com.fuzzywave.tetribattle.gameplay.statemachine;


import com.fuzzywave.tetribattle.TetriBattle;
import com.fuzzywave.tetribattle.gameplay.BlockType;
import com.fuzzywave.tetribattle.gameplay.GameInstance;
import com.fuzzywave.tetribattle.util.IntegerPoint;
import com.fuzzywave.tetribattle.util.IntegerRectangle;
import com.fuzzywave.tetribattle.util.Quicksort;
import com.fuzzywave.tetribattle.util.Util;

public class GemConstructionState implements State {

    private int[] heights;
    private int[] widths;
    private int[] area;
    private int[] areaIndices;
    private int[] cornerPointX;
    private int[] cornerPointY;


    private IntegerPoint maxHistogramPoint;
    private IntegerRectangle maxRectangle;

    public GemConstructionState() {
        heights = new int[TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT];
        widths = new int[TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT];
        area = new int[TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT];
        areaIndices = new int[TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT];

        cornerPointX = new int[TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT];
        cornerPointY = new int[TetriBattle.BLOCKS_WIDTH * TetriBattle.BLOCKS_HEIGHT];

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
            for (int y = TetriBattle.BLOCKS_HEIGHT - 1; y >= 0; y--) {
                if (gameInstance.getBlock(x, y).getBlockType() == blockType) {
                    int previousValue = (y + 1 >= TetriBattle.BLOCKS_HEIGHT) ? 0 : heights[Util.getPosition(
                            x, y + 1)];
                    heights[Util.getPosition(x, y)] = previousValue + 1;
                } else {
                    heights[Util.getPosition(x, y)] = 0;
                }
            }
        }
    }

    private void updateHeights() {
        for (int i = 0; i < maxRectangle.width; i++) {
            for (int j = 0; j < maxRectangle.height; j++) {
                int ii = maxRectangle.x + i;
                int jj = maxRectangle.y + j;
                heights[Util.getPosition(ii, jj)] = 0;
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

                int index = Util.getPosition(i, j);
                int height = heights[index];

                if (height != 0) {
                    int counter = 1;

                    // left
                    int li = i - 1;
                    boolean continueWhile = true;
                    while (continueWhile && li >= 0) {
                        if (height <= heights[Util.getPosition(li, j)]) {
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
                        if (height <= heights[Util.getPosition(ri, j)]) {
                            counter++;
                            ri++;
                        } else {
                            continueWhile = false;
                        }
                    }

                    widths[index] = counter;
                    cornerPointX[index] = li + 1;
                    cornerPointY[index] = j;
                } else {
                    widths[index] = 0;
                    cornerPointX[index] = -1;
                    cornerPointY[index] = -1;
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

                int index = Util.getPosition(i, j);

                int w = widths[index];
                int h = heights[index];
                if(w >= 2 && h >= 2){
                    area[index] = w * h;
                }else{
                    area[index] = 0;
                }

                areaIndices[index] = index;
            }
        }

        Quicksort.quicksort(area, areaIndices);

        boolean continueSearch = true;
        int i = 0;
        while(continueSearch){
            int index = areaIndices[i];
            if(area[index] > 4){
                int width = widths[index];
                int height = heights[index];
                int x = cornerPointX[index];
                int y = cornerPointY[index];

                // TODO check gameInstance for existing GEMs.
                if(true) {
                    hasRectangle = true;
                    maxRectangle.set(x, y, width, height);
                    continueSearch = false;
                }else{
                    ++i;
                }
            }else{
                continueSearch = false;
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
