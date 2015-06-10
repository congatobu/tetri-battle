package com.fuzzywave.tetribattle.util;

import com.fuzzywave.tetribattle.TetriBattle;

public class Util {

    public static int getPosition(int x, int y) {
        return x + y * TetriBattle.BLOCKS_WIDTH;
    }


}
