package com.fuzzywave.tetribattle.gameplay;


import com.fuzzywave.tetribattle.util.Util;

import java.util.ArrayList;


/**
 * A pooling system for garbage collector problems.
 * There will be only 1 GemPool instance per GameInstance.
 */
public class GemPool {

    private final int initialCapacity = 10;
    private ArrayList<Gem> gemList = new ArrayList<Gem>(initialCapacity); // TODO initial capacity

    public GemPool() {
        for (int i = 0; i < initialCapacity; i++) {
            Gem gem = new Gem(0, 0, 0, 0);
            gem.setId(i);
            gemList.add(gem);
        }
    }


    /**
     * @return -1 if overlap/contain checks failed; otherwise ID of the New Gem.
     */
    public int tryToCreateGem(int x, int y, int width, int height) {

        if (check(x, y, width, height)) {
            return create(x, y, width, height);
        }
        return -1;
    }


    private boolean check(int x, int y, int width, int height) {
        for (Gem gem : gemList) {
            if (gem.isActive()) {
                if (Util.overlaps(x, y, width, height, gem.x, gem.y, gem.width, gem.height)) {
                    if (!Util.contains(x, y, width, height, gem.x, gem.y, gem.width, gem.height)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private int create(int x, int y, int width, int height) {
        // Gems that are also completely covered by newly created Gem, are deactivated.
        for (Gem gem : gemList) {
            if (gem.isActive()) {
                if (Util.overlaps(x, y, width, height, gem.x, gem.y, gem.width, gem.height)) {
                    if (Util.contains(x, y, width, height, gem.x, gem.y, gem.width, gem.height)) {
                        gem.setIsActive(false);
                    }
                }
            }
        }

        Gem gem = getFirstInactiveGem();
        gem.setIsActive(true);
        gem.set(x, y, width, height); // set new sizes.
        return gem.getId(); // use this ID to mark blocks.
    }

    private Gem getFirstInactiveGem() {
        for (Gem gem : gemList) {
            if (!gem.isActive()) {
                return gem;
            }
        }
        Gem gem = new Gem(0, 0, 0, 0);
        gem.setId(gemList.size());
        gemList.add(gem);
        return gem;
    }


    public void destroyGems() {
        for (Gem gem : gemList) {
            if (gem.isActive() && gem.getDestructionMarker()) {
                gem.setIsActive(false);
                gem.setDestructionMarker(false);
            }
        }
    }

    public void setDestructionMarker(int gemId) {
        gemList.get(gemId).setDestructionMarker(true);
    }

    public Gem getGem(int gemId) {
        return gemList.get(gemId);
    }
}
