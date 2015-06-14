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

    public boolean contains(IntegerRectangle rectangle) {
        float xmin = rectangle.x;
        float xmax = xmin + rectangle.width;

        float ymin = rectangle.y;
        float ymax = ymin + rectangle.height;

        return ((xmin > x && xmin < x + width) && (xmax > x && xmax < x + width))
                && ((ymin > y && ymin < y + height) && (ymax > y && ymax < y + height));
    }


    public boolean overlaps(IntegerRectangle r) {
        return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
    }

    public boolean overlaps(int x2, int y2, int width2, int height2) {
        return x < x2 + width2 && x + width > x2 && y < y2 + height2 && y + height > y2;
    }



}
