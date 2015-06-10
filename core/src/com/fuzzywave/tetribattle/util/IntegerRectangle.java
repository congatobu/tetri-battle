package com.fuzzywave.tetribattle.util;

public class IntegerRectangle {

    public int x;
    public int y;
    public int width;
    public int height;

    public IntegerRectangle(int x,
                            int y,
                            int width,
                            int height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void set(int x,
                    int y,
                    int width,
                    int height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

}
