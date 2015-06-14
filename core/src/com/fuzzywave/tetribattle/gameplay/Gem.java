package com.fuzzywave.tetribattle.gameplay;


import com.fuzzywave.tetribattle.util.IntegerRectangle;

public class Gem extends IntegerRectangle {

    private int id;
    private boolean isActive;
    private boolean destructionMarker;

    public Gem(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.isActive = false;
        this.destructionMarker = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getDestructionMarker() {
        return destructionMarker;
    }

    public void setDestructionMarker(boolean destructionMarker) {
        this.destructionMarker = destructionMarker;
    }
}
