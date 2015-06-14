package com.fuzzywave.tetribattle.util;

import com.fuzzywave.tetribattle.TetriBattle;

public class Util {

    public static int getPosition(int x, int y) {
        return x + y * TetriBattle.BLOCKS_WIDTH;
    }

    /**
     * @return whether the first rectangle overlaps the second rectangle.
     */
    public static boolean overlaps(int x1, int y1, int width1, int height1, int x2, int y2,
                                   int width2, int height2) {
        return x1 < x2 + width2 && x1 + width1 > x2 && y1 < y2 + height2 && y1 + height1 > y2;
    }

    /**
     * @return whether the first rectangle contains the second one.
     */
    public static boolean contains(int x1, int y1, int width1, int height1, int x2, int y2,
                                   int width2, int height2) {

        float xmin = x2;
        float xmax = xmin + width2;

        float ymin = y2;
        float ymax = ymin + height2;

        return ((xmin >= x1 && xmin <= x1 + width1) && (xmax >= x1 && xmax <= x1 + width1))
                && ((ymin >= y1 && ymin <= y1 + height1) && (ymax >= y1 && ymax <= y1 + height1));
    }


}
