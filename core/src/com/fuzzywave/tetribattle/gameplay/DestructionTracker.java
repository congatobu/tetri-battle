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

    // TODO instead of destroring and recreating them in every destruction state, get them from a pool.
    private Array<DestructionCounter> chain;

    public DestructionTracker() {
        this.chain = new Array<DestructionCounter>(8);
    }

    public void clear() {
        this.chain.clear();
    }

    public void add() {
        this.chain.add(new DestructionCounter());
    }

    public void addToGemCounter(int width, int height) {
        this.chain.get(this.chain.size - 1).destructedGemSizes.add(new IntegerPoint(width, height));
    }

    public void addToBlockCounter(int blockCount) {
        this.chain.get(this.chain.size - 1).destructedBlockCount += blockCount;
    }

    public boolean isCombo() {
        return this.chain.size > 1;
    }

    public String getComboText() {
        int comboScore = this.chain.size - 2;
        return ComboNames.getText(comboScore);
    }
}
