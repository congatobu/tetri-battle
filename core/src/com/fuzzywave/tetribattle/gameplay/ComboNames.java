package com.fuzzywave.tetribattle.gameplay;


public class ComboNames {

    private final static String[] COMBO_NAMES = {"MIN_COMBO", "SUPER_COMBO", "MEGA_COMBO", "ULTRA_COMBO", "HYPER_COMBO", "GIGA_COMBO", "MAX_COMBO"};

    public static String getText(int index) {
        return COMBO_NAMES[Math.min(index, COMBO_NAMES.length - 1)];
    }
}
