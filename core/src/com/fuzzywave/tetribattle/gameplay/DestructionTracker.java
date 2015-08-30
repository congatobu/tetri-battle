package com.fuzzywave.tetribattle.gameplay;

import com.badlogic.gdx.utils.Array;
import com.fuzzywave.tetribattle.util.IntegerPoint;

/**
 * This class is responsible for tracking the count of destroyed blocks and/or gems.
 */
public class DestructionTracker {

    static class DestructionCounter {
        Array<IntegerPoint> destructedGemSizes;
        int destructedBlockCount;

        public DestructionCounter() {
            this.destructedGemSizes = new Array<IntegerPoint>(2);
            this.destructedBlockCount = 0;
        }
    }

    private Array<DestructionCounter> counters;

    public DestructionTracker() {
        this.counters = new Array<DestructionCounter>(8);
    }

    public void clear() {
        this.counters.clear();
    }

    public void add() {
        this.counters.add(new DestructionCounter());
    }

    public void addToGemCounter(int width, int height) {
        this.counters.get(this.counters.size - 1).destructedGemSizes.add(new IntegerPoint(width, height));
    }

    public void addToBlockCounter(int blockCount) {
        this.counters.get(this.counters.size - 1).destructedBlockCount += blockCount;
    }

    public String getComboText() {
        return "COMBO";
    }
}
